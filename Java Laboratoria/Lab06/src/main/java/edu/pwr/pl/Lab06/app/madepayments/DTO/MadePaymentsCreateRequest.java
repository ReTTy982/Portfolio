package edu.pwr.pl.Lab06.app.madepayments.DTO;

import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

public class MadePaymentsCreateRequest {
    private LocalDate paymentDate;
    private Float paymentAmount;
    private Long routerNr;


    public MadePaymentsCreateRequest(LocalDate paymentDate, Float paymentAmount, Long routerNr) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.routerNr = routerNr;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Float getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }


    public Long getRouterNr() {
        return routerNr;
    }

    public void setRouterNr(Long routerNr) {
        this.routerNr = routerNr;
    }

    @Override
    public String toString() {
        return "MadePaymentsCreateRequest{" +
                "paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", routerNr=" + routerNr +
                '}';
    }
}
