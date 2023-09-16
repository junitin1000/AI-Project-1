import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    Box[][] gameBoard;
    ArrayList<Edge> edges;
    int score; // p1 # boxes captured (maximizing) -
    // p2 # boxes captured (minimizing)

    public Board(){
        score = 0;
        gameBoard = new Box[9][9];
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                gameBoard[i][j] = new Box();
            }
        }
        initializeEdges();
    }

    public void initializeEdges(){
        edges = new ArrayList<Edge>();
        for(int i=0; i<=9; i++){
            for(int j=0; j<9; j++){
                edges.add(new Edge(i, j, i, j+1)); // horizontal edges
                edges.add(new Edge(j, i, j+1, i)); // vertical edges
            }
        }
    }


    public int getScore() {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Box aBox = gameBoard[i][j];
                if(aBox.takenBy.equals("steve")){
                    count++;
                } else if (!aBox.takenBy.equals("")){
                    count--;
                }
            }
        }
        return count;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public Box getBox(int row, int col){
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
        System.out.println(Arrays.deepToString(newBoard.gameBoard));
        return newBoard;
    }

}
