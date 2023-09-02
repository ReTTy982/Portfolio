package edu.pwr.pl.Lab06.app.client;

import edu.pwr.pl.Lab06.app.client.DTO.ClientRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepo clientRepo;

    @Autowired
    public ClientService(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    public Client searchForClient(Long clientNr) {
        Optional<Client> clientCheck = clientRepo.findClientByClientNr(clientNr);
        if (clientCheck.isPresent()){
            Client client = clientCheck.get();
            return client;
        }
        return null;
    }

    public void createClient(String name, String surname) {

    }

    public void createClient(String name, String surname, Long clientNr) {
        System.out.println(clientNr);
        System.out.println(clientRepo.findClientByClientNr(clientNr));
        if (clientRepo.findClientByClientNr(clientNr).isEmpty()) {
            clientRepo.save(new Client(
                    name,
                    surname,
                    clientNr
            ));
            System.out.println("test");
        }
    }

    public void createClient(Client client) {
        if (clientRepo.findClientByClientNr(client.getClientNr()).isEmpty()) {
            clientRepo.save(new Client(
                    client.getName(),
                    client.getSurname(),
                    client.getClientNr()
            ));
        }
    }

    public void updateClient(ClientRequest request) {
        Optional<Client> clientOptional  = clientRepo.findClientById(request.getId());
        if(clientOptional.isPresent()){
            Client client = clientOptional.get();
            if (request.getName() != null) {
                client.setName(request.getName());
            }
            if (request.getSurname() != null) {
                client.setSurname(request.getSurname());
            }
            if (request.getClientNr() != null) {
                client.setClientNr(request.getClientNr());
            }
            clientRepo.save(client);

        }
    }
}
