/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.parsers;

import centralregiondonate.constants.ErrorConstant;
import centralregiondonate.constants.QNameConstant;
import centralregiondonate.dtos.TblProvince;
import centralregiondonate.helpers.XMLHelper;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;

/**
 *
 * @author Huy Nguyen
 */
public class KeHoachVietParser {

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
                        if (href.contains("thong-ke/")) {
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
                    ErrorConstant.getErrorMsg(KeHoachVietParser.class.getName(), "parseURL - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return url;
    }

    public String parsePopulationURL(String content) {
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
                        if (href.contains("tinh-hinh-dan-cac-tinh-mien-trung")) {
                            url = href;
                            break;
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parsePopulationURL", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());

        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(KeHoachVietParser.class.getName(), "parsePopulationURL - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return url;
    }

    public List<TblProvince> parseProvinces(String content) {
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        StartElement startElement;
        List<TblProvince> provinces = null;
        try {
            if (provinces == null) {
                provinces = new ArrayList<>();
            }
            if (streamReader.hasNext()) {
                String provinceName = "";
                long population = 0;
                while (streamReader.hasNext()) {
                    int eventType = streamReader.next();
                    if (eventType == XMLStreamConstants.START_ELEMENT) {
                        startElement = XMLHelper.getXMLEvent(streamReader).asStartElement();
                        if ("td".equals(startElement.getName().toString())) {
                            String tdAtt = startElement.getAttributeByName(new QName("width")).getValue();
                            if (tdAtt.equals("250")) {
                                streamReader.next();
                                if (streamReader.isCharacters()) {
                                    provinceName = XMLHelper.getXMLEvent(streamReader).asCharacters().getData();
                                }
                                
                            }
                            if (tdAtt.equals("83")) {
                                streamReader.next();
                                if (streamReader.isCharacters()) {
                                    String tmpPopulation = XMLHelper.getXMLEvent(streamReader).asCharacters().getData();
                                    tmpPopulation = tmpPopulation.replaceAll(",", "");
                                    population = Long.parseLong(tmpPopulation);
                                    
                                    TblProvince province = new TblProvince();
                                    province.setProvinceName(provinceName);
                                    province.setPopulation(population * 1000);
                                    
                                    provinces.add(province);
                                }
                            }
                        }
                    }
                }
            }

        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(GaoSachGiaBaoParser.class.getName(), "parseProvinces", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());

        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(KeHoachVietParser.class.getName(), "parseProvinces - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return provinces;
    }
}
