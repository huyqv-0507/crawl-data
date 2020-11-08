/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;


import centralregiondonate.constants.ErrorConstant;
import com.sun.xml.internal.stream.events.XMLEventAllocatorImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.util.XMLEventAllocator;
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
public class XMLHelper {
    public static XMLStreamReader parseToCursorByStAX(String document) {
        XMLStreamReader reader = null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        InputStream inputStream = StringHelper.toInputStream(document);
        try {
            reader = factory.createXMLStreamReader(inputStream);
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(XMLHelper.class.getName(), "parseToCursorByStAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        }
        return reader;
    }
    
    public static XMLEventReader convertCursorToIterator(XMLStreamReader reader) {
        XMLEventReader eventReader = null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        try {
            eventReader = factory.createXMLEventReader(reader);
        } catch (XMLStreamException ex) {
            Logger.getLogger(XMLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return eventReader;
    }
    
    public static XMLEvent getXMLEvent(XMLStreamReader reader) {
        XMLEvent event = null;
        XMLInputFactory factory = XMLInputFactory.newFactory();
        factory.setEventAllocator(new XMLEventAllocatorImpl());
        
        XMLEventAllocator allocator = factory.getEventAllocator();
        try {
            event = allocator.allocate(reader);
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(XMLHelper.class.getName(), "convertCursorToIterator", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        }
        return event;
    }
    public static void validateXML(String xmlFilePath, String xsdFilePath) 
            throws SAXException, FileNotFoundException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdFilePath));
        InputSource source = new InputSource(new BufferedReader(new FileReader(xmlFilePath)));
        Validator validator = (Validator) schema.newValidator();
        validator.validate(new SAXSource(source));
    }
}
