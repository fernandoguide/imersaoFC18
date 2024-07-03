package br.com.fernandoguide.imersaofc18.infra.factory;

import br.com.fernandoguide.imersaofc18.infra.dto.ReservationRequest;
import br.com.fernandoguide.imersaofc18.infra.dto.ReservationResponse;
import br.com.fernandoguide.imersaofc18.infra.service.partner.Partner2ReservationRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Partner2 implements Partner{
    private final String baseURL;
    private final RestTemplate restTemplate;

    public Partner2(String baseURL) {
        this.baseURL = baseURL;
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<ReservationResponse> makeReservation(ReservationRequest req) throws Exception {
        var partnerReq = new Partner2ReservationRequest(
                req.spots(),
                req.ticketType(),
                req.email()
        );
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // Configura a URL do endpoint
        var endpoint = baseURL + "/eventos/" + req.eventID() + "/reservar";
        // Cria a entidade HTTP com o corpo da requisição e headers
        var requestEntity = new HttpEntity<>(partnerReq, headers);
        // Envia a requisição POST usando RestTemplate
        var partnerResponses = restTemplate.exchange(
                endpoint,
                HttpMethod.POST,
                requestEntity,
                ReservationResponse[].class
        ).getBody();
        // Converte para o formato de resposta esperado

        return Arrays.stream(Objects.requireNonNull(partnerResponses)).collect(Collectors.toList());
    }
}
