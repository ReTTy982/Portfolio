package edu.pwr.pl.Lab06.app.madepayments.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.pwr.pl.Lab06.app.client.Client;
import edu.pwr.pl.Lab06.app.installation.Installation;
import edu.pwr.pl.Lab06.app.madepayments.MadePayments;

import java.time.LocalDate;

public class MadePaymentsGetRequest {
        @JsonProperty("id")
        private Long id;
        @JsonProperty("paymentDate")
        private LocalDate paymentDate;
        @JsonProperty("paymentAmount")
        private Float paymentAmount;
        @JsonProperty("accounted")
        private Boolean accounted;
        @JsonProperty("routerNumber")
        private Long routerNumber;
        @JsonProperty("clientNr")
        private Long clientNr;

        public MadePaymentsGetRequest() {
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

        public Boolean getAccounted() {
                return accounted;
        }

        public void setAccounted(Boolean accounted) {
                this.accounted = accounted;
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

        public void mapObject(MadePayments madePayments) {
                setId(madePayments.getId());
                setPaymentDate(madePayments.getPaymentDate());
                setPaymentAmount(madePayments.getPaymentAmount());
                setAccounted(madePayments.getAccounted());

                Installation installation = madePayments.getInstallation();
                setRouterNumber(installation.getRouterNumber());

                Client client = installation.getClient();
                setClientNr(client.getClientNr());


        }
}
