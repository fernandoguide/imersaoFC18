package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;

public class GetEventUseCase {
    private final EventRepository repo;

    public GetEventUseCase(EventRepository repo) {
        this.repo = repo;
    }

    public GetEventOutputDTO execute(GetEventInputDTO input) {
        Event event = repo.findEventByID(input.id());
        if (event == null) {
            throw new RuntimeException("Event not found");
        }
        return GetEventOutputDTO.builder(event);
    }
}
