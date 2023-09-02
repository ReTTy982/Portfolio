package edu.pwr.pl.Lab06.app.installation;

import edu.pwr.pl.Lab06.PromptLogger;
import edu.pwr.pl.Lab06.app.charges.ChargesRepo;
import edu.pwr.pl.Lab06.app.client.Client;
import edu.pwr.pl.Lab06.app.client.ClientRepo;
import edu.pwr.pl.Lab06.app.installation.DTO.InstallationCreateRequest;
import edu.pwr.pl.Lab06.app.madepayments.MadePayments;
import edu.pwr.pl.Lab06.app.madepayments.MadePaymentsRepo;
import edu.pwr.pl.Lab06.app.tariff.Tariff;
import edu.pwr.pl.Lab06.app.tariff.TariffRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InstallationService {
    private final InstallationRepo installationRepo;
    private final ClientRepo clientRepo;
    private final TariffRepo tariffRepo;
    private final MadePaymentsRepo madePaymentsRepo;
    private final ChargesRepo chargesRepo;

    public InstallationService(InstallationRepo installationRepo, ClientRepo clientRepo, TariffRepo tariffRepo, MadePaymentsRepo madePaymentsRepo, ChargesRepo chargesRepo) {
        this.installationRepo = installationRepo;
        this.clientRepo = clientRepo;
        this.tariffRepo = tariffRepo;
        this.madePaymentsRepo = madePaymentsRepo;
        this.chargesRepo = chargesRepo;
    }

    public void createInstallation(Long clientNr,String tariffType,String address,Long routerNr) {
        Optional<Client> clientOptional = clientRepo.findClientByClientNr(clientNr);
        Optional<Tariff> tariffOptional = tariffRepo.findTariffByTariffType(tariffType);
        if (clientOptional.isPresent() && tariffOptional.isPresent()) {
            Client client = clientOptional.get();
            Tariff tariff = tariffOptional.get();
            installationRepo.save(new Installation(
                    address,
                    routerNr,
                    tariff,
                    client
            ));
        }
    }

    public void checkPayment(Installation installation, PromptLogger logger){
        Optional<List<MadePayments>> paymentsOptional = madePaymentsRepo.findMadePaymentsByInstallation(installation);
        if(paymentsOptional.isPresent()){
            List<MadePayments> payments = paymentsOptional.get();
            for (MadePayments payment : payments){
                if (payment.getAccounted() == false){
                    Float amountToPay = installation.getChargesSet().getAmountToPay();
                    installation.getChargesSet().setAmountToPay(amountToPay - payment.getPaymentAmount());
                    chargesRepo.save(installation.getChargesSet());
                    payment.setAccounted(true);
                    madePaymentsRepo.save(payment);
                    logger.logMadePayment(installation.getTariffType().getTariffType(),installation.getRouterNumber(),payment.getPaymentAmount(),installation.getChargesSet().getAmountToPay());
                }
            }
        }
        if(installation.getChargesSet().getAmountToPay() > installation.getTariffType().getPrice()){
            Float balance = installation.getChargesSet().getAmountToPay();
            installation.getChargesSet().setAmountToPay(balance + installation.getTariffType().getPrice());
            logger.logLatePaymentMessage(installation.getTariffType().getTariffType(),
                    installation.getRouterNumber(),
                    installation.getChargesSet().getAmountToPay());

        }

    }

    public void addMonthly(Installation installation){
        Float amount = installation.getTariffType().getPrice();
        Float lastAmount = installation.getChargesSet().getAmountToPay();
        installation.getChargesSet().setAmountToPay(amount+lastAmount);
        chargesRepo.save(installation.getChargesSet());
    }

    public void makePayment(Installation installation){
        madePaymentsRepo.save(new MadePayments(
                LocalDate.now(),
                45f,
                installation,
                false
        ));
    }





    public void createInstallation(InstallationCreateRequest request) {
        Optional<Client> clientOptional = clientRepo.findClientByClientNr(request.getClientNr());
        Optional<Tariff> tariffOptional = tariffRepo.findTariffByTariffType(request.getTariffType());
        if(clientOptional.isPresent() && tariffOptional.isPresent()){
            Client client = clientOptional.get();
            Tariff tariff = tariffOptional.get();
            installationRepo.save(new Installation(
                    request.getAddress(),
                    request.getRouterNumber(),
                    tariff,
                    client
            ));



        }
    }
}
