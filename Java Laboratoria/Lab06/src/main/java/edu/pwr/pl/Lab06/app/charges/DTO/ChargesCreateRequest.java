package edu.pwr.pl.Lab06.app.charges.DTO;

import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class ChargesCreateRequest {
    private LocalDate paymentDueDate;
    private Float amountToPay;
    private Long routerNr;

    public ChargesCreateRequest(LocalDate paymentDueDate, Float amountToPay, Long routerNr) {
        this.paymentDueDate = paymentDueDate;
        this.amountToPay = amountToPay;
        this.routerNr = routerNr;
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

    public Long getRouterNr() {
        return routerNr;
    }

    public void setRouterNr(Long routerNr) {
        this.routerNr = routerNr;
    }

    @Override
    public String toString() {
        return "ChargesCreateRequest{" +
                "paymentDueDate=" + paymentDueDate +
                ", amountToPay=" + amountToPay +
                ", routerNr='" + routerNr + '\'' +
                '}';
    }
}
