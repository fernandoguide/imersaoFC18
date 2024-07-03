package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.domain.Event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateEventInputDTO(
        String name,
        String location,
        String organization,
        String rating,
        LocalDateTime date,
        Integer capacity,
        String imageUrl,
        BigDecimal price,
        Integer partnerId
) {
    public static CreateEventInputDTO builder(Event event) {
        return new CreateEventInputDTO(
                event.getName(),
                event.getLocation(),
                event.getOrganization(),
                event.getRating().name(),
                event.getDate(),
                event.getCapacity(),
                event.getImageURL(),
                event.getPrice(),
                event.getPartnerID()
        );
    }
}
