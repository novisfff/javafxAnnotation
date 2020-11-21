package cn.novisfff.javafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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

    private static Stage primaryStage;

    public static synchronized Pane getRootInstance() {
        if (root == null) {
            throw new NullPointerException("root is null");
        }
        return root;
    }

    public static synchronized Stage getStageInstance() {
        if (primaryStage == null) {
            throw new NullPointerException("primaryStage is null");
        }
        return primaryStage;
    }

    public static synchronized void run(Class<?> primarySources, Stage primaryStage) {
        run(primarySources, primaryStage, null);
    }

    public static synchronized void run(Class<?> primarySources, Stage primaryStage, String[] args) {

        root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        FxScanner fxScanner = new FxScanner(primarySources);
        try {
            fxScanner.scan();
            List<String> classPathList = fxScanner.getClassPathList();
            for (String s : classPathList) {
                Class<?> aClass = Class.forName(s);

                Pane pane = null;

                LoadFxml loadFxml = aClass.getDeclaredAnnotation(LoadFxml.class);

                if(loadFxml != null) {
                    System.out.println("从Fxml初始化" + loadFxml.fxmlFile());
                    pane = pane == null ? (Pane) aClass.newInstance() : pane;

                    FXMLLoader fxmlLoader = new FXMLLoader(pane.getClass().getResource(loadFxml.fxmlFile()));
                    fxmlLoader.setRoot(pane);
                    fxmlLoader.setController(pane);
                    try {
                        fxmlLoader.load();
                    } catch (IOException exception) {
                        throw new RuntimeException(exception);
                    }

                }

                FxPane annotation = aClass.getDeclaredAnnotation(FxPane.class);
                if (annotation != null) {
                    System.out.println(annotation.name() + "成功注入");
                    pane = pane == null ? (Pane) aClass.newInstance() : pane;
                    root.getChildren().add(pane);
                    Method[] methods = aClass.getDeclaredMethods();
                    for (Method method : methods) {
                        FxNode fxNode = method.getDeclaredAnnotation(FxNode.class);
                        if (fxNode != null) {
                            System.out.println("识别到FxNode注解");
                            method.setAccessible(true);
                            Node node = (Node) method.invoke(pane, fxNode.parameter());
                            pane.getChildren().add(node);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
