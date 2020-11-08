/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.crawls;

import centralregiondonate.constants.URLConstant;
import centralregiondonate.dtos.TblProvince;
import centralregiondonate.helpers.HTMLHelper;
import centralregiondonate.parsers.KeHoachVietParser;
import centralregiondonate.parsers.PaginationParser;
import java.util.List;

/**
 *
 * @author Huy Nguyen
 */
public class KeHoachVietCrawl {

    private String START_TAG_ROOT = "";
    private String END_TAG_ROOT = "";
    private String START_TAG = "";
    private String END_TAG = "";
    private String STOP_TAG = "";
    private String CONTENT = "";

    public String crawlMenuKHV(String url) {
        START_TAG_ROOT = "<menu>";
        END_TAG_ROOT = "</menu>";
        START_TAG = "<ul id=\"menu-menu";
        END_TAG = "\">";
        STOP_TAG = "</ul></div>";
        CONTENT = HTMLHelper.baseCrawl(url, START_TAG_ROOT, END_TAG_ROOT, START_TAG, END_TAG, STOP_TAG);
        return CONTENT;
    }

    public String crawlStatisticPage(String url) {
        START_TAG = "<div class=\"post-listing";
        END_TAG = "\">";
        STOP_TAG = "<div class=\"pagination\">";
        CONTENT = HTMLHelper.baseCrawl(url, "", "", START_TAG, END_TAG, STOP_TAG);
        //Remove  </div> last
        CONTENT = CONTENT.substring(0, CONTENT.length() - 6);
        CONTENT = CONTENT.replaceAll("<div.*?</div>", "");
        return CONTENT;
    }

    public String crawlStatisticPages(String url) {
        System.out.println("crawlStatisticPages");
        PaginationParser parser = new PaginationParser();
        int pageSize = parser.getPageSizeKHV(url);
        CONTENT = "<statistic>";
        for (int i = 0; i < pageSize; i++) {
            String urlPage = url + "page/" + i;
            CONTENT += crawlStatisticPage(urlPage);
        }
        CONTENT += "</statistic>";
        return CONTENT;
    }

    public String crawlTablePopulation(String url) {
        System.out.println("crawlTablePopulation");
        START_TAG_ROOT = "<population>";
        END_TAG_ROOT = "</population>";
        START_TAG = "<table";
        END_TAG = "\">";
        STOP_TAG = "</table>";
        CONTENT = HTMLHelper.baseCrawl(url, START_TAG_ROOT, END_TAG_ROOT, START_TAG, END_TAG, STOP_TAG);
        CONTENT = CONTENT.replaceAll("<td></td>.*?<p><strong><em>200,0</em></strong></td>", "");
        CONTENT = CONTENT.replaceAll("<td></td>", "");
        
        return CONTENT;
    }

    public static void main(String[] args) {
//        //crawl menu to get url
//        KeHoachVietCrawl crawl1 = new KeHoachVietCrawl();
//        String content = crawl1.crawlMenuKHV("https://kehoachviet.com/");
//        //System.out.println("content: " + content);
//        //parse to get real url
//        KeHoachVietParser parser = new KeHoachVietParser();
//        String url = parser.parseURL(content);
//        //System.out.println("url: " + url);
//        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
//        String page = crawl.crawlStatisticPage("https://kehoachviet.com/thong-ke/");
//        System.out.println("page: " + page);
//        KeHoachVietParser parser = new KeHoachVietParser();
//        parser.parsePopulationURL(page);
//        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
//        String menu = crawl.crawlMenuKHV(URLConstant.KHV_ROOT);
//        KeHoachVietParser parser = new KeHoachVietParser();
//        String url = parser.parseURL(menu);
//
//        String content = crawl.crawlStatisticPages(url);
//        String urlPopu = parser.parsePopulationURL(content);
//        System.out.println("urlPopu: " + urlPopu);
//        KeHoachVietCrawl crawl = new KeHoachVietCrawl();
//        String content = crawl.crawlTablePopulation("https://kehoachviet.com/tinh-hinh-dan-cac-tinh-mien-trung/");
//        System.out.println("CONTENT: " + content);
//        KeHoachVietParser parser = new KeHoachVietParser();
//        List<TblProvince> provinces = parser.parseProvinces(content);
//        for (int i = 0; i < provinces.size(); i++) {
//            System.out.println("name: " + provinces.get(i).getProvinceName());
//            System.out.println("population: " + provinces.get(i).getPopulation());
//        }
    }
}
