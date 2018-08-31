package me.chin.utils;

/**
 * Created by ChenHouZhang on 2017/11/17.
 * 字符串工具类
 */
public class StringUtils {
    /**
     * 判断字符串是否为null或者""
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串是否为null或者""或者全为空格
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return str == null || str.trim().length() == 0;
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
