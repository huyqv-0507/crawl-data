/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.services;

import centralregiondonate.clients.MedicineClient;
import centralregiondonate.constants.URLConstant;
import centralregiondonate.crawls.PharmacityCrawl;
import centralregiondonate.dtos.TblMedicine;
import centralregiondonate.helpers.JAXBHelper;
import centralregiondonate.jaxbmodels.CategoryMedicineType;
import centralregiondonate.jaxbmodels.Medicine;
import centralregiondonate.jaxbmodels.Medicines;
import centralregiondonate.parsers.PharmacityParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Huy Nguyen
 */
public class PharmacityService {

    //Servlet call
    public void getMedicines(String url) {
        System.out.println("Bat dau cao...");
        int count = 0;
        Map<String, List<TblMedicine>> medicineInfo = getMedicinesInfo(url);

        List<TblMedicine> medicines = new ArrayList<>();
        MedicineClient client = new MedicineClient();
        TblMedicine medicine = null;
        
        for (Map.Entry<String, List<TblMedicine>> entry : medicineInfo.entrySet()) {
            String key = entry.getKey();
            List<TblMedicine> medicinesList = entry.getValue();

            List<TblMedicine> tmpMedicines = getMedicinesWithDetail(medicinesList, key);
            medicineInfo.replace(key, medicinesList, tmpMedicines);
        }
        for (Map.Entry<String, List<TblMedicine>> entry : medicineInfo.entrySet()) {
            List<TblMedicine> m = entry.getValue();
            for (int i = 0; i < m.size(); i++) {
                medicines.add(m.get(i));

                count++;
                System.out.println("No. " + count);
                System.out.println("name: " + m.get(i).getMedicineName());
                System.out.println("url: " + m.get(i).getUrl());
                System.out.println("type: " + m.get(i).getPacking());
                System.out.println("price: " + m.get(i).getPrice());
                System.out.println("description: " + m.get(i).getDescription());
                System.out.println("categoryName: " + m.get(i).getCategoryName());
                System.out.println("-------------------------------");
            }
        }
       // Medicines m = new Medicines();

        for (int i = 0; i < medicines.size(); i++) {
            medicine = new TblMedicine();
            medicine.setCategoryName(medicines.get(i).getCategoryName());
            medicine.setMedicineName(medicines.get(i).getMedicineName());
            medicine.setPrice(medicines.get(i).getPrice());
            medicine.setPacking(medicines.get(i).getPacking());
            medicine.setDescription(medicines.get(i).getDescription());
            //client.create_XML(medicine);

//            Medicine m1 = new Medicine();
//            m1.setMedicineName(medicines.get(i).getMedicineName());
//            m1.setPrice(medicines.get(i).getPrice());
//            m1.setUrl(medicines.get(i).getUrl());
//            m1.setPacking(medicines.get(i).getPacking());
//            m1.setDescription(medicines.get(i).getDescription());
//            if (medicines.get(i).getCategoryName().equals("Thuốc kháng dị ứng")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_KHÁNG_DỊ_ỨNG);
//            } else if (medicines.get(i).getCategoryName().equals("Thuốc kháng viêm")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_KHÁNG_VIÊM);
//            } else if (medicines.get(i).getCategoryName().equals("Thuốc cảm lạnh, ho")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_CẢM_LẠNH_HO);
//            } else if (medicines.get(i).getCategoryName().equals("Thuốc da liễu")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_THUỐC_DA_LIỄU);
//            } else if (medicines.get(i).getCategoryName().equals("Thuốc tiêu hoá")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_TIÊU_HOÁ);
//            } else if (medicines.get(i).getCategoryName().equals("Thuốc giảm đau, hạ sốt")) {
//                m1.setCategoryName(CategoryMedicineType.THUỐC_GIẢM_ĐAU_HẠ_SỐT);
//            } else if (medicines.get(i).getCategoryName().equals("Viatamin và khoáng chất")) {
//                m1.setCategoryName(CategoryMedicineType.VIATAMIN_VÀ_KHOÁNG_CHẤT);
//            }
//            m.getMedicine().add(m1);

        }
//        JAXBHelper jaxbh = new JAXBHelper();
//        jaxbh.marshal(m, "medicines.xml");
    }

