package br.com.fernandoguide.imersaofc18.infra.dto;

import java.math.BigDecimal;

public record TicketDTO(String id, String spotId, String ticketType, BigDecimal price) {
}
