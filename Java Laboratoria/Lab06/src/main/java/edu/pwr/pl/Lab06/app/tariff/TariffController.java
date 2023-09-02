package edu.pwr.pl.Lab06.app.tariff;

import edu.pwr.pl.Lab06.app.tariff.DTO.TariffUpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/tariff")
@Controller
public class TariffController {

    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService){this.tariffService=tariffService;}

    @PostMapping(path = "/new")
    public void newTariff(@RequestBody Tariff tariff){
        tariffService.createTariff(tariff);
    }

    @PostMapping(path="/update")
    public void updateTariff(@RequestBody TariffUpdateRequest request){tariffService.updateTariff(request);}



}
