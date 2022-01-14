package test.utils;

import me.chin.utils.MapUtils;

import java.util.*;

/**
 * Created by ChenHouZhang on 2017/7/26.
 */
public class MapUtilTest {

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
