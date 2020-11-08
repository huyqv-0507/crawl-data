/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;

/**
 *
 * @author Huy Nguyen
 */
public class CalculatorHelper {

    public static String calculatePercentDamaged(float numberDamaged, float totalPopulation) {
        //Tỷ lệ số hộ dân bị ảnh hưởng bởi bão
        float damagedPercent = numberDamaged / totalPopulation * 100;
        String percent = String.format("%.2f", damagedPercent);
        return percent + " %.";
    }
    
}
