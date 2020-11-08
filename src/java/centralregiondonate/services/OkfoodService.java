/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.services;

import centralregiondonate.clients.NoodleClient;
import centralregiondonate.crawls.OkfoodCrawl;
import centralregiondonate.dtos.TblNoodle;
import centralregiondonate.helpers.JAXBHelper;
import centralregiondonate.jaxbmodels.Noodle;
import centralregiondonate.jaxbmodels.Noodles;
import centralregiondonate.parsers.OkfoodParser;
import java.util.List;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author Huy Nguyen
 */
public class OkfoodService {
    
    public void getNoodles(String url) {
        
        String categoryURL = getURL(url);
        List<TblNoodle> noodles = getAllNoodles(categoryURL);
        NoodleClient client = new NoodleClient();
        TblNoodle model = null;
        
        //Noodles n = new Noodles();
        
        for (int i = 0; i < noodles.size(); i++) {
            model = new TblNoodle();
            model.setNoodleName(noodles.get(i).getNoodleName());
            model.setPrice(noodles.get(i).getPrice());
            //client.create_XML(model);
            
            System.out.println("No. " + i);
            System.out.println("name: " + noodles.get(i).getNoodleName());
            System.out.println("price: " + noodles.get(i).getPrice());
            System.out.println("-------------------------------");
            
            //Noodle n1 = new Noodle();
            //n1.setNoodleName(noodles.get(i).getNoodleName());
            //n1.setPrice(noodles.get(i).getPrice());
            //n.getNoodle().add(n1);
        }
        //JAXBHelper jaxbh = new JAXBHelper();
        //jaxbh.marshal(n, "noodles.xml");
    }
    public String getURL(String url) {
        String baseURL = "";
        OkfoodCrawl crawl = new OkfoodCrawl();
        String content = crawl.crawlCategories(url);
        OkfoodParser parser = new OkfoodParser();
        baseURL = parser.parseCategoryURL(content);
        return  baseURL;
    }
    
    public List<TblNoodle> getAllNoodles(String url) {
        List<TblNoodle> noodles = null;
        OkfoodCrawl crawl = new OkfoodCrawl();
        String content = crawl.crawlNoodles(url);
        
        OkfoodParser parser = new OkfoodParser();
        noodles = parser.parseNoodles(content);
        return noodles;
    }
    
    //DB
    public static List<TblNoodle> fetchNoodles(String name) {
        NoodleClient noodleClient = new NoodleClient();
        GenericType<List<TblNoodle>> gtNoodle = new GenericType<List<TblNoodle>>(){};
        return noodleClient.fetchNoodlesByName(gtNoodle, name);
    }
}
