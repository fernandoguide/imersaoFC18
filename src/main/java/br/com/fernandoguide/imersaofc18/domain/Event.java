package br.com.fernandoguide.imersaofc18.domain;

import br.com.fernandoguide.imersaofc18.domain.exceptions.DomainException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class Event {
    private String id;
    private String name;
    private String location;
    private String organization;
    private Rating rating;
    private LocalDateTime date;
    private String imageURL;
    private Integer capacity;
    private BigDecimal price;
    private Integer partnerID;
    private List<Spot> spots;
    private List<Ticket> tickets;

    public Event(String name, String location, String organization, Rating rating, LocalDateTime date,
                 Integer capacity, BigDecimal price, String imageUrl, Integer partnerID) throws DomainException {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.location = location;
        this.organization = organization;
        this.rating = rating;
        this.date = date;
        this.capacity = capacity;
        this.price = price;
        this.imageURL = imageUrl;
        this.partnerID = partnerID;
        this.spots = new ArrayList<>();
        this.tickets = new ArrayList<>();

        validate();
    }

    private void validate() throws DomainException {
        if (name == null || name.isEmpty()) {
            throw new DomainException("Event name is required");
        }
        if (date.isBefore(LocalDateTime.now())) {
            throw new DomainException("Event date must be in the future");
        }
        if (capacity <= 0) {
            throw new DomainException("Event capacity must be greater than zero");
        }
        if (price.doubleValue() <= 0) {
            throw new DomainException("Event price must be greater than zero");
        }
    }

    public void addSpot(String name) throws DomainException {
        Spot spot = new Spot(this.id, name);
        spots.add(spot);
    }

}
