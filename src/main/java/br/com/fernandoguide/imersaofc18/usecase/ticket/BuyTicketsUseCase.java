package br.com.fernandoguide.imersaofc18.usecase.ticket;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;
import br.com.fernandoguide.imersaofc18.domain.Ticket;
import br.com.fernandoguide.imersaofc18.domain.TicketType;
import br.com.fernandoguide.imersaofc18.domain.repository.EventRepository;
import br.com.fernandoguide.imersaofc18.infra.dto.ReservationRequest;
import br.com.fernandoguide.imersaofc18.infra.dto.ReservationResponse;
import br.com.fernandoguide.imersaofc18.infra.dto.TicketDTO;
import br.com.fernandoguide.imersaofc18.infra.factory.Partner;
import br.com.fernandoguide.imersaofc18.infra.factory.PartnerFactory;

import java.util.ArrayList;
import java.util.List;

public class BuyTicketsUseCase {
    private final EventRepository repo;
    private final PartnerFactory partnerFactory;

    public BuyTicketsUseCase(EventRepository repo, PartnerFactory partnerFactory) {
        this.repo = repo;
        this.partnerFactory = partnerFactory;
    }

    public BuyTicketsOutputDTO execute(BuyTicketsInputDTO input) throws Exception {
        // Verifica o evento
        Event event = repo.findEventByID(input.eventId());
        // Cria a solicitação de reserva
        ReservationRequest req = new ReservationRequest(
                input.eventId(),
                input.spots(),
                input.ticketType(),
                input.cardHash(),
                input.email()
        );
        // Obtém o serviço do parceiro
        Partner partnerService = partnerFactory.createPartner(event.getPartnerID());

        // Reserva os lugares usando o serviço do parceiro
        List<ReservationResponse> reservationResponse = partnerService.makeReservation(req);

        // Salva os ingressos no banco de dados
        List<Ticket> tickets = new ArrayList<>();
        for (ReservationResponse reservation : reservationResponse) {
            Spot spot = repo.findSpotByName(event.getId(), reservation.spot().getName());
            Ticket ticket = new Ticket(event, spot, TicketType.valueOf(input.ticketType()));
            repo.createTicket(ticket);
            spot.reserve(ticket.getId());
            repo.reserveSpot(spot.getId(), ticket.getId());
            tickets.add(ticket);
        }
        // Converte os tickets para DTOs
        List<TicketDTO> ticketDTOs = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDTOs.add(new TicketDTO(ticket.getId(), ticket.getSpot().getId(), ticket.getTicketType().toString(), ticket.getPrice()));
        }
        return new BuyTicketsOutputDTO(ticketDTOs);
    }
}
