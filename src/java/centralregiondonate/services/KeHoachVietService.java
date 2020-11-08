/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.services;

import centralregiondonate.clients.ProvinceClient;
import centralregiondonate.crawls.KeHoachVietCrawl;
import centralregiondonate.dtos.TblProvince;
import centralregiondonate.parsers.KeHoachVietParser;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Huy Nguyen
 */
public class KeHoachVietService {
    public void getProvices(String url) {
        String baseURL = getURL(url);
        System.out.println("baseURL: " + baseURL);
        String populationURL = getPopulationURL(baseURL);
        System.out.println("populationURL: " + populationURL);
        List<TblProvince> provinces = getTablePopulation(populationURL);
        
        ProvinceClient client = new ProvinceClient();
        
        //Provinces p = new Provinces();
        for (int i = 0; i < provinces.size(); i++) {
            //client.create(provinces.get(i));
            System.out.println("Province name: " + provinces.get(i).getProvinceName());
            System.out.println("Population: " + provinces.get(i).getPopulation());
            
            //Province p1 = new Province();
            //p1.setProvinceName(provinces.get(i).getProvinceName());
            //p1.setPopulation(BigInteger.valueOf(provinces.get(i).getPopulation()));
            //p.getProvince().add(p1);
        }
        //JAXBHelper jaxbh = new JAXBHelper();
        //jaxbh.marshal(p, "provinces.xml");
    }
    
    public String getURL(String url) {
        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
        String menuContent = crawl.crawlMenuKHV(url);
        
        KeHoachVietParser parser = new KeHoachVietParser();
        String baseURL = parser.parseURL(menuContent);
        
        return baseURL;
    }
    
    public String getPopulationURL(String baseURL) {
        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
        String content = crawl.crawlStatisticPages(baseURL);
        
        KeHoachVietParser parser = new KeHoachVietParser();
        String populationURL = parser.parsePopulationURL(content);
        return populationURL;
    }
    public List<TblProvince> getTablePopulation(String populationURL) {
       
        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
        String content = crawl.crawlTablePopulation(populationURL);
        
        KeHoachVietParser parser = new KeHoachVietParser();
        List<TblProvince> provinces = parser.parseProvinces(content);
        return provinces;
    }
    
    
    //DB
    public static List<TblProvince> fetchProvincesFromDb() {
        ProvinceClient provinceClient = new ProvinceClient();
        GenericType<List<TblProvince>> provincesType = new GenericType<List<TblProvince>>(){};
        List<TblProvince> provinces = (List<TblProvince>) provinceClient.findAll(provincesType);
        return provinces;
    }
}
