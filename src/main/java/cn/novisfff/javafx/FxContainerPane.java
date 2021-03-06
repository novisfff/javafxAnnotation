package cn.novisfff.javafx;

import javafx.scene.layout.Pane;

import java.util.*;

/**
 * @author ：novisfff
 * @date ：Created in 2020/11/27
 * @description：
 * @modified By：
 * @version: $
 */

public class FxContainerPane {

    private static final int INIT_CAP = 16;

    private int size = 0;

    private Map<String, FxContainerNode<?>> nodeMap;

    private FxContainerNode<?>[] fxContainerNodes = new FxContainerNode[INIT_CAP];

    private String name;

    private FxContainerPane parent;

    private List<FxContainerPane> children;

    private Pane pane;

    FxContainerPane(String name, Pane pane,FxContainerPane parent) {
        this.name = name;
        this.pane = pane;
        this.parent = parent;
        this.children = new ArrayList<>();
        nodeMap = new HashMap<>();
    }

    void sort() {
        Arrays.sort(fxContainerNodes, 0, size);
    }

    void addNode(FxContainerNode<?> node) {
        nodeMap.put(node.getName(), node);
        if(size >= fxContainerNodes.length - 1) {
            resize();
        }
        fxContainerNodes[size] = node;
        size++;
    }

    private void resize() {
        int oldSize = fxContainerNodes.length;
        int newSize = oldSize << 1;
        FxContainerNode<?>[] newNodes = new FxContainerNode<?>[newSize];
        System.arraycopy(fxContainerNodes, 0, newNodes, 0, oldSize);
        fxContainerNodes = newNodes;
    }

    public Map<String, FxContainerNode<?>> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, FxContainerNode<?>> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FxContainerPane getParent() {
        return parent;
    }

    public void setParent(FxContainerPane parent) {
        this.parent = parent;
    }

    public List<FxContainerPane> getChildren() {
        return children;
    }

    public void setChildren(List<FxContainerPane> children) {
        this.children = children;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public FxContainerNode<?>[] getFxContainerNodes() {
        return fxContainerNodes;
    }

    public void setFxContainerNodes(FxContainerNode<?>[] fxContainerNodes) {
        this.fxContainerNodes = fxContainerNodes;
    }

    public int size() {
        return size;
    }
}
