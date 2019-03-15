package com.lynu.yzshopping.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import org.apache.log4j.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.DomSerializer;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * @program: data-interface
 * @description: 网页解析根据类
 * @author: houyu
 * @create: 2018-12-07 00:47
 */
public class ParseUtil {

    private Logger logger = Logger.getLogger(getClass());
    private CommonUtil commonUtil = CommonUtil.get();

//-------------------------------------------------------------JSON----------------------------------------------------------------//

    /**
     * mapToJSONObject
     */
    public JSONObject mapToJSONObject(Map<String, Object> map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(map);
        return jsonObject;
    }

    /**
     * jsonObjectToMap
     */
    public Map<String, Object> jsonObjectToMap(JSONObject jsonObject) {
        return jsonObject;
    }

    /**
     * 使用 fastJson JSONPath
     *
     * @param object fastjson.object/String(fastJson.JSONObject)
     * @param path   路径 example:"$..id"
     */
    public Object xpathFastJsonPath(Object object, String path) {

        if (object instanceof String) {
            return JSONPath.eval(JSON.parse((String) object), path);
        } else if (object instanceof JSONObject) {
            return JSONPath.eval(object, path);
        } else if (object instanceof JSONArray) {
            return JSONPath.eval(object, path);
        }
        return JSONPath.eval(object, path);
    }


    /**
     * 根据jsonKey获取List
     * <br>JSONPath.eval(object, "$.." + jsonKey);
     *
     * @param object fastjson JSONObject / JSONArray / String
     * @return
     */
    public List<Object> jsonKeyToList(Object object, String jsonKey) {
        List<Object> values;
        try {
            values = (List<Object>) xpathFastJsonPath(object, "$.." + jsonKey);
        } catch (Exception e) {
            e.printStackTrace();
            values = new ArrayList<>();
        }
        return values;
    }

    /**
     * 解析json to List_Map
     *
     * @param object fastjson JSONObject / JSONArray / String
     * @param mapper 映射 jsonKey : dbKey
     * @return
     */
    public List<Map> jsonToListMaps(Object object, Map<String, String> mapper) {
        Set<String> jsonKeySet = mapper.keySet();
        List<String> titleList = new ArrayList<>(mapper.values());
        if (object instanceof String) {
            object = JSON.parse((String) object);
        }

        List<List> lists = new ArrayList<>();// 这个是存储多行多列的数据了
        int length = -1;
        for (String jsonOneKey : jsonKeySet) {
            List<Object> keyToList = jsonKeyToList(object, jsonOneKey);
            if (length == -1) {
                length = keyToList.size();
            }
            if (length != keyToList.size()) {
                for (int i = 0; i < length - 1; i++) {
                    keyToList.add(keyToList.get(0));
                }
            }
            lists.add(keyToList);
        }
        Map map = null;
        List<Map> listMaps = new ArrayList<>();

        List<List> listsRotate = commonUtil.listsRotate(lists);// 反转List
        for (List list : listsRotate) {
            map = commonUtil.collectionsToMap(titleList, list);
            listMaps.add(map);
        }
        return listMaps;
    }

//-------------------------------------------------------------JSON----------------------------------------------------------------//

//------------------------------------------------------------Jsoup----------------------------------------------------------------//

//------------------------------------------------------------Jsoup-----------------------------------------------------------------//

//------------------------------------------------------------xpath-----------------------------------------------------------------//

    /**
     * example :
     *
     *   Element element = (Element) xPath.evaluate("//*[@id=\"u1\"]/a[5]", document, XPathConstants.NODE);
     *   System.out.println("-->>>" + element.getTextContent());
     *
     *   NodeList nodeList = (NodeList)  xPath.evaluate("//*[@id=\"u1\"]/a", document, XPathConstants.NODESET);
     *   String s = nodeList.item(4).getTextContent();
     *   System.out.println("-->>>" + s);
     */
    public XPath getXPath() {
        return XPathFactory.newInstance().newXPath();
    }

    private HtmlCleaner htmlCleaner = new HtmlCleaner();
    private DomSerializer domSerializer = new DomSerializer(new CleanerProperties());

    /**
     * html 转 org.w3c.dom.Document
     */
    public org.w3c.dom.Document getW3cDocument(String html) {
        TagNode tagNode = htmlCleaner.clean(html);
        try {
            return domSerializer.createDOM(tagNode);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            return null;        }
    }

//------------------------------------------------------------xpath-----------------------------------------------------------------//

//------------------------------------------------------------String-----------------------------------------------------------------//

    /**
     * 获取区域String包含keys的List_String
     *
     * @param s             整体String
     * @param open          开始串
     * @param close         关闭串
     * @param containKeys   包含的key
     * @return
     */
    public List<String> getSectionStrings(String s, String open, String close, String... containKeys) {

        List<String> substringsBetween = commonUtil.subStringsBetween(s, open, close);
        List<String> list = new ArrayList<>();
        if (substringsBetween != null) {
            if (containKeys != null) {
                for (String str : substringsBetween) {
                    boolean flag = true;
                    for (String key : containKeys) {
                        flag &= str.contains(key);
                    }
                    if (flag) {
                        list.add(String.format("%s%s%s", open, str, close));
                    }
                }
            } else {
                return substringsBetween;
            }
        }
        return list;
    }

    /**
     * 将HTML转换为纯文本
     *
     * @param htmlString
     * @return
     */
    public String htmlToString(String htmlString, String... regex) {
        String htmlStr = htmlString; // 含html标签的字符串
        String textStr = "";
        Pattern p_script;
        java.util.regex.Matcher m_script;
        Pattern p_style;
        java.util.regex.Matcher m_style;
        Pattern p_html;
        java.util.regex.Matcher m_html;
        Pattern p_html1;
        java.util.regex.Matcher m_html1;

        try {
            String regEx_script = "<[//s]*?script[^>]*?>[//s//S]*?<[//s]*?///[//s]*?script[//s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[//s//S]*?<///script>
            String regEx_style = "<[//s]*?style[^>]*?>[//s//S]*?<[//s]*?///[//s]*?style[//s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[//s//S]*?<///style>
            String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); // 过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); // 过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); // 过滤html标签

            p_html1 = Pattern.compile(regEx_html1, Pattern.CASE_INSENSITIVE);
            m_html1 = p_html1.matcher(htmlStr);
            htmlStr = m_html1.replaceAll(""); // 过滤html标签

            textStr = htmlStr.replaceAll("&ldquo;|&nbsp;", "");
            if (regex != null) {
                for (String re : regex) {
                    textStr = htmlStr.replaceAll(re, "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;// 返回文本字符串
    }

//------------------------------------------------------------String-----------------------------------------------------------------//

    /**--------------------------------------------------------------------------------------*/
    private static class SingletonHolder {
        private static final ParseUtil INSTANCE = new ParseUtil();
    }

    public static ParseUtil get() {
        return SingletonHolder.INSTANCE;
    }
    /**--------------------------------------------------------------------------------------*/


}
