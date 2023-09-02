package edu.pwr.Parsers;

import edu.pwr.Models.InformationCard;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class InformationCardHandler extends DefaultHandler {
    private final  List<InformationCard> informationCardList = new ArrayList<>();
    private InformationCard informationCard = null;
    boolean bLink = false;
    boolean bId = false;
    boolean bDate = false;
    boolean bOrganizationAbbreviation = false;
    boolean bEnvironmentalComponent = false;
    boolean bCardModel = false;
    boolean bCardType = false;
    boolean bReportNumber = false;
    boolean bCaseNumber = false;
    boolean bApplicantData = false;

    boolean dataDummy = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "karta_informacyjna" -> informationCard = new InformationCard();
            case "link" -> bLink = true;
            case "id" -> bId = true;
            case "data" -> {
                if (dataDummy) { // For catching english "data", when we want to use Polish word data
                    bDate = true;
                } else {
                    dataDummy = true;
                }
            }
            case "skrot_organizacja" -> bOrganizationAbbreviation = true;
            case "komponent_srodowiska" -> bEnvironmentalComponent = true;
            case "typ_karty" -> bCardModel = true;
            case "rodzaj_karty" -> bCardType = true;
            case "nr_wpisu" -> bReportNumber = true;
            case "znak_sprawy" -> bCaseNumber = true;
            case "dane_wnioskodawcy" -> bApplicantData = true;
        }
    }


    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("karta_informacyjna")) {
            informationCardList.add(informationCard);
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if(bLink){
            informationCard.setLink(new String(ch,start,length));
            bLink = false;
        }else if(bId){
            informationCard.setId(new String(ch,start,length));
            bId = false;
        }else if(bDate){

            try {
                informationCard.setDate(new String(ch,start,length));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            bDate = false;
        }else if(bOrganizationAbbreviation){
            informationCard.setOrganizationAbbreviation(new String(ch,start,length));
            bOrganizationAbbreviation = false;
        }else if(bEnvironmentalComponent){
            informationCard.setEnvironmentalComponent(new String(ch,start,length));
            bEnvironmentalComponent = false;
        }else if(bCardModel){
            informationCard.setCardModel(new String(ch,start,length));
            bCardModel = false;
        }else if(bCardType){
            informationCard.setCardType(new String(ch,start,length));
            bCardType = false;
        }else if(bReportNumber){
            informationCard.setReportNumber(Integer.parseInt(new String(ch,start,length)));
            bReportNumber = false;
        }else if(bCaseNumber){
            informationCard.setCaseNumber(new String(ch,start,length));
            bCaseNumber = false;
        }else if(bApplicantData){
            informationCard.setApplicantData(new String(ch,start,length));
            bApplicantData = false;
        }
    }

    public List<InformationCard> getList(){
        return this.informationCardList;
    }

}
