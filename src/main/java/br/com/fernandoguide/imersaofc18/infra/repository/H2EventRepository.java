package br.com.fernandoguide.imersaofc18.infra.repository;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.Ticket;
import br.com.fernandoguide.imersaofc18.domain.exceptions.DomainException;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;

import java.util.List;

public class H2EventRepository implements EventRepository {
    @Override
    public List<Event> findAllEvents() throws DomainException {
        return null;
    }

    @Override
    public Event findEventByID(String eventID) throws DomainException {
        return null;
    }

    @Override
    public List<Spot> findSpotsByEventID(String eventID) throws DomainException {
        return null;
    }

    @Override
    public Spot findSpotByName(String eventID, String spotName) throws DomainException {
        return null;
    }

    @Override
    public Event createEvent(Event event) throws DomainException {
        return null;
    }

    @Override
    public Spot findSpotByID(String spotId) {
        return null;
    }

    @Override
    public Spot createSpot(Spot spot) throws DomainException {
        return null;
    }

    @Override
    public Ticket createTicket(Ticket ticket) throws DomainException {
        return null;
    }

    @Override
    public void reserveSpot(String spotID, String ticketID) throws DomainException {

    }
}
