package cn.novisfff.javafxExample.scene;

import cn.novisfff.javafx.FxScene;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * @author     ：zyf
 * @date       ：Created in 2020/11/20
 * @description：
 * @modified By：
 * @version:     $
 */

@FxScene(name = "testPane")
public class TestPane extends Pane {

    public TestPane() {
        init();
    }

    private void init() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("testPane.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
