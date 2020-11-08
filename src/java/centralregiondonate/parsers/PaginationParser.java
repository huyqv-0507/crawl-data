/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.parsers;

import centralregiondonate.constants.ErrorConstant;
import centralregiondonate.constants.QNameConstant;
import centralregiondonate.crawls.PaginationCrawl;
import centralregiondonate.helpers.XMLHelper;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 *
 * @author Huy Nguyen
 */
public class PaginationParser {

    //Get page size
    public int getPageSizePharmacity(String url) {
        int pageSize = 1;
        PaginationCrawl paginationCrawl = new PaginationCrawl();
        String content = paginationCrawl.crawlPaginationPharmacityHTML(url);
        content = content.replace("<li><span class=\"page-number dots\">&hellip;</span></li", "");
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);

        try {
            while (streamReader.hasNext()) {
                int cursor = streamReader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    XMLEvent event = XMLHelper.getXMLEvent(streamReader);
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    if ("a".equals(qName.toString())) {
                        Attribute attribute = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE));
                        String attName = attribute.getValue();
                        if ("page-number".equals(attName)) {
                            streamReader.next();
                            event = XMLHelper.getXMLEvent(streamReader);
                            Characters characters = event.asCharacters();
                            pageSize = Integer.parseInt(characters.getData());
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizePharmacity", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        }  finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizePharmacity - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return pageSize;
    }

    public int getPageSizeGaoSachGiaBao(String url) {
        PaginationCrawl paginationCrawl = new PaginationCrawl();
        String content = paginationCrawl.crawlPaginationGaoSachGiaBaoHTML(url);
        int pageSize = 1;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        try {
            while (streamReader.hasNext()) {
                int cursor = streamReader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    XMLEvent event = XMLHelper.getXMLEvent(streamReader);
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    if ("a".equals(qName.toString())) {
                        Attribute attribute = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE));
                        String attName = attribute.getValue();
                        if ("page-number".equals(attName)) {
                            streamReader.next();
                            event = XMLHelper.getXMLEvent(streamReader);
                            Characters characters = event.asCharacters();
                            pageSize = Integer.parseInt(characters.getData());
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeGaoSachGiaBao", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeGaoSachGiaBao - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return pageSize;
    }

    public int getPageSizeOkfood(String url) {
        PaginationCrawl paginationCrawl = new PaginationCrawl();
        String content = paginationCrawl.crawlPaginationOkfoodHTML(url);
        int pageSize = 1;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        try {
            while (streamReader.hasNext()) {
                int cursor = streamReader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    XMLEvent event = XMLHelper.getXMLEvent(streamReader);
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    if ("a".equals(qName.toString())) {
                        Attribute attribute = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE));
                        String attName = attribute.getValue();
                        if (attName.contains("page")) {
                            streamReader.next();
                            event = XMLHelper.getXMLEvent(streamReader);
                            Characters characters = event.asCharacters();
                            pageSize = Integer.parseInt(characters.getData());
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeOkfood", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeOkfood - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return pageSize;
    }

    public int getPageSizeKHV(String url) {
        PaginationCrawl paginationCrawl = new PaginationCrawl();
        String content = paginationCrawl.crawlPaginationKHVHTML(url);
        int pageSize = 1;
        XMLStreamReader streamReader = XMLHelper.parseToCursorByStAX(content);
        try {
            while (streamReader.hasNext()) {
                int cursor = streamReader.next();
                if (cursor == XMLStreamConstants.START_ELEMENT) {
                    XMLEvent event = XMLHelper.getXMLEvent(streamReader);
                    StartElement startElement = event.asStartElement();
                    QName qName = startElement.getName();
                    if ("a".equals(qName.toString())) {
                        Attribute attribute = startElement.getAttributeByName(new QName(QNameConstant.CLASS_ATTRIBUTE));
                        String attName = attribute.getValue();
                        if (attName.contains("last")) {
                            String href = startElement.getAttributeByName(new QName(QNameConstant.HREF_ATTRIBUTE)).getValue().trim();
                            href = href.substring(href.length() - 3, href.length() - 1);
                            pageSize = Integer.parseInt(href);
                        }
                    }
                }
            }
        } catch (XMLStreamException ex) {
            ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeKHV", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
        } finally {
            if (streamReader != null) {
                try {
                    streamReader.close();
                } catch (XMLStreamException ex) {
                    ErrorConstant.getErrorMsg(PaginationParser.class.getName(), "getPageSizeKHV - close StAX", ErrorConstant.XMLSTREAM_EXCEPTION, ex.getMessage());
                }
            }
        }
        return pageSize;
    }
}
