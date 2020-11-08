/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.crawls;

import centralregiondonate.helpers.HTMLHelper;
import centralregiondonate.parsers.PaginationParser;

/**
 *
 * @author Huy Nguyen
 */
public class OkfoodCrawl {

    private final String OKFOOD_START_TAG_ROOT = "<noodles>";
    private final String OKFOOD_END_TAG_ROOT = "</noodles>";
    private final String START_TAG = "<div class=\"collect-grid";
    private final String END_TAG = "\">";
    private final String STOP_TAG = "<div class=\"collect-list\">";
    
    public String crawlCategories(String url) {
        String rootStartTag = "<menu>";
        String rootEndTag = "</menu>";
        String startTag = "<ul class=\"list-unstyled\" style=\"display: none";
        String endTag = "\">";
        String stopTag = "</ul>";
        return HTMLHelper.baseCrawl(url, rootStartTag, rootEndTag, startTag, endTag, stopTag);
    }
    public  String crawlPageNoodle(String url) {
        String content = HTMLHelper.baseCrawl(url, "", "", START_TAG, END_TAG, STOP_TAG);
        String format = String.format("<%s.*?>", "img");
        content = content.replaceAll(format, "");
        content = content.replaceAll("<div class=\"row\">", "");
        return content;
    }
    
    public String crawlNoodles(String url) {
        PaginationParser paginationParser = new PaginationParser();
        int pageSize = paginationParser.getPageSizeOkfood(url);
        String content = OKFOOD_START_TAG_ROOT;
        for (int i = 1; i <= pageSize; i++) {
            
            String urlPage = url + "?page=" + i;
            System.out.println("url: " + urlPage);
            content += crawlPageNoodle(urlPage);
        }
        content += OKFOOD_END_TAG_ROOT;
        String regex = "</form></div></div></div>";
        content = content.replaceAll(regex, "</form></div>");
        return content;
    }
    
    /*public static void main(String[] args) {
        OkfoodCrawl crawl = new OkfoodCrawl();
        String content = crawl.crawlNoodles("https://okfood.vn/mi-goi");
        
        OkfoodParser parser = new OkfoodParser();
        parser.parseNoodles(content);
    }*/
}
