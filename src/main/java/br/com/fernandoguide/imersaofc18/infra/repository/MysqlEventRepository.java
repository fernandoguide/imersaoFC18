package br.com.fernandoguide.imersaofc18.infra.repository;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.Ticket;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MysqlEventRepository implements EventRepository {
    private final JdbcTemplate jdbcTemplate;

    public MysqlEventRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Event> findAllEvents() {
        String query = "SELECT e.id, e.name, e.location, e.organization, e.rating, e.date, e.image_url, " +
                "e.capacity, e.price, e.partner_id, " +
                "s.id AS spot_id, s.event_id AS spot_event_id, s.name AS spot_name, " +
                "s.status AS spot_status, s.ticket_id AS spot_ticket_id, " +
                "t.id AS ticket_id, t.event_id AS ticket_event_id, t.spot_id AS ticket_spot_id, " +
                "t.ticket_type, t.price AS ticket_price " +
                "FROM events e " +
                "LEFT JOIN spots s ON e.id = s.event_id " +
                "LEFT JOIN tickets t ON s.id = t.spot_id";
        return jdbcTemplate.query(query, new EventRowMapper());
    }

    @Override
    public Event findEventByID(String eventId) {
        String query = "SELECT e.id, e.name, e.location, e.organization, e.rating, e.date, e.image_url, " +
                "e.capacity, e.price, e.partner_id, " +
                "s.id AS spot_id, s.event_id AS spot_event_id, s.name AS spot_name, " +
                "s.status AS spot_status, s.ticket_id AS spot_ticket_id, " +
                "t.id AS ticket_id, t.event_id AS ticket_event_id, t.spot_id AS ticket_spot_id, " +
                "t.ticket_type, t.price AS ticket_price " +
                "FROM events e " +
                "LEFT JOIN spots s ON e.id = s.event_id " +
                "LEFT JOIN tickets t ON s.id = t.spot_id " +
                "WHERE e.id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{eventId}, new EventRowMapper());
    }

    @Override
    public Event createEvent(Event event) {
        String query = "INSERT INTO events (id, name, location, organization, rating, date, image_url, " +
                "capacity, price, partner_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, event.getId(), event.getName(), event.getLocation(),
                event.getOrganization(), event.getRating(), event.getDate(),
                event.getImageURL(), event.getCapacity(), event.getPrice(),
                event.getPartnerID());
        return event;
    }

    @Override
    public Spot findSpotByID(String spotId) {
        String query = "SELECT s.id, s.event_id, s.name, s.status, s.ticket_id, " +
                "t.id AS ticket_id, t.event_id AS ticket_event_id, " +
                "t.spot_id AS ticket_spot_id, t.ticket_type, t.price AS ticket_price " +
                "FROM spots s LEFT JOIN tickets t ON s.id = t.spot_id WHERE s.id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{spotId}, new SpotRowMapper());
    }

    @Override
    public Spot createSpot(Spot spot) {
        String query = "INSERT INTO spots (id, event_id, name, status, ticket_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, spot.getId(), spot.getEventID(), spot.getName(),
                spot.getStatus(), spot.getTicketID());
        return spot;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        String query = "INSERT INTO tickets (id, event_id, spot_id, ticket_type, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, ticket.getId(), ticket.getEventID(), ticket.getSpot().getId(),
                ticket.getTicketType(), ticket.getPrice());
        return ticket;
    }

    @Override
    public void reserveSpot(String spotId, String ticketId) {
        String query = "UPDATE spots SET status = ?, ticket_id = ? WHERE id = ?";
        jdbcTemplate.update(query, "sold", ticketId, spotId);
    }

    @Override
    public List<Spot> findSpotsByEventID(String eventId) {
        String query = "SELECT id, event_id, name, status, ticket_id FROM spots WHERE event_id = ?";
        return jdbcTemplate.query(query, new Object[]{eventId}, new SpotRowMapper());
    }

    @Override
    public Spot findSpotByName(String eventId, String name) {
        String query = "SELECT s.id, s.event_id, s.name, s.status, s.ticket_id, " +
                "t.id AS ticket_id, t.event_id AS ticket_event_id, " +
                "t.spot_id AS ticket_spot_id, t.ticket_type, t.price AS ticket_price " +
                "FROM spots s LEFT JOIN tickets t ON s.id = t.spot_id " +
                "WHERE s.event_id = ? AND s.name = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{eventId, name}, new SpotRowMapper());
    }

}
