package br.com.fernandoguide.imersaofc18.usecase.spot;

import br.com.fernandoguide.imersaofc18.infra.dto.SpotDTO;

import java.util.List;

public record CreateSpotsOutputDTO(List<SpotDTO> spots) {
}
