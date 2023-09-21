//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Board {

    Box[][] gameBoard;
    ArrayList<Edge> edges;
    int score; // p1 # boxes captured (maximizing) -
    // p2 # boxes captured (minimizing)

    public Board() {
        score = 0;
        gameBoard = new Box[Main.boardSize][Main.boardSize];
        for (int i = 0; i < Main.boardSize; i++) {
            for (int j = 0; j < Main.boardSize; j++) {
                gameBoard[i][j] = new Box();
            }
        }
        initializeEdges();
    }

    public Board(Box[][] boxes) {
        this.gameBoard = boxes;
    }

    public void initializeEdges() {
        ArrayList<Edge> loedges = new ArrayList<Edge>();
        for (int i = 0; i <= Main.boardSize; i++) {
            for (int j = 0; j < Main.boardSize; j++) {
                loedges.add(new Edge(i, j, i, j + 1)); // horizontal edges
                loedges.add(new Edge(j, i, j + 1, i)); // vertical edges
            }
        }
        Collections.shuffle(loedges);
        edges = loedges;

    }


    public int getScore(String name) {
        int count = 0;
        for (int i = 0; i < Main.boardSize; i++) {
            for (int j = 0; j < Main.boardSize; j++) {
                Box aBox = gameBoard[i][j];
                if (aBox.takenBy.equals(name)) {
                    count++;
                } else if (!aBox.takenBy.equals("")) {
                    count--;
                }
            }
        }
        return count;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Box getBox(int row, int col) {
        return gameBoard[row][col];
    }

    public Board coolClone() {
        Board newBoard = new Board();
        int rows = this.gameBoard.length;
        int cols = this.gameBoard[0].length;
        newBoard.gameBoard = new Box[rows][cols];

        for (int i = 0; i < rows; i++) {
            System.arraycopy(gameBoard[i], 0, newBoard.gameBoard[i], 0, cols);
        }
        return newBoard;
    }

    public Board deepCopy() {
        // Implement a deep copy method for the Board.
        int numRows = gameBoard.length;
        int numCols = gameBoard[0].length;
        Box[][] newBoard = new Box[numRows][numCols];

        // Clone each Box object and populate the newBoxes array.
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                newBoard[i][j] = gameBoard[i][j].clone();
            }
        }
        return new Board(newBoard);
    }

}
