/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;

import centralregiondonate.constants.ErrorConstant;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.transform.sax.SAXSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Huy Nguyen
 */
public class ValidatorHelper {
    //Valid data XML before insert DB
    public static boolean validDataByXSD(String xmlFilePath, String xsdFilePath) throws SAXException {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdFilePath));
            InputSource source = new InputSource(new BufferedReader(new FileReader(xmlFilePath)));
            Validator validator = (Validator) schema.newValidator();
            System.out.println("validator: " + validator);
            try {
                validator.validate(new SAXSource(source));
                return true;
            } catch (IOException ex) {
                ErrorConstant.getErrorMsg(ValidatorHelper.class.getName(), "validData", ErrorConstant.IO_EXCEPTION, ex.getMessage());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ValidatorHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    //Rice
    public static String validName(String name) {
        name = name.replaceAll("GẠO", "").trim();
        String result  = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
        return result;
    }
}
