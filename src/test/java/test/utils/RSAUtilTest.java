package test.utils;

import me.chin.utils.RsaCheckUtil;
import me.chin.utils.RsaSignUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenHouZhang on 2017/7/26.
 */
public class RSAUtilTest {
    @Test
    public void testRsaSign() throws Exception {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("param1","param1");
        paramMap.put("param2","param2");
//        paramMap.put("charset","utf-8");
//        paramMap.put("sign_type","RSA2");
        Map<String, String> resultMap = RsaSignUtil.rsaSign(paramMap);
        boolean b = RsaCheckUtil.rsaCheck(resultMap);
        System.out.println("b = " + b);
        System.out.println("++++++++++++++++");
    }
}
