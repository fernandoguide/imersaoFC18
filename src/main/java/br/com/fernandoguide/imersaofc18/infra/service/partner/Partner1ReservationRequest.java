package br.com.fernandoguide.imersaofc18.infra.service.partner;

import java.util.List;

public record Partner1ReservationRequest(
        List<String> spots,
        String ticketType,
        String email
) {
}
