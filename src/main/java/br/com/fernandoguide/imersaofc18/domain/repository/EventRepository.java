package br.com.fernandoguide.imersaofc18.domain.repository;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.Ticket;
import br.com.fernandoguide.imersaofc18.domain.exceptions.DomainException;

import java.util.List;

public interface EventRepository {
    List<Event> findAllEvents() throws DomainException;

    Event findEventByID(String eventID) throws DomainException;

    List<Spot> findSpotsByEventID(String eventID) throws DomainException;

    Spot findSpotByName(String eventID, String spotName) throws DomainException;

    Event createEvent(Event event) throws DomainException;

    Spot createSpot(Spot spot) throws DomainException;

    Ticket createTicket(Ticket ticket) throws DomainException;

    void reserveSpot(String spotID, String ticketID) throws DomainException;

}
