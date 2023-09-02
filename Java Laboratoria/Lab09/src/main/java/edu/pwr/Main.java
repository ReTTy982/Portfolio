package edu.pwr;

import edu.pwr.Models.BipPoznan;
import edu.pwr.Parsers.DomParser;
import edu.pwr.Parsers.MyMarshaller;
import edu.pwr.Parsers.MyTransformer;
import edu.pwr.Parsers.SaxParser;
import jakarta.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JAXBException, ParseException {
        DomParser domParser = new DomParser();
        SaxParser saxParser = new SaxParser();
        var saxData = saxParser.getData("src/main/resources/data.xml");
        var domData = domParser.getData("src/main/resources/data.xml");
        BipPoznan data =  MyMarshaller.unmarshal();

        MyTransformer.transform("src/main/resources/data.xml","src/main/resources/formater.xslt");


        // Wypisywanie
        //saxParser.printData(saxData);
        //domParser.printData(domData);
        //System.out.println(data.getData().getInformationCards().getItems().getInformationCardList().get(0));
    }


}
