package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.domain.Event;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public record GetEventOutputDTO(
        String id,
        String name,
        String location,
        String organization,
        String rating,
        String date,
        Integer capacity,
        BigDecimal price,
        Integer partnerId
) {
    public static GetEventOutputDTO builder(Event event) {
        return new GetEventOutputDTO(
                event.getId(),
                event.getName(),
                event.getLocation(),
                event.getOrganization(),
                event.getRating().name(),
                event.getDate().format(DateTimeFormatter.BASIC_ISO_DATE),
                event.getCapacity(),
                event.getPrice(),
                event.getPartnerID()
        );
    }
}
