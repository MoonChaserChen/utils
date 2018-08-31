package test.utils;

import me.chin.utils.JsonUtil;

import java.util.Map;

/**
 * Created by ChenHouZhang on 2017/8/6.
 */
public class JsonUtilTest {
    @org.junit.Test
    public void testStrJson2Map(){
        String str = "{\n" +
                "    \"status\": 1,\n" +
                "    \"msg\": null,\n" +
                "    \"data\": {\n" +
                "        \"school\": \"青岛农业大学\",\n" +
                "        \"cetX\": \"英语六级\",\n" +
                "        \"name\": \"谢丹\",\n" +
                "        \"penResult\": {\n" +
                "            \"penTicketNumber\": \"370580162206508\",\n" +
                "            \"totalGrade\": \"424\",\n" +
                "            \"writingTranslatingGrade\": \"129\",\n" +
                "            \"readingGrade\": \"137\",\n" +
                "            \"listeningGrade\": \"158\"\n" +
                "        },\n" +
                "        \"oralResult\": {\n" +
                "            \"oralGrade\": \"B\",\n" +
                "            \"oralTicketNumber\": \"S16437001010762\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Map<String, Object> map = JsonUtil.jsonStr2Map(str);
        for (String key : map.keySet()) {
            System.out.println(key + "," + map.get(key));
        }
    }
}
