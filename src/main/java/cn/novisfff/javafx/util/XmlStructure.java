package cn.novisfff.javafx.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/30
 * @description：
 * @modified By：
 * @version: $
 */

public class XmlStructure {

    private String value;

    private List<XmlStructure> children;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<XmlStructure> getChildren() {
        return children;
    }

    public void addChildren(XmlStructure child) {
        if(this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public XmlStructure(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "XmlStructure{" +
                "value='" + value + '\'' +
                ", children=" + children +
                '}';
    }
}
