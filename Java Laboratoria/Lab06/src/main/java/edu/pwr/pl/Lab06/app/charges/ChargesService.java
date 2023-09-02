package edu.pwr.pl.Lab06.app.charges;

import edu.pwr.pl.Lab06.app.charges.DTO.ChargesCreateRequest;
import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.installation.InstallationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChargesService {
    private final ChargesRepo chargesRepo;
    private final InstallationRepo installationRepo;
    @Autowired
    public ChargesService(ChargesRepo chargesRepo, InstallationRepo installationRepo){
        this.chargesRepo =chargesRepo;
        this.installationRepo = installationRepo;
    }

    public void createCharge(ChargesCreateRequest request) {
        Optional<Installation> installationOptional = installationRepo.findInstallationByRouterNumber(request.getRouterNr());
        if(installationOptional.isPresent()){
            Installation installation = installationOptional.get();
            chargesRepo.save(new Charges(
                    request.getPaymentDueDate(),
                    request.getAmountToPay(),
                    installation
            ));
        }
    }

    public void createCharge(LocalDate paymentDueDate,Long routerNr){
        Optional<Installation> installationOptional = installationRepo.findInstallationByRouterNumber(routerNr);
        if (installationOptional.isPresent()){
            Installation installation = installationOptional.get();
            chargesRepo.save(new Charges(
                    paymentDueDate,
                    installation.getTariffType().getPrice(),
                    installation
            ));

        }

    }

    public void checkPayment(){

    }




    public Charges getCharges(Long routerNr) {
        Optional<Installation> installationOptional = installationRepo.findInstallationByRouterNumber(routerNr);
        if (installationOptional.isPresent()){
            Installation installation = installationOptional.get();
            Optional<Charges> chargesOptional = chargesRepo.findChargesByInstallation(installation);
            if (chargesOptional.isPresent()){
                Charges charges = chargesOptional.get();
                return charges;
            }
        }
        return null;
    }

    public Charges getCharges(Installation installation){
        Optional<Charges> chargesOptional = chargesRepo.findChargesByInstallation(installation);
        if (chargesOptional.isPresent()){
            return chargesOptional.get();
        }
        return null;
    }

    public List<Charges> getByClient(Long clientNr) {
        return chargesRepo.findChargesByCustomerNumber(clientNr);
    }
}
