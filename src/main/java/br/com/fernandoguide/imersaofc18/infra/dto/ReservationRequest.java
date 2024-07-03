package br.com.fernandoguide.imersaofc18.infra.dto;

import java.util.List;

public record ReservationRequest(String eventID, List<String> spots, String ticketType, String cardHash, String email) {
}
