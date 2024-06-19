package br.com.fernandoguide.imersaofc18.domain;

import br.com.fernandoguide.imersaofc18.domain.exceptions.DomainException;
import lombok.Data;

import java.util.UUID;

@Data
public class Spot {
    private String id;
    private String eventID;
    private String name;
    private SpotStatus status;
    private String ticketID;

    public Spot(String eventID, String name) {
        this.id = UUID.randomUUID().toString();
        this.eventID = eventID;
        this.name = name;
        this.status = SpotStatus.AVAILABLE;
    }

    public void reserve(String ticketID) throws DomainException {
        if (this.status == SpotStatus.SOLD) {
            throw new DomainException("Spot already reserved");
        }
        this.status = SpotStatus.SOLD;
        this.ticketID = ticketID;
    }

    public void validate() throws DomainException {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Spot name is required");
        }
        if (name.length() < 2) {
            throw new DomainException("Spot name must be at least 2 characters long");
        }
        // Validate if the spot name is in the correct format
        if (!Character.isLetter(name.charAt(0))) {
            throw new DomainException("Spot name must start with a letter");
        }
        if (!Character.isDigit(name.charAt(1))) {
            throw new DomainException("Spot name must end with a number");
        }
    }
}
