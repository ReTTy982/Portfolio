package edu.pwr.pl.Lab06.app.charges;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.*;

import java.time.LocalDate;

@Table
@Entity
public class Charges {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate paymentDueDate;


    private Float amountToPay;
    @ManyToOne
    @JsonIgnoreProperties("charges")
    private Installation installation;

    public Charges(LocalDate paymentDueDate, Float amountToPay, Installation installation) {
        this.paymentDueDate = paymentDueDate;
        this.amountToPay = amountToPay;
        this.installation = installation;
    }

    public Charges() {
    }


    public Long getId() {
        return id;
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

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    @Override
    public String toString() {
        return "Charges{" +
                "id=" + id +
                ", paymentDueDate=" + paymentDueDate +
                ", amountToPay=" + amountToPay +
                ", installation=" + installation +
                '}';
    }
}
