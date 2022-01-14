package ink.akira.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.*;

/**
 * Created by Allen on 2018/6/13.
 */
public class TransferUtils {
    public static Map<String, String> object2Map(Object object) {
        // TODO object to map
        return null;
    }

    /**
     * object 2 xml string
     * @param object
     * @return
     */
    public static String object2XmlString(Object object) {
        return object2XmlString(object, null);
    }

    /**
     * object 2 xml string
     * @param object
     * @param classAlias
     * @return
     */
    public static String object2XmlString(Object object, String classAlias) {
        XStream xstream = new XStream(new DomDriver());
        xstream.alias(classAlias, object.getClass());
        return xstream.toXML(object);
    }

    /**
     * map to Standard Xml with head: <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     * @param data
     * @return
     */
    public static String map2StandardXml(Map<String, String> data) {
        try (StringWriter writer = new StringWriter()) {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("xml");
            document.appendChild(root);
            for (String key: data.keySet()) {
                String value = data.get(key);
                if (value == null) {
                    value = "";
                }
                value = value.trim();
                Element filed = document.createElement(key);
                filed.appendChild(document.createTextNode(value));
                root.appendChild(filed);
            }
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.getBuffer().toString().replaceAll("[\n\r]", "");
        } catch (TransformerException | IOException | ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Standard Xml with head: <?xml version="1.0" encoding="UTF-8" standalone="no"?> to map
     * @param strXML
     * @return
     * @throws SAXException parse failed
     */
    public static Map<String, String> standardXml2Map(String strXML) throws SAXException {
        try(InputStream stream = new ByteArrayInputStream(strXML.getBytes("UTF-8"))) {
            Map<String, String> data = new HashMap<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
            Document doc = documentBuilder.parse(stream);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getDocumentElement().getChildNodes();
            for (int idx=0; idx<nodeList.getLength(); ++idx) {
                Node node = nodeList.item(idx);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    data.put(element.getNodeName(), element.getTextContent());
                }
            }
            return data;
        }catch (ParserConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 请求参数转化为Map
     * @param requestParams request params get from HttpservletRequest
     * @param separator separator for multi values
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
     * @param requestParams request params get from HttpservletRequest
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
        if (paramMap == null) {
            return "";
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>();
        Iterator<String> iterator = paramMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            keys.add(key);
        }
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = paramMap.get(key);
            if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            }
        }
        return content.toString();
    }
}
