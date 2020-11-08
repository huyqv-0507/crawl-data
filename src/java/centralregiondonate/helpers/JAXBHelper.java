/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;

import com.sun.codemodel.JCodeModel;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 *
 * @author Huy Nguyen
 */
public class JAXBHelper {

    public void parseJAXB(String schemaPath) {
         try {
            String output = "src/java/";
            SchemaCompiler sc = XJC.createSchemaCompiler();
            sc.setErrorListener(new ErrorListener() {
                @Override
                public void error(SAXParseException saxpe) {
                    System.out.println("error: " + saxpe.getMessage());
                }

                @Override
                public void fatalError(SAXParseException saxpe) {
                    System.out.println("fatalError: " + saxpe.getMessage());
                }

                @Override
                public void warning(SAXParseException saxpe) {
                    System.out.println("warning: " + saxpe.getMessage());
                }

                @Override
                public void info(SAXParseException saxpe) {
                    System.out.println("info: " + saxpe.getMessage());
                }
            });
            sc.forcePackageName("centralregiondonate.jaxbmodels");
            //sc.forcePackageName("C:\\Users\\Huy Nguyen\\Documents\\NetBeansProjects\\CRDWS\\src\\java\\crdws\\entities");
            File schema = new File(schemaPath);
            InputSource is = new InputSource(schema.toURI().toString());
            sc.parseSchema(is);
            S2JJAXBModel model = sc.bind();
            JCodeModel jCodeModel = model.generateCode(null, null);
            jCodeModel.build(new File(output));
            System.out.println("Finished");
        } catch (IOException e) {
             System.out.println("IOException - " + e.getMessage());
        }
    }
    //Mapping Object to XML
    public <T> void marshal(T t, String fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            File file = new File("C:\\Users\\Huy Nguyen\\Documents\\NetBeansProjects\\CentralRegionDonate\\src\\java\\centralregiondonate\\xml\\" + fileName);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); //If not, default is UTF-8
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); //XML data is formatted with linefeeds and indentation.
            marshaller.marshal(t, file);
        } catch (JAXBException ex) {
            System.out.println("JAXBException - " + ex.getMessage());
        }
    }
    //Mapping XML to Object
    public void unmarshal(String xmlFilePath) 
            throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance();
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
    }
    
    public static void main(String[] args) {
        JAXBHelper helper = new JAXBHelper();
        //helper.parseJAXB("src/java/centralregiondonate/xml/medicines.xsd");
        //helper.parseJAXB("src/java/centralregiondonate/xml/noodles.xsd");
        helper.parseJAXB("src/java/centralregiondonate/xml/provinces.xsd");
    }
}
