package edu.pwr.Parsers;

import edu.pwr.Models.InformationCard;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SaxParser {
    private SAXParserFactory saxParserFactory;
    private SAXParser saxParser;
    private InformationCardHandler informationCardHandler;


    public SaxParser() throws ParserConfigurationException, SAXException {
        this.saxParserFactory = SAXParserFactory.newInstance();
        this.saxParser = saxParserFactory.newSAXParser();
        this.informationCardHandler = new InformationCardHandler();
    }

    public List<InformationCard> getData(String filePath) throws IOException, SAXException {
        File file = new File(filePath);
        saxParser.parse(file,informationCardHandler);
        return informationCardHandler.getList();

    }

    public void printData(List<InformationCard> informationCardList) throws ParseException {
        for (int i = 0; i<informationCardList.size(); i++){
            InformationCard informationCard = informationCardList.get(i);
            System.out.println(informationCard.toString());

        }
    }


}
