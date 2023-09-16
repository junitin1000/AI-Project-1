import java.util.ArrayList;

public class Minimax {



    public String bestMove(Board currentBoard) {

        ArrayList<Edge> moves = currentBoard.edges;
        return minimaxDecision(moves, currentBoard, true);

//        String bestMove = "";
//
//        return bestMove;
    }

    public String minimaxDecision(ArrayList<Edge> moves, Board board, Boolean isMaximizing){

        // if steve (player1)
        if(isMaximizing){
            for (Edge move : moves) {
               // String moveString = "steve " + move.row1 + "," + move.col1 + " " + move.row2 + "," + move.col2;
                Board newBoard = makeMove(move.row1, move.col1, move.row2, move.col2, board);
                newBoard.getScore();
            }

        }
        return "";
    }


    /**
     *
     * @return A copy of the board with the potential move made
     */
    private Board makeMove(int r1, int c1, int r2, int c2, Board currentBoard){
        Board potentialBoard = currentBoard;
        int distanceBetweenRows = Math.abs(r1-r2);
        int distanceBetweenCols = Math.abs(c1-c2);
        if (((distanceBetweenRows == 1) && (distanceBetweenCols == 0))){
            //Vertical Line
            Box leftBox = potentialBoard.getBox(Math.min(r1,r2),c1-1);
            Box rightBox = potentialBoard.getBox(Math.min(r1,r2),c1);

            leftBox.right = true;
            rightBox.left = true;

        } //Checks to see if rows or columns but not both are next to each other
        if(((distanceBetweenCols == 1) && (distanceBetweenRows == 0))) {
            //Horizontal Line
            Box aboveBox = potentialBoard.getBox(r1 - 1, Math.min(c1, c2));
            Box belowBox = potentialBoard.getBox(r1, Math.min(c1, c2));

            aboveBox.bottom = true;
            belowBox.top = true;
        }

        return potentialBoard;
    }

}