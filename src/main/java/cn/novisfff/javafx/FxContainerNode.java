package cn.novisfff.javafx;

public class FxContainerNode<T> {

    private String name;

    private Class<T> classType;

    private T[] nodes;

    private int index;

    public FxContainerNode(String name, Class<T> classType, T[] nodes, int index) {
        this.name = name;
        this.classType = classType;
        this.nodes = nodes;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<T> getClassType() {
        return classType;
    }

    public void setClassType(Class<T> classType) {
        this.classType = classType;
    }

    public T[] getNodes() {
        return nodes;
    }

    public void setNodes(T[] nodes) {
        this.nodes = nodes;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
