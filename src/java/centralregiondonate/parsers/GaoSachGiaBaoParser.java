/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.parsers;

import centralregiondonate.constants.ErrorConstant;
import centralregiondonate.constants.QNameConstant;
import centralregiondonate.dtos.TblRice;
import centralregiondonate.helpers.ValidatorHelper;
import centralregiondonate.helpers.XMLHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GaoSachGiaBaoParser {

    public String parseURL(String content) {
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        StartElement startElement;
        String url = "";
        try {

            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                    if ("a".equals(startElement.getName().toString())) {
                        String href = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE)).getValue();
                        if (href.contains("san-pham/")) {
                            url = href;
                            break;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseURL", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());

        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseURL - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return url;
    }

    public String parseCategoryURL(String content) {
        String categoryURL = "";

        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        StartElement startElement;
        XMLEvent event;

        try {
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();

                    if ("a".equals(startElement.getName().toString())) {
                        String hrefAtt = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE)).getValue();
                        if (hrefAtt.contains("gao-ngon/")) {
                            categoryURL = hrefAtt;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseCategoryURL", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseCategoryURL - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }

        return categoryURL;
    }

    public List<TblRice> parseRices(String content) {
        List<TblRice> rices = null;
        StartElement startElement;
        XMLEvent event;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);

        try {
            rices = new ArrayList<>();
            if (streamReader.hasNext()) {
                String riceName = "";
                String price = "";

                while (streamReader.hasNext()) {
                    int eventType = streamReader.next();
                    if (eventType == XMLStreamConstants.START_ELEMENT) {
                        startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                        if ("p".equals(startElement.getName().toString())) {
                            String classAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();
                            if (classAtt.contains("product-title")) {
                                streamReader.nextTag();
                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    riceName = event.asCharacters().getData();
                                    riceName = ValidatorHelper.validName(riceName);
                                }
                            }

                        }
                        if ("span".equals(startElement.getName().toString())) {
                            String classAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();
                            if (classAtt.contains("woocommerce-Price-amount")) {
                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    price = event.asCharacters().getData();
                                    price = price.replace(".", "");
                                    
                                    TblRice rice = new TblRice();
                                    rice.setRiceName(riceName);
                                    rice.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                                    rices.add(rice);
                                    
                                }
                            }
                        }
                    }
                }
            }
            
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseRicePage", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseRices - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return rices;
    }
}
