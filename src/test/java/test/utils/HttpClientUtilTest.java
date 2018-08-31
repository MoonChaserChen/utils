package test.utils;

import me.chin.utils.HttpClientUtil;
import me.chin.utils.JsonUtil;
import me.chin.utils.RsaSignUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by ChenHouZhang on 2017/7/23.
 */
public class HttpClientUtilTest {
    @Test
    public void sendTest() {
        Map<String, Object> resultMap = JsonUtil.jsonStr2Map(HttpClientUtil.getInstance().sendHttpPost("http://localhost:8082/note/testHttpClient", "param1=param1&param2=param2"));
        Iterator<String> iterator = resultMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key:"+ key + ",value:" + resultMap.get(key));
        }
    }

    @Test
    public void sendTest1() {
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("param1","param1");
        paramMap.put("param2","param2");
        Map<String, Object> resultMap = JsonUtil.jsonStr2Map(HttpClientUtil.getInstance().sendHttpPost("http://localhost:8082/note/testHttpClient", paramMap));
        Iterator<String> iterator = resultMap.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key:"+ key + ",value:" + resultMap.get(key));
        }
    }

    @Test
    public void rsaSendTest() throws Exception {
        Map<String ,String> paramMap = new HashMap<>();
        paramMap.put("param1","param1");
        paramMap.put("param2","param2");
        Map<String, String> map = RsaSignUtil.rsaSign(paramMap);
        Map<String, Object> resultMap = JsonUtil.jsonStr2Map(HttpClientUtil.getInstance().sendHttpPost("http://localhost:8082/note/testRSA", map));
        for (String s : resultMap.keySet()) {
            System.out.println(s + "," + resultMap.get(s));
        }
    }

    @Test
    public void rsaFalseSendTest() throws Exception {
        Map<String ,String> paramMap = new HashMap<>();
        paramMap.put("param1","param1");
        paramMap.put("param2","param2");
        HttpClientUtil.getInstance().sendHttpPost("http://localhost:8082/note/testRSA",paramMap);
    }
}
