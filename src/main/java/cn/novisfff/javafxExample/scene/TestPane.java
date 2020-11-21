package cn.novisfff.javafxExample.scene;

import cn.novisfff.javafx.FxNode;
import cn.novisfff.javafx.FxPane;
import cn.novisfff.javafx.LoadFxml;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


import java.io.IOException;

/**
 * @author     ：zyf
 * @date       ：Created in 2020/11/20
 * @description：
 * @modified By：
 * @version:     $
 */

@FxPane(name = "testPane")
@LoadFxml(fxmlFile = "testPane.fxml")
public class TestPane extends FlowPane {

    @FxNode(parameter = {"123"})
    private Node test(String s) {
        Label label = new Label(s);
        return label;
    }

    @FxNode(parameter = {"456"})
    private Node abc4(String s) {
        Label label = new Label(s);
        return label;
    }

    @FxNode(parameter = {"789"})
    private Node abc5(String s) {
        Label label = new Label(s);
        return label;
    }

    @FxNode
    private Node nnn() {
        CheckBox checkBox = new CheckBox();
        return checkBox;
    }

}
