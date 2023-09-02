package edu.pwr.pl.Lab06.app.charges;

import edu.pwr.pl.Lab06.app.charges.DTO.ChargesCreateRequest;
import edu.pwr.pl.Lab06.app.charges.DTO.ChargesGetRequest;
import edu.pwr.pl.Lab06.app.madepayments.DTO.MadePaymentsGetRequest;
import edu.pwr.pl.Lab06.app.madepayments.MadePayments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/charges")
@Controller
public class ChargesController {
    private final ChargesService chargesService;

    @Autowired
    public ChargesController(ChargesService chargesService){
        this.chargesService = chargesService;
    }

    @PostMapping("/new")
    public void createCharge(@RequestBody ChargesCreateRequest request){
        chargesService.createCharge(request);
    }
    @GetMapping("/getByClient")
    public List<ChargesGetRequest> getChargesByClient(@RequestParam Long clientNr) {
        List<Charges> chargesList = chargesService.getByClient(clientNr);
        List<ChargesGetRequest> dtoList = new ArrayList<>();

        for (Charges charges : chargesList) {
            ChargesGetRequest dto = new ChargesGetRequest();
            dto.mapObject(charges);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
