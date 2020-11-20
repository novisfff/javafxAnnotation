package cn.novisfff.javafxExample;

import cn.novisfff.javafx.FxScanner;
import cn.novisfff.javafx.FxScene;
import cn.novisfff.javafxExample.scene.TestPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/19
 * @description：
 * @modified By：
 * @version: $
 */

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        FxScanner fxScanner = new FxScanner(Main.class);
        try {
            fxScanner.scan();
            List<String> classPathList = fxScanner.getClassPathList();
            for (String s : classPathList) {
                Class<?> aClass = Class.forName(s);
                FxScene annotation = aClass.getDeclaredAnnotation(FxScene.class);
                if(annotation != null) {
                    System.out.println(annotation.name() + "成功注入");
                    root.getChildren().add((Node)aClass.newInstance());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
