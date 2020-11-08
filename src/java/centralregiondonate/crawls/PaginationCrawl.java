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
public class PaginationCrawl {

    public String crawlPaginationPharmacityHTML(String url) {
        String startTagRoot = "<pagination>";
        String endTagRoot = "</pagination>";

        //Determine start point to crawl
        String startTag = "<div class=\"container\"";
        String endTag = ">";

        //Determine end point
        String stopTag = "</div><!-- shop container -->";
        return HTMLHelper.baseCrawl(url, startTagRoot, endTagRoot, startTag, endTag, stopTag);
    }
     public String crawlPaginationGaoSachGiaBaoHTML(String url) {
        System.out.println("Dang cao page size...");
        String startTagRoot = "<pagination>";
        String endTagRoot = "</pagination>";

        //Determine start point to crawl
        String startTag = "<nav class=\"woocommerce-pagination";
        String endTag = "\">";

        //Determine end point
        String stopTag = "</div>";
        
        String content = HTMLHelper.baseCrawl(url, startTagRoot, endTagRoot, startTag, endTag, stopTag);
        content = content.replace("</nav>", "");
        return content;
    }
      public String crawlPaginationOkfoodHTML(String url) {
        System.out.println("Dang cao page size...");
        String startTagRoot = "<pagination>";
        String endTagRoot = "</pagination>";

        //Determine start point to crawl
        String startTag = "<li class=\"current\">1</li";
        String endTag = ">";

        //Determine end point
        String stopTag = "<li class=\"next\">";
        
        String content = HTMLHelper.baseCrawl(url, startTagRoot, endTagRoot, startTag, endTag, stopTag);

        return content;
    }
      public String crawlPaginationKHVHTML(String url) {
        System.out.println("Dang cao page size...");
        String startTagRoot = "<pagination>";
        String endTagRoot = "</pagination>";

        //Determine start point to crawl
        String startTag = "<div class=\"pagination\"";
        String endTag = ">";

        //Determine end point
        String stopTag = "</div> <!-- .content -->";
        
        String content = HTMLHelper.baseCrawl(url, startTagRoot, endTagRoot, startTag, endTag, stopTag);
        content = content.replaceAll("</div>", "");
        content = content.replaceAll("<span.*?</span>", "");
        content = content.replaceAll(" &raquo;", "");
        //System.out.println("content: " + content);
        return content;
    }
      public static void main(String[] args) {
          PaginationParser paginationParser = new PaginationParser();
          paginationParser.getPageSizeKHV("https://kehoachviet.com/thong-ke/");
          //System.out.println("content: " + content);
    }
}
