package cn.novisfff.javafx;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.List;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/20
 * @description：
 * @modified By：
 * @version: $
 */

public class JavafxApplication {

    /**
     * root Pane
     */
    private static Pane root;

    /**
     * primaryStage
     */
    private static Stage primaryStage;

    /**
     * fxContainer, contain all FxNode
     */
    private static FxContainer fxContainer;

    /**
     * get root
     * @return Pane
     */
    public static synchronized Pane getRootInstance() {
        if (root == null) {
            throw new NullPointerException("root is null");
        }
        return root;
    }

    /**
     * get primaryStage
     * @return Stage
     */
    public static synchronized Stage getStageInstance() {
        if (primaryStage == null) {
            throw new NullPointerException("primaryStage is null");
        }
        return primaryStage;
    }

    /**
     * run JavaFxApplication
     * @param primarySources the Class of main
     * @param primaryStage primaryStage
     */
    public static synchronized void run(Class<?> primarySources, Stage primaryStage) {
        run(primarySources, primaryStage, null);
    }

    /**
     * run JavaFxApplication
     * @param primarySources the Class of main
     * @param primaryStage primaryStage
     * @param args args
     */
    public static synchronized void run(Class<?> primarySources, Stage primaryStage, String[] args) {

        root = new Pane();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        FxScanner fxScanner = new FxScanner(primarySources);
        fxContainer = FxContainer.getInstance();
        fxContainer.addPane(new FxContainerPane("root", root, null));

        try {
            fxScanner.scan();
            List<String> classPathList = fxScanner.getClassPathList();
            addPanes(classPathList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * add Panes to Container and Application
     * @param classPathList the .class file paths
     * @throws Exception
     */
    private static void addPanes(List<String> classPathList) throws Exception {
        for (String classPath : classPathList) {

            Class<?> aClass = Class.forName(classPath);

            FxPane fxPaneAnnotation = aClass.getDeclaredAnnotation(FxPane.class);
            if (fxPaneAnnotation != null) {

                Pane pane = getPane(aClass);
                FxContainerPane fxContainerPane = addPane(pane, fxPaneAnnotation);
                addNode(aClass, fxContainerPane);

            }
        }
    }

    /**
     *
     * @param aClass
     * @param fxContainerPane
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private static void addNode(Class<?> aClass, FxContainerPane fxContainerPane) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = aClass.getDeclaredMethods();
        Pane pane = fxContainerPane.getPane();
        for (Method method : methods) {
            FxNode fxNodeAnnotation = method.getDeclaredAnnotation(FxNode.class);
            if (fxNodeAnnotation != null) {
                System.out.println("识别到FxNode注解");
                method.setAccessible(true);
                Node node = (Node) method.invoke(pane, fxNodeAnnotation.parameter());
                fxContainerPane.addNode(new FxContainerNode<Node>(method.getName(), (Class<Node>) method.getReturnType(), fxNodeAnnotation.index(), new Node[]{node}));
            }
        }

        fxContainerPane.sort();

        for (FxContainerNode<?> fxContainerNode : fxContainerPane.getFxContainerNodes()) {
            if(fxContainerNode == null) {
                break;
            }
            for (Object node : fxContainerNode.getNodes()) {
                pane.getChildren().add((Node)node);
            }
        }

    }

    /**
     * add Pane to fxContainer and return FxContainerPane
     * @param pane Pane
     * @param fxPaneAnnotation
     * @return FxContainerPane
     * @throws Exception
     */
    private static FxContainerPane addPane(Pane pane, FxPane fxPaneAnnotation) throws Exception {
        FxContainerPane parentContainerPane = fxContainer.getPaneByName(fxPaneAnnotation.parentName());
        FxContainerPane fxContainerPane = new FxContainerPane(fxPaneAnnotation.name(), pane, parentContainerPane);
        fxContainer.addPane(fxContainerPane);
        parentContainerPane.getPane().getChildren().add(pane);
        System.out.println(fxPaneAnnotation.name() + "成功注入");
        return fxContainerPane;
    }

    /**
     * get Pane from class
     * @param aClass type
     * @return
     * @throws Exception
     */
    private static Pane getPane(Class<?> aClass) throws Exception {

        Pane pane = (Pane) aClass.newInstance();

        LoadFxml loadFxml = aClass.getDeclaredAnnotation(LoadFxml.class);

        if(loadFxml != null) {

            System.out.println("从Fxml初始化" + loadFxml.fxmlFile()); pane = pane == null ? (Pane) aClass.newInstance() : pane;
            FXMLLoader fxmlLoader = new FXMLLoader(pane.getClass().getResource(loadFxml.fxmlFile()));
            fxmlLoader.setRoot(pane);
            fxmlLoader.setController(pane);
            try {
                fxmlLoader.load();
            } catch (Exception exception) {
                throw new RuntimeException(exception);
            }
        }

        return pane;
    }

}
