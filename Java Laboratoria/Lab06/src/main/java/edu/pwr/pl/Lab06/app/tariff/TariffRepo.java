package edu.pwr.pl.Lab06.app.tariff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TariffRepo extends JpaRepository<Tariff,Long> {
    Optional<Tariff> findTariffByTariffType(String tariffType);

    Optional<Tariff> findTariffById(Long id);
}
