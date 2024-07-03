package br.com.fernandoguide.imersaofc18.infra.service.partner;

import br.com.fernandoguide.imersaofc18.infra.factory.Partner;
import br.com.fernandoguide.imersaofc18.infra.factory.Partner1;
import br.com.fernandoguide.imersaofc18.infra.factory.Partner2;
import br.com.fernandoguide.imersaofc18.infra.factory.PartnerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PartnerService implements PartnerFactory {
    private final Map<Integer, String> partnerBaseURLs;

    public PartnerService(Map<Integer, String> partnerBaseURLs) {
        this.partnerBaseURLs = partnerBaseURLs;
    }

    @Override
    public Partner createPartner(int partnerID) throws IllegalArgumentException {
        String baseURL = partnerBaseURLs.get(partnerID);
        if (baseURL == null) {
            throw new IllegalArgumentException("Unsupported partner ID: " + partnerID);
        }

        return switch (partnerID) {
            case 1 -> new Partner1(baseURL);
            case 2 -> new Partner2(baseURL);
            default -> throw new IllegalArgumentException("Unsupported partner ID: " + partnerID);
        };
    }
}
