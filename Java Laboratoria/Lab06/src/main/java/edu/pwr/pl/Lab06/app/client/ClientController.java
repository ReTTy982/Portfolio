package edu.pwr.pl.Lab06.app.client;

import edu.pwr.pl.Lab06.app.client.DTO.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/client")
@Controller
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    public Client searchByNumber(Long number){
        return clientService.searchForClient(number);
    }

    public void createNewClient(String name, String surname, Long clientNr){
        clientService.createClient(name,surname,clientNr);
    }

    @PostMapping("/test")
    public void createClient(@RequestBody Client client) {
        clientService.createClient(client);
    }

    @PostMapping("/update")
    public void updateClient(@RequestBody ClientRequest request){clientService.updateClient(request);}



}
