package br.com.fernandoguide.imersaofc18.usecase.ticket;

import br.com.fernandoguide.imersaofc18.infra.dto.TicketDTO;

import java.util.List;

public record BuyTicketsOutputDTO(List<TicketDTO> tickets) {
}
