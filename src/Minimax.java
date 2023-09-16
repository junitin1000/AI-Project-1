import java.util.ArrayList;

public class Minimax {



    public String bestMove(Board currentBoard) {

        ArrayList<Edge> moves = currentBoard.edges;
        return minimaxDecision(moves, currentBoard, true, -81, 81, 5);

//        String bestMove = "";
//        return bestMove;
    }

    public int minimaxDecision(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int max, int min, int depth){

        if (depth == 0){
            if (isMaximizing){
                if (board.getScore() > max)
                    max = board.getScore();
                return max;
            }
            if (board.getScore() < min)
                min = board.getScore();
            return min;

        }
        // if steve (player1)
        if(isMaximizing){
            for (Edge move : moves) {
                Board newBoard = makeMove(move.row1, move.col1, move.row2, move.col2, board);

                int score = minimaxDecision(moves, newBoard, !isMaximizing, max, min, depth-1);

                if (newBoard.getScore() > max)
                    max = newBoard.getScore();
                moves.remove(move);
                ;
                if(min <= max) {
                    break;
                }
            }
        }

        // minimizing player
        if(!isMaximizing){
            for (Edge move : moves) {
                Board newBoard = makeMove(move.row1, move.col1, move.row2, move.col2, board);
                if (newBoard.getScore() < min)
                    min = newBoard.getScore();
            }
        }
        return "";
    }


    /**
     * makeMove clones the original board, then makes the move specified by r1, c1, r2, c2 on that cloned board
     * @return A copy of the board with the potential move made
     */
    private Board makeMove(int r1, int c1, int r2, int c2, Board currentBoard){
        Board potentialBoard = currentBoard.coolClone();
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
