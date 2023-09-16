import java.util.ArrayList;

public class Minimax {


    /**
     * Returns the best move given a certain board state
     * ASSUMES: depth tree of length=5, maximizing player is playing
     * @param currentBoard is the games currentBoard state
     * @return playername row1,col1 row2,col2 best move to play
     */
    public Edge bestMove(Board currentBoard) {

        ArrayList<Edge> moves = currentBoard.edges;
        Edge decision = minimaxDecision(moves, currentBoard, true,2).getEdge();
        return "steve " + decision.row1 + "," + decision.col1 + " " + decision.row2 + "," + decision.col2;
}

    public BestEdge minimaxDecision(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int depth){
        Edge bestEdgeSoFar = null;
        if (depth == 0){
            return new BestEdge(bestEdgeSoFar, board.getScore());
        }
        int max = -81;
        int min = 81;
        if (isMaximizing){
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                int potentialMax = minimaxDecision(otherMoves, makeMove(move.row1, move.col1, move.row2, move.col2, board, "steve"), false, depth-1).getValue();
                if (potentialMax > max){
                    max = potentialMax;
                    bestEdgeSoFar = move;
                }
                if(min <= max) {
                    break;
                }
            }
            return new BestEdge(bestEdgeSoFar, max);
        }
        else{
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                int potentialMin = minimaxDecision(otherMoves, makeMove(move.row1, move.col1, move.row2, move.col2, board, "mind greg"), true, depth-1).getValue();
                if (potentialMin < min){
                    min = potentialMin;
                    bestEdgeSoFar = move;
                }
                if(min >= max) {
                    break;
                }
            }
            return new BestEdge(bestEdgeSoFar, min);
        }
    }
    /*
    public int minimaxDecision2(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int max, int min, int depth){

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
                if(min <= max) { //TODO
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
    }*/


    /**
     * makeMove clones the original board, then makes the move specified by r1, c1, r2, c2 on that cloned board
     * @return A copy of the board with the potential move made
     */
    private Board makeMove(int r1, int c1, int r2, int c2, Board currentBoard, String name){
        Board potentialBoard = currentBoard.deepCopy();
        Turn.updateBoard(r1, c1, r2, c2, potentialBoard, name);

        return potentialBoard;
    }

}
