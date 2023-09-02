package edu.pwr.pl.Lab06.app.installation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.pl.Lab06.app.charges.Charges;
import edu.pwr.pl.Lab06.app.client.Client;
import edu.pwr.pl.Lab06.app.madepayments.MadePayments;
import edu.pwr.pl.Lab06.app.tariff.Tariff;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table
public class Installation {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String address;
    private Long routerNumber;

    @ManyToOne
    @JoinColumn(name = "tariffId")
    @JsonIgnoreProperties("installationSet")
    private Tariff tariffType;



    @ManyToOne
    @JoinColumn(name = "clientId")
    @JsonIgnoreProperties("installations")
    private Client client;
    @OneToMany(mappedBy = "installation", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("installation")
    private Set<MadePayments> paymentsSet;

    @OneToOne(mappedBy = "installation")
    @JsonIgnoreProperties("installation")
    private Charges charges;

    public Installation(String address, Long routerNumber, Tariff tariffType, Client client) {
        this.address = address;
        this.routerNumber = routerNumber;
        this.tariffType = tariffType;
        this.client = client;
    }

    public Installation() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(Long routerNumber) {
        this.routerNumber = routerNumber;
    }


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Tariff getTariffType() {
        return tariffType;
    }

    public void setTariffType(Tariff tariffType) {
        this.tariffType = tariffType;
    }

    public Set<MadePayments> getPaymentsSet() {
        return paymentsSet;
    }

    public void setPaymentsSet(Set<MadePayments> paymentsSet) {
        this.paymentsSet = paymentsSet;
    }

    public Charges getChargesSet() {
        return charges;
    }

    public void setChargesSet(Charges chargesSet) {
        this.charges = chargesSet;
    }
}
