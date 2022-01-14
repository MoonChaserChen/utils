package ink.akira.utils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.util.*;

/**
 * Created by Allen on 2018/6/1.
 */
public class MapUtils {
    /**
     * 对对象的频率进行排序
     * @param lists
     * @param doDesc true：降序，false：升序
     * @return
     */
    public static List<Map.Entry<Object, Integer>> getFrequency(List lists, boolean doDesc){
        final int factor = doDesc ? -1 : 1;
        Multiset<Object> multiset = HashMultiset.create();
        multiset.addAll(lists);
        Map<Object, Integer> map = new HashMap<>();
        Set<Object> elementSet = multiset.elementSet();
        for (Object o : elementSet) {
            map.put(o,multiset.count(o));
        }
        List<Map.Entry<Object,Integer>> resultList = new ArrayList<>(map.entrySet());
        Collections.sort(resultList, new Comparator<Map.Entry<Object, Integer>>() {
            @Override
            public int compare(Map.Entry<Object, Integer> o1, Map.Entry<Object, Integer> o2) {
                return factor * (o1.getValue() - o2.getValue());
            }
        });
        return resultList;
    }

}
