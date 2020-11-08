/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;

import centralregiondonate.constants.ErrorConstant;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author Huy Nguyen
 */
public class HTMLHelper {

    public static BufferedReader getBufferReaderContent(String url) {
        BufferedReader bufferedReader = null;
        try {
            URL baseURL = new URL(url);
            URLConnection urlConnection = baseURL.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (MalformedURLException ex) {
            ErrorConstant.getErrorMsg(HTMLHelper.class.getName(), "getBufferReaderContent", ErrorConstant.MALFORMEDURL_EXCEPTION, ex.getMessage());
        } catch (IOException ex) {
            ErrorConstant.getErrorMsg(HTMLHelper.class.getName(), "getBufferReaderContent", ErrorConstant.IO_EXCEPTION, ex.getMessage());
        }
        return bufferedReader;
    }

    public static String baseCrawl(String url, String startRoot, String endRoot, String startTag, String endTag, String stopTag) {
        //Flag
        boolean isStart = false;
        boolean isFound = false;

        String content = startRoot;
        String line = "";
        BufferedReader bufferedReader = HTMLHelper.getBufferReaderContent(url);
        try {
            while ((line = bufferedReader.readLine()) != null) {
                if (isStart && line.contains(stopTag)) {
                    break;
                }
                if (isStart) {
                   //System.out.println("line: " + line + "\n");
                    content += line.trim();
                }
                if (line.contains(startTag)) {
                    isFound = true;
                }
                if (isFound && line.contains(endTag)) {
                    isStart = true;
                }
            }
        } catch (IOException ex) {
            ErrorConstant.getErrorMsg(HTMLHelper.class.getName(), "crawlHTML", ErrorConstant.IO_EXCEPTION, ex.getMessage());
        } finally {
            if (bufferedReader != null) try {
                bufferedReader.close();
            } catch (IOException ex) {
                ErrorConstant.getErrorMsg(HTMLHelper.class.getName(), "crawlHTML", ErrorConstant.IO_EXCEPTION, ex.getMessage());
            }
        }
        content = content + endRoot;
        return content;
    }
    
    public static String removeRedundantTags(String content) {
        String[] REDUNDANT_TAGS = {"script", "noscript", "style"}; 
        String result = content;
        for (String redundantTag : REDUNDANT_TAGS) {
            String format = String.format("<%s.*?</%s>", redundantTag, redundantTag);
            result = result.replaceAll(format, "");
        }
        return result;
    }
    
    public static XPath createXpath() {
        XPathFactory factory = XPathFactory.newInstance();
        return factory.newXPath();
    }
}
