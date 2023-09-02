package edu.pwr.Parsers;

import edu.pwr.Models.Person;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import edu.pwr.Models.BipPoznan;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class MyMarshaller {

    public static BipPoznan unmarshal() throws FileNotFoundException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(BipPoznan.class);
        BipPoznan data = (BipPoznan) context.createUnmarshaller().unmarshal(new FileReader("./src/main/resources/data.xml"));
        return data;
    }
}
