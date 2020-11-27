package cn.novisfff.javafx;

import java.util.Map;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/27
 * @description：
 * @modified By：
 * @version: $
 */

public class FxContainerPane {

    private Map<String, FxContainerNode<?>> nodeMap;

    void addNode(FxContainerNode<?> node) {
        nodeMap.put(node.getName(), node);
    }

}
