package edu.pwr.Models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class InformationCard {
    @XmlElement()
    protected String link;
    @XmlElement
    protected String id;
    @XmlElement(name = "data")
    protected Date date;
    @XmlElement(name = "skrot_organizacja")
    protected String organizationAbbreviation;
    @XmlElement(name = "komponent_srodowiska")
    protected String environmentalComponent;
    @XmlElement(name = "typ_karty")
    protected String cardModel;
    @XmlElement(name = "rodzaj_karty")
    protected String cardType;
    @XmlElement(name = "nr_wpisu")
    protected int reportNumber;
    @XmlElement(name = "znak_sprawy")
    protected String caseNumber;
    @XmlElement(name = "dane_wnioskodawcy")
    protected String applicantData;

    public void setDate(String date) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.date = format.parse(date);
    }


    public InformationCard(String link, String id, String date, String organizationAbbreviation, String environmentalComponent, String cardModel, String cardType, int reportNumber, String caseNumber, String applicantData) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        this.link = link;
        this.id = id;
        this.date = format.parse(date);
        this.organizationAbbreviation = organizationAbbreviation;
        this.environmentalComponent = environmentalComponent;
        this.cardModel = cardModel;
        this.cardType = cardType;
        this.reportNumber = reportNumber;
        this.caseNumber = caseNumber;
        this.applicantData = applicantData;
    }
    public InformationCard(){}
}
