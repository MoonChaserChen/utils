package ink.akira.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Created by ChenHouZhang on 2017/7/23.
 */
public class XMLUtil {
    /**
     * @param object 为空不字段将不会转化
     * @param classAlias 类型的别名，为空将不使用别名
     * @return
     */
    public static String Object2Xml(Object object, String classAlias){
        XStream xStream = new XStream(new DomDriver());
        if (classAlias != null){
            xStream.alias(classAlias,object.getClass());
        }
        return StringUtils.trimAllWhitespace(xStream.toXML(object));
    }

    public static <T> T Xml2Object(String xml, String classAlias ,T t){
        XStream xStream = new XStream(new DomDriver());
        //为类名取个别名
        xStream.alias(classAlias, t.getClass());
        //由于上面有<unknow>标签，忽略掉，否则会报错
        xStream.ignoreUnknownElements();
        return (T)xStream.fromXML(xml);
    }
}
