package br.com.fernandoguide.imersaofc18.infra.repository;

import br.com.fernandoguide.imersaofc18.domain.*;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        String eventId = rs.getString("id");
        Event event = new Event();
        event.setId(eventId);
        event.setName(rs.getString("name"));
        event.setLocation(rs.getString("location"));
        event.setOrganization(rs.getString("organization"));
        event.setRating(Rating.valueOf(rs.getString("rating")));
        event.setDate(rs.getObject("date", LocalDateTime.class));
        event.setImageURL(rs.getString("image_url"));
        event.setCapacity(rs.getInt("capacity"));
        event.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
        event.setPartnerID(rs.getInt("partner_id"));
        event.setSpots(new ArrayList<>());
        event.setTickets(new ArrayList<>());

        do {
            Spot spot = extractSpot(rs);
            if (spot != null) {
                event.getSpots().add(spot);
            }

            Ticket ticket = extractTicket(rs);
            if (ticket != null) {
                event.getTickets().add(ticket);
            }
        } while (rs.next() && eventId.equals(rs.getString("id")));

        return event;
    }

    private Spot extractSpot(ResultSet rs) throws SQLException {
        String spotId = rs.getString("spot_id");
        if (spotId == null) {
            return null;
        }

        Spot spot = new Spot();
        spot.setId(spotId);
        spot.setEventID(rs.getString("spot_event_id"));
        spot.setName(rs.getString("spot_name"));
        spot.setStatus(SpotStatus.valueOf(rs.getString("spot_status")));
        spot.setTicketID(rs.getString("spot_ticket_id"));
        return spot;
    }

    private Ticket extractTicket(ResultSet rs) throws SQLException {
        return getTicket(rs);
    }

    static Ticket getTicket(ResultSet rs) throws SQLException {
        String ticketId = rs.getString("ticket_id");
        if (ticketId == null) {
            return null;
        }

        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setEventID(rs.getString("ticket_event_id"));
        ticket.getSpot().setTicketID(rs.getString("ticket_spot_id"));
        ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
        ticket.setPrice(BigDecimal.valueOf(rs.getDouble("ticket_price")));
        return ticket;
    }
}
