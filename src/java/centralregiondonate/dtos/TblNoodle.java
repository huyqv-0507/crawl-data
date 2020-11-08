/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy Nguyen
 */
@XmlRootElement
public class TblNoodle implements Serializable{
    private Integer noodleId;
    private String noodleName;
    private BigDecimal price;

    public TblNoodle() {
    }

    public TblNoodle(String noodleName, BigDecimal price) {
        this.noodleName = noodleName;
        this.price = price;
    }

    public Integer getNoodleId() {
        return noodleId;
    }

    public void setNoodleId(Integer noodleId) {
        this.noodleId = noodleId;
    }

    public String getNoodleName() {
        return noodleName;
    }

    public void setNoodleName(String noodleName) {
        this.noodleName = noodleName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
