/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.crawls;

import centralregiondonate.clients.RiceClient;
import centralregiondonate.dtos.TblRice;
import centralregiondonate.helpers.HTMLHelper;
import centralregiondonate.parsers.PaginationParser;
import java.math.BigDecimal;

/**
 *
 * @author Huy Nguyen
 */
public class PharmacityCrawl {
    //RootName
    private final String PHARMACITY_START_TAG_ROOT = "<medicines>";
    private final String PHARMACITY_END_TAG_ROOT = "</medicines>";
    
    //Determine start point to crawl
    private final String START_TAG = "<div class=\"products";
    private final String END_TAG = "equalize-box\">";
    
    //Determine end point
    private final String STOP_TAG = "<!-- row --><div class=\"container\">";
    
    public String crawlPageContent(String url) {
        return HTMLHelper.baseCrawl(url, "", "", START_TAG, END_TAG, STOP_TAG);
    }
    
    public String crawlMedicines(String url) {
        PaginationParser paginationParser = new PaginationParser();
        int pageSize = paginationParser.getPageSizePharmacity(url);
        String content = PHARMACITY_START_TAG_ROOT;
        for (int i = 1; i <= pageSize; i++) {
            String urlPage = url + "/page/" + i;
            content += crawlPageContent(urlPage);
        }
        
        content += PHARMACITY_END_TAG_ROOT;
        return content;
    }
    
    public String crawlUrl(String url) {
        String rootStartTag = "<menu>";
        String rootEndTag = "</menu>";
        String startTag = "<ul class=\"nav header-nav header-bottom-nav nav-left";
        String endTag = "\">";
        String stopTag = "</div><!-- flex-col -->";
        return HTMLHelper.baseCrawl(url, rootStartTag, rootEndTag, startTag, endTag, stopTag);
    }
    
    
    public String crawlMedicineDetail(String url) {
        String rootStartTag = "<detail>";
        String rootEndTag = "</detail>";
        String description = crawlMedicineDescription(url);
        String content = rootStartTag + description + rootEndTag;
        content = content.replaceAll("<br>", "");
        return content;
    }
    public String crawlMedicineDescription(String url) {
        String startTag = "<div class=\"product-short-description";
        String endTag = "\">";
        String stopTag = "</div>";
        return HTMLHelper.baseCrawl(url, "", "", startTag, endTag, stopTag);
    }
    public String crawlMedicineInfo(String url) {
        String startTag = "<div class=\"panel entry-content";
        String endTag = "tab-description\">";
        String stopTag = "</div>";
        String content = HTMLHelper.baseCrawl(url, "", "", startTag, endTag, stopTag);
        String format = String.format("<%s.*?%s>", "strong", "strong");
        content = content.replaceAll(format, "");
        content = content.replaceAll("<br />", "");
        return  content;
    }
}
