/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.constants;

/**
 *
 * @author Huy Nguyen
 */
public class ErrorConstant {
    //Error
    public static String getErrorMsg(String className, String methodName, String errorName, String msg) {
        return className + " - " + methodName + " - " + errorName + " - " + msg;
    }
    public static final String MALFORMEDURL_EXCEPTION = "MalformedURLException";
    public static final String IO_EXCEPTION = "IOException";
    public static final String UNSUPPORTEDENCODING_EXCEPTION = "UnsupportedEncodingException";
    public static final String XMLSTREAM_EXCEPTION = "XMLStreamException";
    public static final String XPATH_EXPRESSION_EXCEPTION = "XPathExpressionException";
    public static final String JAXB_EXCEPTION = "JAXBException";
}
