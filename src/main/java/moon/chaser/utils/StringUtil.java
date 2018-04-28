package moon.chaser.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * Created by ChenHouZhang on 2017/11/17.
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 判断字符串是否为null或者""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否为null或者""或者全为空格
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return StringUtils.isBlank(str);
    }

    /**
     * 去除字符串所有空格
     * @param str
     * @return
     */
    public static String trimAllWhitespace(String str){
        if (isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            }
            else {
                index++;
            }
        }
        return sb.toString();
    }

}
