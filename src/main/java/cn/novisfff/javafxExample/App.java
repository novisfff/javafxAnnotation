package cn.novisfff.javafxExample;

import cn.novisfff.javafx.JavafxApplication;
import javafx.application.Application;
import javafx.stage.Stage;

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
        JavafxApplication.run(this.getClass(), primaryStage);
    }

}
