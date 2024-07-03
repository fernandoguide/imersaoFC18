package br.com.fernandoguide.imersaofc18.infra.dto;

import br.com.fernandoguide.imersaofc18.domain.Spot;

public record SpotDTO(
        String id,
        String name,
        String status,
        String ticketId
) {
    public static SpotDTO builder(Spot spot) {
        return new SpotDTO(spot.getId(), spot.getName(), spot.getStatus().name(), spot.getTicketID());
    }
}
