package edu.pwr.pl.Lab06.app.tariff;

import edu.pwr.pl.Lab06.app.tariff.DTO.TariffUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TariffService {
    private final TariffRepo tariffRepo;

    @Autowired
    public TariffService(TariffRepo tariffRepo){this.tariffRepo = tariffRepo;}

    public void createTariff(Tariff tariff) {
        if(tariffRepo.findTariffByTariffType(tariff.getTariffType()).isEmpty()){
            tariffRepo.save(new Tariff(
                    tariff.getTariffType(),
                    tariff.getPrice()
            ));
        }
    }

    public void updateTariff(TariffUpdateRequest request) {
        Optional<Tariff> tariffOptional = tariffRepo.findTariffById(request.getId());
        if (tariffOptional.isPresent()){
            Tariff tariff = tariffOptional.get();
            if(request.getTariffType() != null) {
                tariff.setTariffType(request.getTariffType());
            }
            if (request.getPrice() != null){
                tariff.setPrice(request.getPrice());
            }
            tariffRepo.save(tariff);
        }
    }
}
