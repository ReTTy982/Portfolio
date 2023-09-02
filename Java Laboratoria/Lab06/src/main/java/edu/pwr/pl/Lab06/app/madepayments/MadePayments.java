package edu.pwr.pl.Lab06.app.madepayments;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.pwr.pl.Lab06.app.installation.Installation;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class MadePayments {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private LocalDate paymentDate;
    private Float paymentAmount;
    private Boolean accounted;
    @ManyToOne
    @JsonIgnoreProperties("paymentsSet")
    private Installation installation;

    public MadePayments() {
    }

    public MadePayments(LocalDate paymentDate, Float paymentAmount, Installation installation, Boolean accounted) {
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.installation = installation;
        this.accounted = accounted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Installation getInstallation() {
        return installation;
    }

    public void setInstallation(Installation installation) {
        this.installation = installation;
    }

    public Boolean getAccounted() {
        return accounted;
    }

    public void setAccounted(Boolean accounted) {
        this.accounted = accounted;
    }

    @Override
    public String toString() {
        return "MadePayments{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", paymentAmount=" + paymentAmount +
                ", installation=" + installation +
                '}';
    }
}