    public Map<String, List<TblMedicine>> getMedicinesInfo(String url) {
        Map<String, List<TblMedicine>> allMedicines = new HashMap<>();
        List<String> urls = crawlURLsOfPharmacity(URLConstant.PHARMACITY_ROOT);
        for (int i = 0; i < urls.size(); i++) {
            System.out.println("i: " + i + "\nurl: " + urls.get(i));
            switch (i) {
                case 0:
                    allMedicines.put("Thuốc kháng dị ứng", crawlMedicine(urls.get(i)));
                    break;
                case 1:
                    allMedicines.put("Thuốc kháng viêm", crawlMedicine(urls.get(i)));
                    break;
                case 2:
                    allMedicines.put("Thuốc cảm lạnh, ho", crawlMedicine(urls.get(i)));
                    break;
                case 3:
                    allMedicines.put("Thuốc da liễu", crawlMedicine(urls.get(i)));
                    break;
                case 4:
                    allMedicines.put("Thuốc tiêu hoá", crawlMedicine(urls.get(i)));
                    break;
                case 5:
                    allMedicines.put("Thuốc giảm đau, hạ sốt", crawlMedicine(urls.get(i)));
                    break;
                case 6:
                    allMedicines.put("Vitamin và khoáng chất", crawlMedicine(urls.get(i)));
                    break;

            }
        }
        return allMedicines;
    }

    public List<TblMedicine> crawlMedicine(String url) {
        PharmacityCrawl pharmacityCrawl = new PharmacityCrawl();
        String medicinesContent = pharmacityCrawl.crawlMedicines(url);
        PharmacityParser pharmacityParser = new PharmacityParser();

        return pharmacityParser.parsePageMedicine(medicinesContent);
    }

    //Craw all url of categories in Pharmacity website
    public List<String> crawlURLsOfPharmacity(String url) {
        System.out.println("Dang cao URL danh muc cua Pharmacity");
        PharmacityCrawl pharmacityCrawl = new PharmacityCrawl();
        String urlContent = pharmacityCrawl.crawlUrl(url);

        PharmacityParser pharmacityParser = new PharmacityParser();
        List<String> urls = pharmacityParser.parseUrl(urlContent);
        return urls;
    }

    public List<TblMedicine> getMedicinesWithDetail(List<TblMedicine> medicines, String key) {
        System.out.println("Dang cao mo ta thuoc...");
        List<TblMedicine> medicinesWithDetails = new ArrayList<>();
        System.out.println("Dang parse mo ta thuoc...");
        for (int i = 0; i < medicines.size(); i++) {
            if (medicines.get(i).getUrl().contains("thuoc-com-dieu-tri-cac-roi-loan-tiet-dich-duong-ho-hap-vi-cam-acemuc-100mg")) {
                medicines.remove(i);
            }
            medicinesWithDetails.add(getMedicineWithDetail(medicines.get(i), key));
        }
        return medicinesWithDetails;
    }

    public TblMedicine getMedicineWithDetail(TblMedicine medicine, String key) {

        PharmacityCrawl pharmacityCrawl = new PharmacityCrawl();
        String description = pharmacityCrawl.crawlMedicineDetail(medicine.getUrl());

        PharmacityParser pharmacityParser = new PharmacityParser();

        TblMedicine medicineWithDetail = pharmacityParser.parseMedicineDetail(description, medicine);
        medicineWithDetail.setCategoryName(key);

        return medicineWithDetail;
    }
    
    
    //DB
    //Thuốc giảm đau, hạ sốt
    public static List<TblMedicine> fetchPainFevers() {
        MedicineClient medicineClient = new MedicineClient();
        GenericType<List<TblMedicine>> gtMedicine = new GenericType<List<TblMedicine>>(){};
        return medicineClient.fetchPainFevers(gtMedicine);
    }
    
    //Thuốc cảm lạnh, ho
    public static List<TblMedicine> fetchColdCoughs() {
        MedicineClient medicineClient = new MedicineClient();
        GenericType<List<TblMedicine>> gtMedicine = new GenericType<List<TblMedicine>>(){};
        return medicineClient.fetchColdCoughs(gtMedicine);
    }
    
    //Thuốc da liễu
    public static List<TblMedicine> fetchDermatologies() {
        MedicineClient medicineClient = new MedicineClient();
        GenericType<List<TblMedicine>> gtMedicine = new GenericType<List<TblMedicine>>(){};
        return medicineClient.fetchDermatologies(gtMedicine);
    }
    //Thuốc tiêu hoá
    public static List<TblMedicine> fetchDegestives() {
        MedicineClient medicineClient = new MedicineClient();
        GenericType<List<TblMedicine>> gtMedicine = new GenericType<List<TblMedicine>>(){};
        return medicineClient.fetchDegestives(gtMedicine);
    }
    //Vitamin và khoáng chất
    public static List<TblMedicine> fetchVitamins() {
        MedicineClient medicineClient = new MedicineClient();
        GenericType<List<TblMedicine>> gtMedicine = new GenericType<List<TblMedicine>>(){};
        return medicineClient.fetchVitamins(gtMedicine);
    }
}
