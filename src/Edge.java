import jdk.nashorn.internal.objects.annotations.Getter;

public class Edge {
    int row1;
    int col1;
    int row2;
    int col2;

    public Edge(int row1, int col1, int row2, int col2) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;

        // print the edge to the console
        printEdge();
    }

    public void printEdge() {
        System.out.println("New Edge: (" + row1 + ", " + col1 + ") (" + row2 + ", " + col2 + ")");
    }
}
