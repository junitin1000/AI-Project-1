import java.util.ArrayList;

public class Minimax {

    int depth = 4;

    public double timeLimit = 10;

    /**
     * Returns the best move given a certain board state
     * ASSUMES: depth tree of length=5, maximizing player is playing
     * @param currentBoard is the games currentBoard state
     * @return playername row1,col1 row2,col2 best move to play
     */
    public Edge bestMove(Board currentBoard, String name) {

        ArrayList<Edge> moves = currentBoard.edges;
        double start = 0;
        //BestEdge decision = minimaxDecision(moves, currentBoard, true,5);
        BestEdge decision = minimaxAB(moves, currentBoard, true,depth,Integer.MIN_VALUE, Integer.MAX_VALUE, name);
        System.out.println(name + " picked with value of " + decision.getValue());
        return decision.getEdge();
}
/*

    */
/**
     * Recursive alg that Decides the BestEdge
     * @param moves ArrayList<Edge> of all possible moves remaining
     * @param board is the currect game Board
     * @param isMaximizing is if the player is the maximizing player
     * @param depth is how deep in th tree to look before cutting off
     * @return the BestEdge (which edge to pick along with the value of why we chose it)
     *//*

    public BestEdge minimaxDecision(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int depth){
        Edge bestEdgeSoFar = null;
        if (depth == 0 || moves.size() == 0){
            return new BestEdge(bestEdgeSoFar, board.getScore());
        }
        int max = -81;
        int min = 81;
        if (isMaximizing){
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                Board potentialBoard = board.deepCopy();
                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, "steve");
                int potentialMax = minimaxDecision(otherMoves, potentialBoard, completed, depth-1).getValue();
                if (potentialMax > max){
                    max = potentialMax;
                    bestEdgeSoFar = move;
                }
//                if(min <= max) {
//                    break;
//                }
            }
            return new BestEdge(bestEdgeSoFar, max);
        }
        else{
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                Board potentialBoard = board.deepCopy();
                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, "mind greg");
                int potentialMin = minimaxDecision(otherMoves, potentialBoard, !completed, depth-1).getValue();
                if (potentialMin < min){
                    min = potentialMin;
                    bestEdgeSoFar = move;
                }
//                if(min >= max) {
//                    break;
//                }
            }
            return new BestEdge(bestEdgeSoFar, min);
        }
    }
*/


    /**
     * MinimaxDecision but with alpha beta pruning
     * alpha is the best maximizer currently guaranteed
     * beta is the best minimizer currently guaranteed
     */
    public BestEdge minimaxAB(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int depth, int alpha, int beta, String name){
        Edge bestEdgeSoFar = null;
        if (depth == 0 || moves.size() == 0){
            return new BestEdge(bestEdgeSoFar, board.getScore(name));
        }
        int max = -81;
        int min = 81;
        if (isMaximizing){
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                Board potentialBoard = board.deepCopy();
                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, name);
                int potentialMax = minimaxAB(otherMoves, potentialBoard, completed, depth-1, alpha, beta, name).getValue();
                if (potentialMax > max){
                    max = potentialMax;
                    bestEdgeSoFar = move;
                }
                alpha = Math.max(alpha, potentialMax);
                if (beta <= alpha){
                    break;
                }

            }
            return new BestEdge(bestEdgeSoFar, max);
        }
        else{
            for (Edge move : moves){
                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
                otherMoves.remove(move);
                Board potentialBoard = board.deepCopy();
                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, "mind greg");
                int potentialMin = minimaxAB(otherMoves, potentialBoard, !completed, depth-1, alpha, beta, name).getValue();
                if (potentialMin < min){
                    min = potentialMin;
                    bestEdgeSoFar = move;
                }
                beta = Math.min(beta, potentialMin);
                if (beta <= alpha){
                    break;
                }
            }
            return new BestEdge(bestEdgeSoFar, min);
        }
    }

    /**
     * makeMove clones the original board, then makes the move specified by r1, c1, r2, c2 on that cloned board
     * @return boolean stating if it catches a box
     * Additionally: potentialBoard inputted will be changed with the potential move made
     */
    private boolean makeMove(int r1, int c1, int r2, int c2, Board potentialBoard, String name){
        boolean completed = Turn.updateBoard(r1, c1, r2, c2, potentialBoard, name, false);
        return completed;
    }

}
