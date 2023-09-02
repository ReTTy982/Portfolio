package edu.pwr.pl.Lab06.app.madepayments;

import edu.pwr.pl.Lab06.app.installation.Installation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MadePaymentsRepo extends JpaRepository<MadePayments,Long> {

    Optional<List<MadePayments>> findMadePaymentsByInstallation(Installation installation);

    @Query("SELECT mp FROM MadePayments mp JOIN mp.installation i JOIN i.client c WHERE c.clientNr = :clientNr")
    List<MadePayments> findMadePaymentsByCustomerNumber(@Param("clientNr") Long clientNr);
}
