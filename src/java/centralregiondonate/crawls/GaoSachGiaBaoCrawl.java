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
public class GaoSachGiaBaoCrawl {

    private final String GSGB_START_TAG_ROOT = "<rice>";
    private final String GSGB_END_TAG_ROOT = "</rice>";
    private final String START_TAG = "<div class=\"products";
    private final String END_TAG = "\">";
    private final String STOP_TAG = "<!-- row -->";

    public String crawlUrl(String url) {
        String rootStartTag = "<menu>";
        String rootEndTag = "</menu>";
        String startTag = "<ul class=\"nav header-nav header-bottom-nav nav-left";
        String endTag = "\">";
        String stopTag = "</div><!-- flex-col -->";
        String content = HTMLHelper.baseCrawl(url, rootStartTag, rootEndTag, startTag, endTag, stopTag);

        //Valid content
        content = content.replace("</ul>", "");
        String tag = "i";
        String regex = String.format("<%s.*?</%s>", tag, tag);
        content = content.replaceAll(regex, "");
        tag = "ul";
        regex = String.format("<%s.*?</%s>", tag, tag);
        content = content.replaceAll(regex, "");
        return content;
    }

    public String crawlRiceCategories(String url) {
        String rootStartTag = "<categories>";
        String rootEndTag = "</categories>";
        String startTag = "<div class=\"section-content relative";
        String endTag = "\">";
        String stopTag = "<!-- .section-content -->";
        String content = HTMLHelper.baseCrawl(url, rootStartTag, rootEndTag, startTag, endTag, stopTag);
        return content;
    }

    public String crawlPageRice(String url) {
        return HTMLHelper.baseCrawl(url, "", "", START_TAG, END_TAG, STOP_TAG);
    }

    public String crawlRices(String url) {
        PaginationParser paginationParser = new PaginationParser();
        int pageSize = paginationParser.getPageSizeGaoSachGiaBao(url);
        String content = GSGB_START_TAG_ROOT;
        for (int i = 1; i <= pageSize; i++) {
            String urlPage = url + "page/" + i;
            content += crawlPageRice(urlPage);
        }
        content += GSGB_END_TAG_ROOT;
        return content;
    }
}
