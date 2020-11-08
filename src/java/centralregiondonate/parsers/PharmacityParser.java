/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.parsers;

import centralregiondonate.constants.ErrorConstant;
import centralregiondonate.constants.QNameConstant;
import centralregiondonate.constants.URLConstant;
import centralregiondonate.dtos.TblMedicine;
import centralregiondonate.helpers.XMLHelper;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Huy Nguyen
 */
public class PharmacityParser {

    //Parse HTML Content to List<Medicine>
    public List<TblMedicine> parsePageMedicine(String content) {
        List<TblMedicine> medicines = null;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        StartElement startElement;
        XMLEvent event;
        try {
            if (medicines == null) {
                medicines = new ArrayList<>();
            }
            if (streamReader.hasNext()) {
                String medicineName = "";
                String url = "";
                String price = "";
                String packing = "";

                while (streamReader.hasNext()) {
                    int eventType = streamReader.next();
                    if (eventType == XMLStreamConstants.START_ELEMENT) {
                        startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                        QName qName = startElement.getName();
                        //Get name and URL of medicine
                        if ("h3".equals(qName.toString())) {
                            String pAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();
                            if (pAtt.contains("product-title")) {
                                streamReader.nextTag();
                                startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                                url = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE)).getValue();

                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    medicineName = event.asCharacters().getData();
                                    String tmpMedicineName[] = medicineName.split("\\(");
                                    medicineName = tmpMedicineName[0].trim();

                                }
                            }
                        }
                        //Get price of medicine
                        if ("span".equals(qName.toString())) {
                            String spanAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();
                            if (spanAtt.contains("woocommerce-Price-amount")) {
                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    price = event.asCharacters().getData();
                                    price = price.replace(",", "").trim();
                                    
                                }
                            }
                        }
                        //Get type of medicine
                        if ("span".equals(qName.toString())) {
                            String spanAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE)).getValue();

                            if (spanAtt.contains("uom")) {
                                streamReader.next();
                                event = XMLHelper.getXMLEvent(streamReader);
                                if (event.isCharacters()) {
                                    packing = event.asCharacters().getData();
                                    packing = packing.replace("/", "").trim();
                                    
                                    TblMedicine medicine = new TblMedicine();
                                    medicine.setMedicineName(medicineName);
                                    medicine.setUrl(url);
                                    medicine.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
                                    medicine.setPacking(packing);
                                    medicine.setDescription(null);
                                    medicine.setCategoryName(null);
                                    medicines.add(medicine);
                                }
                            }
                        }

                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parsePageMedicine", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parsePageMedicine - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return medicines;
    }
    //Parse Menu content to  List<String> urls


    public List<String> parseUrl(String contentMenu) {
        System.out.println("Dang parse URL cua danh muc Pharmacity");
        List<String> menus = null;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(contentMenu);
        try {
            while (streamReader.hasNext()) {
                int cursor = streamReader.next();
                if (menus == null) {
                    menus = new ArrayList<>();
                }
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    XMLEvent event = XMLHelper.getXMLEvent(streamReader);
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    
                    if ("a".equals(qName.toString())) {
                        Attribute classAtt = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE));
                        if (classAtt.getValue().equals("mega-menu-link")) {
                            Attribute urlAtt = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE));
                            String url = urlAtt.getValue();
                            if (url.contains(URLConstant.COLD_AND_COUGH)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.PAIN_FEVER)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.DERMATOLOGY)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.ANTIALLERGIC)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.INFLAMMATORY)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.DIGESTIVE)) {
                                menus.add(url);
                            } else if (url.contains(URLConstant.VITAMIN_MINERAL)) {
                                menus.add(url);
                            }
                        }
                    }
                }
            }
        } catch (XMLStreamException e) {
            ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parseUrl", ErrorConstant.XMLSTREAM_EXCEPTION, e.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parseUrl - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return menus;
    }

    //Parse detail
    public TblMedicine parseMedicineDetail(String content, TblMedicine medicine) {
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        XMLEvent event;
        StartElement startElement;
        String description = "";
        
        try {
            while (streamReader.hasNext()) {
                int eventType = streamReader.next();
                if (eventType == XMLStreamConstants.START_ELEMENT) {
                    startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                    if ("p".equals(startElement.getName().toString())) {
                        streamReader.next();
                        event = XMLHelper.getXMLEvent(streamReader);
                        if (event.isCharacters()) {
                            description = event.asCharacters().getData();
                            medicine.setDescription(description);
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parseMedicineDetail", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PharmacityParser.class.getName(), "parseMedicineDetail - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return medicine; //Done
    }
}
