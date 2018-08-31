package me.chin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Allen on 2018/8/31.
 */
public class URLEncodeUtils {
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String encode(String str) {
        return encode(str, DEFAULT_CHARSET);
    }

    public static String encode(String str, String charset) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String encodeChinese(String str) {
        return encodeChinese(str, DEFAULT_CHARSET);
    }

    public static String encodeChinese(String str, String charset) {
        if (str == null) {
            return null;
        }
        StringBuilder resultStrBuf = new StringBuilder();
        try {
            for (int i = 0; i < str.length(); i++) {
                String step = str.charAt(i) + "";
                if (step.getBytes().length > 1) {
                    resultStrBuf.append(URLEncoder.encode(step, charset));
                } else {
                    resultStrBuf.append(str.charAt(i));
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
        return resultStrBuf.toString();
    }
}
