package cn.novisfff.javafxExample;

import cn.novisfff.javafx.FxScanner;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //Application.launch(App.class);
        FxScanner fxScanner = new FxScanner(Main.class);
        try {
            fxScanner.scan();
            List<String> classPathList = fxScanner.getClassPathList();
            for (String s : classPathList) {
                Class<?> aClass = Class.forName(s);
                System.out.println(aClass.getName());
                for (Method method : aClass.getDeclaredMethods()) {
                    System.out.println(method.getName());
                }
                System.out.println("----------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
