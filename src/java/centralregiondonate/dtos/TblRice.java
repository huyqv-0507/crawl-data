/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Huy Nguyen
 */
@XmlRootElement
public class TblRice implements Serializable{
    private Integer riceId;
    private String riceName;
    private BigDecimal price;

    public TblRice() {
    }

    public TblRice(String riceName, BigDecimal price) {
        this.riceName = riceName;
        this.price = price;
    }

    public Integer getRiceId() {
        return riceId;
    }

    public void setRiceId(Integer riceId) {
        this.riceId = riceId;
    }

    public String getRiceName() {
        return riceName;
    }

    public void setRiceName(String riceName) {
        this.riceName = riceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
}
