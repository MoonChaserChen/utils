package moon.chaser.utils;

import com.google.common.collect.*;

import java.util.*;

/**
 * Created by ChenHouZhang on 2017/7/26.
 */
public class MapUtil {
    /**
     * 请求参数转化为Map
     * @param requestParams
     * @param separator
     * @return
     */
    public static Map<String, String> requestParams2Map(Map<String, String[]> requestParams, String separator) {
        Map<String, String> params = new HashMap();
        String name;
        String valueStr;
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); params.put(name, valueStr)) {
            name = (String) iter.next();
            String[] values = requestParams.get(name);
            valueStr = "";
            for (int i = 0; i < values.length; ++i) {
                valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + separator;
            }
        }
        return params;
    }

    /**
     * 请求参数转化为Map
     * @param requestParams
     * @return
     */
    public static Map<String, String> requestParams2Map(Map<String, String[]> requestParams) {
        return requestParams2Map(requestParams, ",");
    }

    /**
     * Map转化为有序String
     * @param paramMap
     * @return
     */
    public static String map2OrderedString(Map<String, String> paramMap) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>();
        Iterator<String> iterator = paramMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            keys.add(key);
        }
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = paramMap.get(key);
            if (!StringUtil.isEmpty(key) && !StringUtil.isEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 对对象的频率进行排序
     * @param lists
     * @param doDesc true：降序，false：升序
     * @return
     */
    public static List<Map.Entry<Object, Integer>> getFrequency(List lists,boolean doDesc){
        final int factor = doDesc ? -1 : 1;
        Multiset<Object> multiset = HashMultiset.create();
        for (Object object : lists) {
            multiset.add(object);
        }
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
