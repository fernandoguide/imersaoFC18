package br.com.fernandoguide.imersaofc18.domain.service;

import br.com.fernandoguide.imersaofc18.domain.Event;
import br.com.fernandoguide.imersaofc18.domain.Spot;

public class ServiceDomain {
    public static ServiceDomain newInstance() {
        return new ServiceDomain();
    }

    // Método para gerar os spots para um evento
    public void generateSpots(Event event, int quantity) throws Exception {
        if (quantity <= 0) {
            throw new Exception("Quantity must be greater than zero");
        }

        for (int i = 0; i < quantity; i++) {
            char letter = (char) ('A' + i / 10);
            int number = i % 10 + 1;
            String spotName = String.format("%c%d", letter, number);
            Spot spot = new Spot(event.getId(), spotName);
            event.getSpots().add(spot);
        }
    }

}
