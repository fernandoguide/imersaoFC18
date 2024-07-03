package br.com.fernandoguide.imersaofc18.infra.factory;

import br.com.fernandoguide.imersaofc18.infra.dto.ReservationRequest;
import br.com.fernandoguide.imersaofc18.infra.dto.ReservationResponse;

import java.util.List;

public interface Partner {
    List<ReservationResponse> makeReservation(ReservationRequest req) throws Exception;
}
