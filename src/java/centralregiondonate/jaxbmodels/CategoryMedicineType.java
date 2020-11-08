
package centralregiondonate.jaxbmodels;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CategoryMedicineType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CategoryMedicineType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Thuốc cảm lạnh, ho"/>
 *     &lt;enumeration value="Thuốc giảm đau, hạ sốt"/>
 *     &lt;enumeration value="Thuốc thuốc da liễu"/>
 *     &lt;enumeration value="Thuốc kháng dị ứng"/>
 *     &lt;enumeration value="Thuốc kháng viêm"/>
 *     &lt;enumeration value="Thuốc tiêu hoá"/>
 *     &lt;enumeration value="Viatamin và khoáng chất"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CategoryMedicineType")
@XmlEnum
public enum CategoryMedicineType {

    @XmlEnumValue("Thu\u1ed1c c\u1ea3m l\u1ea1nh, ho")
    THUỐC_CẢM_LẠNH_HO("Thu\u1ed1c c\u1ea3m l\u1ea1nh, ho"),
    @XmlEnumValue("Thu\u1ed1c gi\u1ea3m \u0111au, h\u1ea1 s\u1ed1t")
    THUỐC_GIẢM_ĐAU_HẠ_SỐT("Thu\u1ed1c gi\u1ea3m \u0111au, h\u1ea1 s\u1ed1t"),
    @XmlEnumValue("Thu\u1ed1c thu\u1ed1c da li\u1ec5u")
    THUỐC_THUỐC_DA_LIỄU("Thu\u1ed1c thu\u1ed1c da li\u1ec5u"),
    @XmlEnumValue("Thu\u1ed1c kh\u00e1ng d\u1ecb \u1ee9ng")
    THUỐC_KHÁNG_DỊ_ỨNG("Thu\u1ed1c kh\u00e1ng d\u1ecb \u1ee9ng"),
    @XmlEnumValue("Thu\u1ed1c kh\u00e1ng vi\u00eam")
    THUỐC_KHÁNG_VIÊM("Thu\u1ed1c kh\u00e1ng vi\u00eam"),
    @XmlEnumValue("Thu\u1ed1c ti\u00eau ho\u00e1")
    THUỐC_TIÊU_HOÁ("Thu\u1ed1c ti\u00eau ho\u00e1"),
    @XmlEnumValue("Viatamin v\u00e0 kho\u00e1ng ch\u1ea5t")
    VIATAMIN_VÀ_KHOÁNG_CHẤT("Viatamin v\u00e0 kho\u00e1ng ch\u1ea5t");
    private final String value;

    CategoryMedicineType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CategoryMedicineType fromValue(String v) {
        for (CategoryMedicineType c: CategoryMedicineType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
