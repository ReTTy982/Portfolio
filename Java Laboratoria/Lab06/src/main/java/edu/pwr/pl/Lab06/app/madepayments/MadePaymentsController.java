package edu.pwr.pl.Lab06.app.madepayments;

import edu.pwr.pl.Lab06.app.madepayments.DTO.MadePaymentsCreateRequest;
import edu.pwr.pl.Lab06.app.madepayments.DTO.MadePaymentsGetRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Controller
@RequestMapping(path = "api/madepayments")
public class MadePaymentsController {

    private final MadePaymentsService madePaymentsService;

    @Autowired
    public MadePaymentsController(MadePaymentsService madePaymentsService){
        this.madePaymentsService = madePaymentsService;
    }

    @PostMapping("/new")
    public void createMadePayment(@RequestBody MadePaymentsCreateRequest request){
        madePaymentsService.createMadePayment(request);
    }


    @GetMapping("/getByClient")
    public List<MadePaymentsGetRequest> getMadePaymentByClient(@RequestParam Long clientNr) {
        List<MadePayments> madePaymentsList = madePaymentsService.getByClient(clientNr);
        List<MadePaymentsGetRequest> dtoList = new ArrayList<>();

        for (MadePayments madePayments : madePaymentsList) {
            MadePaymentsGetRequest dto = new MadePaymentsGetRequest();
            dto.mapObject(madePayments);
            System.out.println(dto.getPaymentAmount());
            dtoList.add(dto);
        }

        return dtoList;
    }


}
