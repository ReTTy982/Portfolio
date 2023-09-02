package edu.pwr.Parsers;


import edu.pwr.Models.InformationCard;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DomParser {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;

    public DomParser() throws ParserConfigurationException {
        this.factory = DocumentBuilderFactory.newInstance();
        this.builder = this.factory.newDocumentBuilder();
    }

    public List<Element> getData(String filePath) throws IOException, SAXException {
        List<Element> elementList = new ArrayList<>();
        File file = new File(filePath);

        Document doc = builder.parse(file);
        doc.getDocumentElement().normalize();
        NodeList nodes = doc.getElementsByTagName("karta_informacyjna");

        for(int i = 0;  i < nodes.getLength(); i++){
            Node node = nodes.item(i);
            if(node.getNodeType() == Node.ELEMENT_NODE){
                Element element = (Element) node;
                elementList.add(element);
            }

        }
        return elementList;
    }

    public void printData(List<Element> elements) throws ParseException {
        for (int i = 0; i<elements.size(); i++){
            InformationCard informationCard = this.cardBuilder(elements.get(i));

            System.out.println(informationCard.toString());
        }
    }

    public InformationCard cardBuilder(Element element) throws ParseException {
        return new InformationCard(
        element.getElementsByTagName("link").item(0).getTextContent(),
        element.getElementsByTagName("id").item(0).getTextContent(),
        element.getElementsByTagName("data").item(0).getTextContent(),
        element.getElementsByTagName("skrot_organizacja").item(0).getTextContent(),
        element.getElementsByTagName("komponent_srodowiska").item(0).getTextContent(),
        element.getElementsByTagName("typ_karty").item(0).getTextContent(),
        element.getElementsByTagName("rodzaj_karty").item(0).getTextContent(),
        Integer.parseInt(element.getElementsByTagName("nr_wpisu").item(0).getTextContent()),
        element.getElementsByTagName("znak_sprawy").item(0).getTextContent(),
        element.getElementsByTagName("dane_wnioskodawcy").item(0).getTextContent()
        );



    }

}
