package edu.pwr.pl.Lab06;

import java.util.ArrayList;
import java.util.List;

public class PromptLogger {
    private List<String> log;

    public List<String> getLog() {
        return log;
    }

    public PromptLogger() {
        this.log = new ArrayList<>();
    }


    public void logLatePaymentMessage(String tariffType,Long routerNr,Float amountToPay){

        StringBuilder builder = new StringBuilder();
        builder.append("Usluga: ");
        builder.append(tariffType + " ");
        builder.append(", dla routera: ");
        builder.append(routerNr + " ");
        builder.append("nie zostala oplacona w czasie. Nowa zaleznosc: ");
        builder.append(amountToPay);
        log.add(builder.toString());
    }

    public void logMadePayment(String tariffType, Long routerNr, Float amountPayed, Float balance){
        StringBuilder builder = new StringBuilder();
        builder.append("Usluga: ");
        builder.append(tariffType+" ");
        builder.append(", dla routera: ");
        builder.append(routerNr + " ");
        builder.append("została opłacona w wysokości: ");
        builder.append(amountPayed + " ,");
        builder.append("Balans: " + balance);
        log.add(builder.toString());
    }

    public void monthlyAdd(){
        log.add("DODANO NALEZNOSCI");
    }

}
