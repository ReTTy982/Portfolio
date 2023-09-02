package edu.pwr.pl.Lab06.app.tariff;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Tariff {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String tariffType;
    private Float price;

    @OneToMany(mappedBy = "tariffType")
    @JsonIgnoreProperties("tariffType")
    private Set<Installation> installationSet;

    public Tariff(String tariffType, Float price) {
        this.tariffType = tariffType;
        this.price = price;
    }

    public Tariff(Long id, String tariffType, Float price) {
        this.id = id;
        this.tariffType = tariffType;
        this.price = price;
    }

    public Tariff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTariffType() {
        return tariffType;
    }

    public void setTariffType(String tariffType) {
        this.tariffType = tariffType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", tariffType='" + tariffType + '\'' +
                ", price=" + price +
                '}';
    }

    public Set<Installation> getInstallationSet() {
        return installationSet;
    }

    public void setInstallationSet(Set<Installation> installationSet) {
        this.installationSet = installationSet;
    }
}
