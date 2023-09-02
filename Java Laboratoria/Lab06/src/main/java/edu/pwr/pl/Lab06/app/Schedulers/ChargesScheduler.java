package edu.pwr.pl.Lab06.app.Schedulers;

import edu.pwr.pl.Lab06.PromptLogger;
import edu.pwr.pl.Lab06.app.charges.Charges;
import edu.pwr.pl.Lab06.app.charges.ChargesService;
import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.installation.InstallationRepo;
import edu.pwr.pl.Lab06.app.installation.InstallationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
@Component
public class ChargesScheduler {
    private final InstallationRepo installationRepo;
    private final ChargesService chargesService;
    private PromptLogger promptLogger;
    private final InstallationService installationService;

    @Autowired
    public ChargesScheduler(InstallationRepo installationRepo, ChargesService chargesService, InstallationService installationService) {
        this.installationRepo = installationRepo;
        this.chargesService = chargesService;
        this.installationService = installationService;
        this.promptLogger = new PromptLogger();
    }

    @Scheduled(fixedRate = 20000) // 20 seconds
    public void processCharges(){
        List<Installation> installationList = installationRepo.findAll();

        for (Installation installation : installationList){
            Charges charges = installation.getChargesSet();
            if (charges != null){

                installationService.checkPayment(installation,promptLogger);


            }


        }

    }
    @Scheduled(fixedRate =  10000)
    public void makePayment(){
        List<Installation> installationList = installationRepo.findAll();
        Random random = new Random();
        int randomIndex = random.nextInt(installationList.size());
        Installation randomElement = installationList.get(randomIndex);
        installationService.makePayment(randomElement);

    }

    @Scheduled(fixedRate = 10000)
    public void monthlyCheck(){
        promptLogger.monthlyAdd();
        List<Installation> installationList = installationRepo.findAll();
        for(Installation installation : installationList){
            installationService.addMonthly(installation);
        }
    }


    @GetMapping("/log")
    public List<String> getLog(){
        return promptLogger.getLog();
    }
}
