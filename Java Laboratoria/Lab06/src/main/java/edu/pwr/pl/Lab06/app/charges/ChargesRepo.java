package edu.pwr.pl.Lab06.app.charges;

import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.madepayments.MadePayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChargesRepo extends JpaRepository<Charges,Long> {

    Optional<Charges> findChargesByInstallation(Installation installation);

    @Query("SELECT c FROM Charges c JOIN c.installation i JOIN i.client cl WHERE cl.clientNr = :clientNr")
    List<Charges> findChargesByCustomerNumber(@Param("clientNr") Long clientNr);


}
