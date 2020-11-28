package cn.novisfff.javafx;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/21
 * @description：
 */

public class FxContainer {

    private static FxContainer fxContainer;

    private Map<String, FxContainerPane> paneMap;

    static {
        if(fxContainer == null) {
            fxContainer = new FxContainer();
        }
    }

    private FxContainer() {
        paneMap = new HashMap<>();
    }

    static FxContainer getInstance() {
        return fxContainer;
    }

    void addPane(FxContainerPane pane) {
        if(pane.getName() != "root") {
            pane.getParent().getChildren().add(pane);
        }
        paneMap.put(pane.getName(), pane);
    }

    FxContainerPane getPaneByName(String name) {
        return paneMap.get(name);
    }

}
