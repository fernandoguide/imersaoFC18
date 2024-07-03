package br.com.fernandoguide.imersaofc18.usecase.event;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Rating;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;

import java.time.LocalDateTime;


public class CreateEventUseCase {
    private final EventRepository repo;

    public CreateEventUseCase(EventRepository eventRepository) {
        this.repo = eventRepository;
    }


    public CreateEventOutputDTO execute(CreateEventInputDTO input) {
        Event event = new Event(
                input.name(),
                input.location(),
                input.organization(),
                Rating.valueOf(input.name()),
                LocalDateTime.now(),
                input.capacity(),
                input.price(),
                input.imageUrl(),
                input.partnerId()
        );
        return CreateEventOutputDTO.builder(repo.createEvent(event));
    }
}
