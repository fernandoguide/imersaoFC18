package br.com.fernandoguide.imersaofc18.usecase.spot;

import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;
import br.com.fernandoguide.imersaofc18.infra.dto.SpotDTO;

import java.util.ArrayList;
import java.util.List;

public class CreateSpotsUseCase {
    private final EventRepository repo;

    public CreateSpotsUseCase(EventRepository repo) {
        this.repo = repo;
    }

    public CreateSpotsOutputDTO execute(CreateSpotsInputDTO input) {

        var event = repo.findEventByID(input.eventId());
        if (event == null) {
            throw new RuntimeException("Event not found");
        }
        List<Spot> spots = new ArrayList<>();
        for (int i = 0; i < input.numberOfSpots(); i++) {
            String spotName = generateSpotName(i);
            Spot spot = new Spot(event.getId(), spotName);
            repo.createSpot(spot);
            spots.add(spot);
        }
        List<SpotDTO> spotDTOs = new ArrayList<>();
        for (Spot spot : spots) {
            spotDTOs.add(new SpotDTO(
                            spot.getId(),
                            spot.getName(),
                            spot.getStatus().toString(),
                            spot.getTicketID())
            );
        }
        return new CreateSpotsOutputDTO(spotDTOs);
    }

    private String generateSpotName(int index) {
        char letter = (char) ('A' + (index / 10));
        int number = (index % 10) + 1;
        return String.format("%c%d", letter, number);
    }
}
