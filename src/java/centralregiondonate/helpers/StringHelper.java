/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package centralregiondonate.helpers;

import centralregiondonate.constants.ErrorConstant;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Huy Nguyen
 */
public class StringHelper {

    //Convert String to InputStream
    public static InputStream toInputStream(String str) {
        InputStream result = null;
        try {
            result = new ByteArrayInputStream(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            ErrorConstant.getErrorMsg(StringHelper.class.getName(), "toInputStream", ErrorConstant.UNSUPPORTEDENCODING_EXCEPTION, ex.getMessage());
        }
        return result;
    }

    public static String encodeCharToUnicode(char c) {
        if (c < 0x10) {
            return "\\u000" + Integer.toHexString(c);
        } else if (c < 0x100) {
            return "\\u00" + Integer.toHexString(c);
        } else if (c < 0x1000) {
            return "\\u0" + Integer.toHexString(c);
        }
        return "\\u" + Integer.toHexString(c);
    }

    //Convert string to Unicode
    public static String encodeStringToUnicode(String string) {
        String result = "";
        for (int i = 0; i < string.length(); i++) {
            result += encodeCharToUnicode(string.charAt(i));
        }
        return result;
    }
    
    public static String decodeUnicodeToString(String string) {
        string = string.split(" ")[0];
        string = string.replace("\\", "");
        String[] arr = string.split("u");
        String result = "";
        for (int i = 1; i < arr.length; i++) {
            int hexVal = Integer.parseInt(arr[i], 16);
            result += (char) hexVal;
        }
        return result;
    }
}
