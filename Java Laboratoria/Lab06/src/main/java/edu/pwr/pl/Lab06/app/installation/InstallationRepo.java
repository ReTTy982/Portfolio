package edu.pwr.pl.Lab06.app.installation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstallationRepo extends JpaRepository<Installation,Long> {
    Optional<Installation> findInstallationByRouterNumber(Long routerNumber);
}
