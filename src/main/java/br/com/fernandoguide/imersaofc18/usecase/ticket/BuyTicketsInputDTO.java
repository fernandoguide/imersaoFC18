package br.com.fernandoguide.imersaofc18.usecase.ticket;

import java.util.List;

public record BuyTicketsInputDTO(
        String eventId,
        List<String> spots,
        String ticketType,
        String cardHash,
        String email
) {
}
