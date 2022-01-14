package ink.akira.utils;

import org.junit.Test;

/**
 * Created by ChenHouZhang on 2017/7/24.
 */
public class StringUtilsTest {
    @Test
    public void testTrim(){
        System.out.println(StringUtils.trimAllWhitespace(null) == null);
        System.out.println(Character.isWhitespace(' '));
    }
    @Test
    public void testMyTrim(){
        String str = "  abc   def   ghi   ";
        while (Character.isWhitespace(str.charAt(0))){
            str = str.substring(1);
        }
        while (Character.isWhitespace(str.charAt(str.length()-1))){
            str = str.substring(0,str.length()-1);
        }
        System.out.println(str);
    }

    @Test
    public void testTrimAll(){
//        String abc = " abc def ";
        String abc = "";
        System.out.println("abc = " + StringUtils.trimAllWhitespace(abc).length());
    }
}
