package ink.akira.utils;

/**
 * Created by Allen on 2018/8/31.
 */
public class EncodeUtilsTest {
    @org.junit.Test
    public void testEncode(){
        String encode = URLEncodeUtils.encode(" ");
        System.out.println("encode = " + encode);
    }

    @org.junit.Test
    public void testEncodeChinese(){
        String chinese = URLEncodeUtils.encodeChinese("http://www.baidu.com?param=中文");
        System.out.println("chinese = " + chinese);
    }

}
