package cn.novisfff.javafx;

import cn.novisfff.javafxExample.Main;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/20
 * @description：
 * @modified By：
 * @version: $
 */

public class JavafxApplication {

    private static Pane root;

    public static synchronized Pane getRootInstance() {
        if(root == null) {
            throw new NullPointerException("root is null");
        }
        return root;
    }

    public static synchronized void run(Class<?> primarySources, Stage primaryStage) {
        run(primarySources, primaryStage, null);
    }

    public static synchronized void run(Class<?> primarySources, Stage primaryStage, String[] args) {

        root = new Pane();
        Scene scene = new Scene(root, 800, 480);
        primaryStage.setScene(scene);
        primaryStage.show();

        FxScanner fxScanner = new FxScanner(primarySources);
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
