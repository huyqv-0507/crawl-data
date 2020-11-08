/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.parsers;

import centralregiondonate.constants.ErrorConstant;
import centralregiondonate.constants.QNameConstant;
import centralregiondonate.constants.URLConstant;
import centralregiondonate.dtos.TblNoodle;
import centralregiondonate.helpers.XMLHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Huy Nguyen
 */
public class OkfoodParser {

    public String parseCategoryURL(String content) {
        StartElement startElement;
        String categoryURL = "";
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        try {
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                    if ("a".equals(startElement.getName().toString())) {
                        String hrefAtt = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE)).getValue();
                        if (hrefAtt.equals("/mi-goi")) {
                            categoryURL = URLConstant.OKFOOD_ROOT + hrefAtt;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(OkfoodParser.class.getName(), "parseCategoryURL", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(OkfoodParser.class.getName(), "parseCategoryURL - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return categoryURL;
    }

    public List<TblNoodle> parseNoodles(String content) {
        List<TblNoodle> noodles = null;
        StartElement startElement;
        XMLEvent event;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        try {
            if (streamReader.hasNext()) {
                if (noodles == null) {
                    noodles = new ArrayList<>();
                }
                String noodleName = "";
                String price = "";
                
                while (streamReader.hasNext()) {
                    int eventType = streamReader.next();
                    if (eventType == XMLStreamConstants.START_ELEMENT) {
                        startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                        if ("p".equals(startElement.getName().toString())) {
                            String classAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();
                            if (classAtt.equals("special-price")) {
                                streamReader.nextTag();
                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    String tmpPrice = event.asCharacters().getData();
                                    tmpPrice = tmpPrice.replace(".", "");
                                    price = tmpPrice.replaceAll("₫", "");
                                    
                                }
                            }
                        }
                        if ("h3".equals(startElement.getName().toString())) {
                            streamReader.next();
                            event =  XMLHelper.getXMLEvent(streamReader);
                            if (event.isCharacters()) {
                                noodleName = event.asCharacters().getData();
                                
                                TblNoodle noodle = new TblNoodle();
                                noodle.setNoodleName(noodleName);
                                noodle.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                                noodles.add(noodle);
                            }
                        }
                    }
                }
            }
            
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseNoodles", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        }
        finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(OkfoodParser.class.getName(), "parseNoodles - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return noodles;
    }
}
