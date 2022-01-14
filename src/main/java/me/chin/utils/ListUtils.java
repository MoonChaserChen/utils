package me.chin.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * List工具
 *
 * @author chenhouzhang
 * @date 2022/1/14 10:32
 */
public class ListUtils {
    /**
     * 对list进行分页返回数据
     *
     * @param list
     * @param pageNo 页码
     * @param pageSize 页size
     * @param <T> 数据类型
     * @return 分页数据，NotNull。
     * <p> ["a", "b", "c", "d", "e"],1,4 ==> ["a", "b", "c", "d"]
     * <p> ["a", "b", "c", "d", "e"],1,6 ==> ["a", "b", "c", "d", "e"]
     * <p> ["a", "b", "c", "d", "e"],2,3 ==> ["d", "e"]
     * <p> ["a", "b", "c", "d", "e"],3,2 ==> ["e"]
     * <p> ["a", "b", "c", "d", "e"],3,3 ==> []
     *
     */
    public static <T> List<T> getByPage(List<T> list, int pageNo, int pageSize) {
        if (list == null || list.size() == 0 || pageSize <= 0 || pageNo <= 0) {
            return new ArrayList<>();
        }
        int startIndexInclude = (pageNo - 1) * pageSize;
        if (startIndexInclude > list.size()) {
            return new ArrayList<>();
        }
        int endIndexExclude = startIndexInclude + pageSize;
        if (endIndexExclude > list.size()) {
            endIndexExclude = list.size();
        }
        return list.subList(startIndexInclude, endIndexExclude);
    }
}
