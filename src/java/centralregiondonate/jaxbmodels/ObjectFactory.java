
package centralregiondonate.jaxbmodels;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the centralregiondonate.jaxbmodels package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Provinces_QNAME = new QName("http://www.centralregiondonate.provinces.com", "provinces");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: centralregiondonate.jaxbmodels
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Provinces }
     * 
     */
    public Provinces createProvinces() {
        return new Provinces();
    }

    /**
     * Create an instance of {@link Province }
     * 
     */
    public Province createProvince() {
        return new Province();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Provinces }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.centralregiondonate.provinces.com", name = "provinces")
    public JAXBElement<Provinces> createProvinces(Provinces value) {
        return new JAXBElement<Provinces>(_Provinces_QNAME, Provinces.class, null, value);
    }

}
