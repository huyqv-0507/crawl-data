
package centralregiondonate.jaxbmodels;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Noodles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Noodles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="noodle" type="{http://www.centralregiondonate.noodles.com}Noodle" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Noodles", propOrder = {
    "noodle"
})
@XmlRootElement
public class Noodles {

    @XmlElement(required = true)
    protected List<Noodle> noodle;

    /**
     * Gets the value of the noodle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the noodle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNoodle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Noodle }
     * 
     * 
     */
    public List<Noodle> getNoodle() {
        if (noodle == null) {
            noodle = new ArrayList<Noodle>();
        }
        return this.noodle;
    }

}
