/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.services;

import centralregiondonate.clients.RiceClient;
import centralregiondonate.crawls.GaoSachGiaBaoCrawl;
import centralregiondonate.dtos.TblRice;
import centralregiondonate.helpers.JAXBHelper;
import centralregiondonate.jaxbmodels.Rice;
import centralregiondonate.jaxbmodels.Rices;
import centralregiondonate.parsers.GaoSachGiaBaoParser;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Huy Nguyen
 */
public class GaoSachGiaBaoService {

    
    //client
    public void getRices(String url) {
        System.out.println("Bat dau cao...");
        String tmpURL = getURL(url);
        String categoryURL = getCategory(tmpURL);
        List<TblRice> rices = getAllRices(categoryURL);
        RiceClient client = new RiceClient();
        TblRice model = null;
        //Rices tmpRices = new Rices();
        for (int i = 0; i < rices.size(); i++) {
            model = new TblRice();
            model.setRiceName(rices.get(i).getRiceName());
            model.setPrice(rices.get(i).getPrice());
            //client.create_XML(model);
            System.out.println("No. " + i);
            System.out.println("name: " + rices.get(i).getRiceName());
            System.out.println("price: " + rices.get(i).getPrice());
            System.out.println("-------------------------------");
            
            //Rice tmpRice = new Rice();
            //tmpRice.setRiceName(rices.get(i).getRiceName());
            //tmpRice.setPrice(rices.get(i).getPrice());
            //tmpRices.getRice().add(tmpRice);
        }
        
        //JAXBHelper jaxbh = new JAXBHelper();
        //jaxbh.marshal(tmpRices, "rices.xml");
    }

    public String getURL(String url) {
        String baseURL = "";
        GaoSachGiaBaoCrawl crawl = new GaoSachGiaBaoCrawl();
        String content = crawl.crawlUrl(url);
        GaoSachGiaBaoParser parser = new GaoSachGiaBaoParser();
        baseURL = parser.parseURL(content);
        return baseURL;
    }

    public String getCategory(String url) {
        GaoSachGiaBaoCrawl crawl = new GaoSachGiaBaoCrawl();
        String content = crawl.crawlRiceCategories(url);
        GaoSachGiaBaoParser parser = new GaoSachGiaBaoParser();
        String category = parser.parseCategoryURL(content);
        return category;
    }

    public List<TblRice> getAllRices(String url) {
        List<TblRice> rices = null;
        GaoSachGiaBaoCrawl crawl = new GaoSachGiaBaoCrawl();
        String content = crawl.crawlRices(url);

        GaoSachGiaBaoParser parser = new GaoSachGiaBaoParser();
        rices = parser.parseRices(content);
        return rices;
    }
    
    //db
   public static List<TblRice> fetchRices() {
       RiceClient riceClient = new RiceClient();
       GenericType<List<TblRice>> gtRice = new GenericType<List<TblRice>>(){};
       return riceClient.findAll(gtRice);
   }
}
