package cn.novisfff.javafx.util;

import cn.novisfff.javafx.FxScanner;
import cn.novisfff.javafxExample.Main;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/29
 * @description：
 * @modified By：
 * @version: $
 */

public class ReadXml {

    public static void main(String[] args) throws Exception {
        FxScanner fxScanner = new FxScanner(Main.class);
        fxScanner.scan();

        List<File> xmlFiles = fxScanner.getXmlFiles();

        File file = xmlFiles.get(0);

        SAXReader reader = new SAXReader();

        Document document = reader.read(file);
        Element rootElement = document.getRootElement();
        System.out.println(rootElement.getName());
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            System.out.println(element.getName());
        }

    }
}
