package br.com.fernandoguide.imersaofc18.infra.repository;

import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.SpotStatus;
import br.com.fernandoguide.imersaofc18.domain.Ticket;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import static br.com.fernandoguide.imersaofc18.infra.repository.EventRowMapper.getTicket;

public class SpotRowMapper implements RowMapper<Spot> {

    @Override
    public Spot mapRow(ResultSet rs, int rowNum) throws SQLException {
        Spot spot = new Spot();
        spot.setId(rs.getString("id"));
        spot.setEventID(rs.getString("event_id"));
        spot.setName(rs.getString("name"));
        spot.setStatus(SpotStatus.valueOf(rs.getString("status")));
        spot.setTicketID(rs.getString("ticket_id"));

        Ticket ticket = extractTicket(rs);
        if (ticket != null) {
            spot.setTicketID(ticket.getId());
        }

        return spot;
    }

    private Ticket extractTicket(ResultSet rs) throws SQLException {
        return getTicket(rs);
    }
}
