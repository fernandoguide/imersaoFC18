package br.com.fernandoguide.imersaofc18.usecase.spot;

public record CreateSpotsInputDTO(
        String eventId,
        Integer numberOfSpots
) {
}
