package test.utils;

import java.util.*;

/**
 * Created by ChenHouZhang on 2017/7/26.
 */
public class MapUtilTest {
    @org.junit.Test
    public void testMapChange(){
        Map<String,String[]> map = new HashMap<>();
        map.put("a",new String[]{"1","first"});
        map.put("b",new String[]{"2","second"});
        Map<String, String> params2Map =MapUtils.requestParams2Map(map,"-");
        Iterator<String> iterator = params2Map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            String value = params2Map.get(key);
            System.out.println(key + " --- " + value);
        }
    }

    @org.junit.Test
    public void countWordFrequency(){
        List<String> list = new ArrayList<String>();
        list.add("abc");
        list.add("abc");
        list.add("gh");
        list.add("gh");
        list.add("def");
        list.add("def");
        list.add("def");
        list.add("def");
        list.add("i");
        List<Map.Entry<Object, Integer>> frequencyDesc =MapUtils.getFrequency(list,false);
        System.out.println(frequencyDesc);
    }
}
