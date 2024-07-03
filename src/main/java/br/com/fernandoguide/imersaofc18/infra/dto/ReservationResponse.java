package br.com.fernandoguide.imersaofc18.infra.dto;

import br.com.fernandoguide.imersaofc18.domain.Spot;

public record ReservationResponse(String id, String email, Spot spot, String ticketType, String status, String eventID) {
}
