package br.com.fernandoguide.imersaofc18.usecase.spot;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;
import br.com.fernandoguide.imersaofc18.infra.dto.EventDTO;
import br.com.fernandoguide.imersaofc18.infra.dto.SpotDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ListSpotsUseCase {
    private final EventRepository repo;

    public ListSpotsUseCase(EventRepository repo) {
        this.repo = repo;
    }

    public ListSpotsOutputDTO execute(ListSpotsInputDTO input) {
        Event event = repo.findEventByID(input.eventId());
        List<Spot> spots = repo.findSpotsByEventID(input.eventId());

        List<SpotDTO> spotDTOs = spots.stream()
                .map(SpotDTO::builder)
                .collect(Collectors.toList());
        return new ListSpotsOutputDTO(EventDTO.builder(event), spotDTOs);
    }
}
