package cn.novisfff.javafx;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/21
 * @description：
 * @modified By：
 * @version: $
 */

public class FxContainer {

    private static FxContainer fxContainer;

    static {
        if(fxContainer == null) {
            fxContainer = new FxContainer();
        }
    }

    private FxContainer() {

    }

    public static FxContainer getInstance() {
        return fxContainer;
    }

}
