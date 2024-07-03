package br.com.fernandoguide.imersaofc18.infra.dto;

import br.com.fernandoguide.imersaofc18.domain.Event;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record EventDTO(
        String id,
        String name,
        String location,
        String organization,
        String rating,
        String date,
        Integer capacity,
        BigDecimal price,
        Integer partnerId,
        String imageUrl
) {
    public static EventDTO builder(Event event) {
        return new EventDTO(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getOrganization(),
                event.getRating().name(),
                event.getDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                event.getCapacity(),
                event.getPrice(),
                event.getPartnerID(),
                event.getImageURL()
        );
    }
}
