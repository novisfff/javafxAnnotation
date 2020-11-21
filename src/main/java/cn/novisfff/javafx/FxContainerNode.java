package cn.novisfff.javafx;

public class FxContainerNode<T> {

    private String name;

    private Class<T> classType;

    private T node;

    private T[] nodes;

    private int index;

    public FxContainerNode(String name, Class<T> classType, T node, int index) {
        this.name = name;
        this.classType = classType;
        this.node = node;
        this.index = index;
    }

    public FxContainerNode(String name, Class<T> classType, T[] nodes, int index) {
        this.name = name;
        this.classType = classType;
        this.nodes = nodes;
        this.index = index;
    }

}
