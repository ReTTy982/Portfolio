package edu.pwr.pl.Lab06.app.tariff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TariffConfig implements CommandLineRunner {
    private final TariffService tariffService;
    @Autowired
    public TariffConfig(TariffService tariffService) {
        this.tariffService = tariffService;
    }

    @Override
    public void run(String... args) throws Exception {







    }
}
