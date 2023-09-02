package edu.pwr.pl.Lab06;

import edu.pwr.pl.Lab06.app.charges.ChargesService;
import edu.pwr.pl.Lab06.app.client.Client;
import edu.pwr.pl.Lab06.app.client.ClientService;
import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.installation.InstallationService;
import edu.pwr.pl.Lab06.app.madepayments.MadePaymentsService;
import edu.pwr.pl.Lab06.app.tariff.Tariff;
import edu.pwr.pl.Lab06.app.tariff.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabasePopulate implements CommandLineRunner {

    private final TariffService tariffService;
    private final ClientService clientService;
    private final MadePaymentsService madePaymentsService;
    private final InstallationService installationService;
    private final ChargesService chargesService;
    @Autowired
    public DatabasePopulate(TariffService tariffService, ClientService clientService, MadePaymentsService madePaymentsService, InstallationService installationService, ChargesService chargesService) {
        this.tariffService = tariffService;
        this.clientService = clientService;
        this.madePaymentsService = madePaymentsService;
        this.installationService = installationService;
        this.chargesService = chargesService;
    }

    @Override
    public void run(String... args) throws Exception {
        populateClients();
        populateTariff();
        populateInstallation();
        populateCharges();
        populateMadePayments();


    }


    private void populateClients(){
        Client clientA = new Client("Paul","Paczynski", 123L);
        Client clientB = new Client("Jan", "kowalski", 312L);
        Client clientC = new Client("Borys", "Johnson",679L);
        clientService.createClient(clientA);
        clientService.createClient(clientB);

        clientService.createClient(clientC);


    }

    private void populateTariff(){
        Tariff tariffA = new Tariff("A", 25.25f);
        Tariff tariffB = new Tariff("B", 13.10f);
        Tariff tariffC = new Tariff("C",50.25f);
        tariffService.createTariff(tariffA);
        tariffService.createTariff(tariffB);
        tariffService.createTariff(tariffC);

    }
    private void populateMadePayments(){
        madePaymentsService.createMadePayment(25f,5555L);
        madePaymentsService.createMadePayment(30f,1111L);
        madePaymentsService.createMadePayment(12f,2222L);
        madePaymentsService.createMadePayment(5f,333L);
        madePaymentsService.createMadePayment(4f,4444L);
        madePaymentsService.createMadePayment(11f,6666L);

    }

    private void populateInstallation(){
        installationService.createInstallation(123L,"A","Inflancka",5555L);
        installationService.createInstallation(123L,"B","Poleska",1111L);
        installationService.createInstallation(312L,"C","Zmudzka",2222L);
        installationService.createInstallation(312L,"A","Kielczowksa",333L);
        installationService.createInstallation(679L,"B","Lawendowa",4444L);
        installationService.createInstallation(679L,"C","Polna",6666L);

    }

    private void populateCharges(){
        chargesService.createCharge(LocalDate.now(),5555L);
        chargesService.createCharge(LocalDate.now(),1111L);
        chargesService.createCharge(LocalDate.now(),2222L);
        chargesService.createCharge(LocalDate.now(),333L);
        chargesService.createCharge(LocalDate.now(),4444L);
        chargesService.createCharge(LocalDate.now(),6666L);

    }
}
