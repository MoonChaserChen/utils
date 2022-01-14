package ink.akira.utils;

import org.junit.Test;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static ink.akira.utils.ListUtils.getByPage;
import static org.junit.Assert.assertEquals;

/**
 * @author chenhouzhang
 * @date 2022/1/14 10:34
 */
public class ListUtilsTest {
    @Test
    public void testGetByPage() {
        List<String> list = newArrayList("a", "b", "c", "d", "e");
        assertEquals(newArrayList("a", "b", "c", "d"), getByPage(list, 1, 4));
        assertEquals(newArrayList("a", "b", "c", "d", "e"), getByPage(list, 1, 5));
        assertEquals(newArrayList("a", "b", "c", "d", "e"), getByPage(list, 1, 6));
        assertEquals(newArrayList("c", "d"), getByPage(list, 2, 2));
        assertEquals(newArrayList("d", "e"), getByPage(list, 2, 3));
        assertEquals(newArrayList("e"), getByPage(list, 2, 4));
        assertEquals(newArrayList(), getByPage(list, 2, 5));
        assertEquals(newArrayList(), getByPage(list, 2, 8));
        assertEquals(newArrayList(), getByPage(list, 6, 1));
        assertEquals(newArrayList(), getByPage(list, 3, 3));
        assertEquals(newArrayList(), getByPage(list, 0, 8));
        assertEquals(newArrayList(), getByPage(list, 0, 0));
        assertEquals(newArrayList(), getByPage(list, -1, 0));
        assertEquals(newArrayList(), getByPage(list, -1, 8));
        assertEquals(newArrayList(), getByPage(newArrayList(), 1, 8));
        assertEquals(newArrayList(), getByPage(null, 1, 8));
    }
}
