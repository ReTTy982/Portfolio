package edu.pwr.Parsers;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;

public class MyTransformer {


    public static void transform(String xmlFilePath, String xsltFilePath){
        try {
            Source xmlSource = new StreamSource(xmlFilePath);
            Source xsltSource = new StreamSource(xsltFilePath);

            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(xsltSource);

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);

            transformer.transform(xmlSource, result);
            String htmlOutput = writer.toString();

            FileWriter fileWriter = new FileWriter("./test.html");
            fileWriter.write(htmlOutput);
            fileWriter.close();
            Desktop.getDesktop().browse(new File("test.html").toURI());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
