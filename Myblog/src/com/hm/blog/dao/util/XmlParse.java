package com.hm.blog.dao.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlParse {
    //缓存对象
    private Map<String, String> dbConfigs;

    public XmlParse(String fileName) {
        this.dbConfigs = new HashMap<>();
        //解析XML
        parse(fileName);
    }

    /**
     * 解析XML
     *
     * @param fileName
     */
    private void parse(String fileName) {
        try {
            SAXReader saxReader = new SAXReader();
            URL url = com.hm.blog.dao.util.XmlParse.class.getResource(fileName);
            Document doc = saxReader.read(url);
            //根元素
            Element rootEle = doc.getRootElement();
            List<Element> rootSubEle = rootEle.elements();
            for (Element e : rootSubEle) {
                this.dbConfigs.put(e.getName(), e.getText());
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得值
     */
    public String getVal(String key) throws Exception {
        if (this.dbConfigs.containsKey(key)) {
            return this.dbConfigs.get(key);
        }
        throw new Exception("没有你所要查找的key");
    }
}
