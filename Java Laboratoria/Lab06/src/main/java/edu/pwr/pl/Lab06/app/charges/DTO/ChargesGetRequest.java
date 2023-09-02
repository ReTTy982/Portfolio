package edu.pwr.pl.Lab06.app.charges.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pl.Lab06.app.charges.Charges;
import edu.pwr.pl.Lab06.app.client.Client;
import edu.pwr.pl.Lab06.app.installation.Installation;

import java.time.LocalDate;

public class ChargesGetRequest {
    public Long getId() {
        return id;
    }

    public ChargesGetRequest() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Float getAmountToPay() {
        return amountToPay;
    }

    public void setAmountToPay(Float amountToPay) {
        this.amountToPay = amountToPay;
    }

    public Long getRouterNumber() {
        return routerNumber;
    }

    public void setRouterNumber(Long routerNumber) {
        this.routerNumber = routerNumber;
    }

    public Long getClientNr() {
        return clientNr;
    }

    public void setClientNr(Long clientNr) {
        this.clientNr = clientNr;
    }

    @JsonProperty("id")
    private Long id;
    @JsonProperty("paymentDueDate")
    private LocalDate paymentDueDate;
    @JsonProperty("amountToPay")
    private Float amountToPay;
    @JsonProperty("routerNumber")
    private Long routerNumber;
    @JsonProperty("clientNr")
    private Long clientNr;

    public void mapObject(Charges charges){
        setId(charges.getId());
        setPaymentDueDate(charges.getPaymentDueDate());
        setAmountToPay(charges.getAmountToPay());
        Installation installation = charges.getInstallation();

        setRouterNumber(installation.getRouterNumber());

        Client client = installation.getClient();
        setClientNr(client.getClientNr());
    }
}
