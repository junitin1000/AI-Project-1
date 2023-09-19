public class BestEdge {

    Edge edge;
    int value;

    public BestEdge(Edge theEdge, int theValue) {
        edge = theEdge;
        value = theValue;
    }

    public Edge getEdge() {
        return edge;
    }

    public void setEdge(Edge edge) {
        this.edge = edge;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
