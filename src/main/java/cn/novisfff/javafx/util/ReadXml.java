package cn.novisfff.javafx.util;

import cn.novisfff.javafx.FxScanner;
import cn.novisfff.javafxExample.Main;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/29
 * @description：
 * @modified By：
 * @version: $
 */

public class ReadXml {

    public static List<XmlStructure> getStructure(List<File> xmlFiles) throws DocumentException {
        ArrayList<XmlStructure> xmlStructures = new ArrayList<>();
        for (File xmlFile : xmlFiles) {
            xmlStructures.add(resolveXml(xmlFile));
        }
        return xmlStructures;
    }

    private static XmlStructure resolveXml(File xmlFile) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlFile);
        Element rootElement = document.getRootElement();
        XmlStructure rootStructure = new XmlStructure(rootElement.getName());
        getXmlElementDfs(rootElement, rootStructure);
        return rootStructure;
    }

    private static void getXmlElementDfs(Element rootEle, XmlStructure rootStr) {
        for (Object elementObj : rootEle.elements()) {
            Element element = (Element) elementObj;
            XmlStructure xmlStructure = new XmlStructure(element.getName());
            rootStr.addChildren(xmlStructure);
            getXmlElementDfs(element, xmlStructure);
        }
    }

    public static void main(String[] args) throws Exception {
        FxScanner fxScanner = new FxScanner(Main.class);
        fxScanner.scan();

        List<File> xmlFiles = fxScanner.getXmlFiles();
        List<XmlStructure> structures = getStructure(xmlFiles);
        for (XmlStructure structure : structures) {
            System.out.println(structure);
        }
    }
}
