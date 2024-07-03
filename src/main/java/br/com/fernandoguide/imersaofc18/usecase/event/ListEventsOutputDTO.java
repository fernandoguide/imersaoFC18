package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.infra.dto.EventDTO;

import java.util.List;

public record ListEventsOutputDTO(List<EventDTO> events) {
}
