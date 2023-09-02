package edu.pwr.pl.Lab06.app.madepayments;

import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.installation.InstallationRepo;
import edu.pwr.pl.Lab06.app.madepayments.DTO.MadePaymentsCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MadePaymentsService {
    private final MadePaymentsRepo madePaymentsRepo;
    private final InstallationRepo installationRepo;

    @Autowired
    public MadePaymentsService(MadePaymentsRepo madePaymentsRepo, InstallationRepo installationRepo){
        this.madePaymentsRepo = madePaymentsRepo;
        this.installationRepo = installationRepo;
    }

    public void createMadePayment(MadePaymentsCreateRequest request) {
        Optional<Installation> installationOptional = installationRepo.findInstallationByRouterNumber(request.getRouterNr());
        if (installationOptional.isPresent()){
            Installation installation = installationOptional.get();
            madePaymentsRepo.save(new MadePayments(
                    request.getPaymentDate(),
                    request.getPaymentAmount(),
                    installation,
                    false
            ));
        }
    }

    public void createMadePayment(Float paymentAmount,Long routerNr){
        Installation installation = installationRepo.findInstallationByRouterNumber(routerNr).get();
        madePaymentsRepo.save(new MadePayments(LocalDate.now(),paymentAmount,installation,false));
    }

    public List<MadePayments> getByClient(Long clientNr) {
        return madePaymentsRepo.findMadePaymentsByCustomerNumber(clientNr);

    }
}
