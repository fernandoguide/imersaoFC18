package br.com.fernandoguide.imersaofc18.domain;

import br.com.fernandoguide.imersaofc18.domain.exceptions.DomainException;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;


@Data
public class Ticket {
    private String id;
    private String eventID;
    private Spot spot;
    private TicketType ticketType;
    private BigDecimal price;

    public Ticket(Event event, Spot spot, TicketType ticketType) throws DomainException {
        if (!isValidTicketType(ticketType)) {
            throw new DomainException("Invalid ticket type");
        }

        this.id = UUID.randomUUID().toString();
        this.eventID = event.getId();
        this.spot = spot;
        this.ticketType = ticketType;
        this.price = event.getPrice();
        calculatePrice();
        validate();
    }

    private boolean isValidTicketType(TicketType ticketType) {
        return ticketType == TicketType.HALF || ticketType == TicketType.FULL;
    }

    private void calculatePrice() {
        if (ticketType == TicketType.HALF) {
            this.price = this.price.divide(BigDecimal.valueOf(2), RoundingMode.HALF_DOWN);
        }
    }

    private void validate() throws DomainException {
        if (this.price.doubleValue() <= 0) {
            throw new DomainException("Ticket price must be greater than zero");
        }
    }

}
