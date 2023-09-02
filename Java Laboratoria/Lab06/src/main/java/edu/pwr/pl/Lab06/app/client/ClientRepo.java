package edu.pwr.pl.Lab06.app.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client,Long> {

    Optional<Client> findClientByClientNr(Long clientNr);
    Optional<Client> findClientById(Long id);

    Client save(Client client);

}
