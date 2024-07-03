package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;
import br.com.fernandoguide.imersaofc18.infra.dto.EventDTO;

import java.util.ArrayList;
import java.util.List;

public class ListEventsUseCase {
    private final EventRepository repo;

    public ListEventsUseCase(EventRepository repo) {
        this.repo = repo;
    }

    public ListEventsOutputDTO execute() {
        List<Event> events = repo.findAllEvents();
        List<EventDTO> eventDTOs = new ArrayList<>();
        for (Event event : events) {
            EventDTO builder = EventDTO.builder(event);
            eventDTOs.add(builder);
        }
        return new ListEventsOutputDTO(eventDTOs);
    }
}
