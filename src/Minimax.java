import java.util.ArrayList;

public class Minimax {

    public double timeLimit = 10;

    /**
     * Returns the best move given a certain board state
     * ASSUMES: depth tree of length=5, maximizing player is playing
     * @param currentBoard is the games currentBoard state
     * @return playername row1,col1 row2,col2 best move to play
     */
    public Edge bestMove(Board currentBoard) {

        ArrayList<Edge> moves = currentBoard.edges;
        double start = 0;
        BestEdge decision = minimaxDecision(moves, currentBoard, true,6);
        System.out.println("Steve picked with value of " + decision.getValue());
        return decision.getEdge();
}

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
                //TODO if move causes a move that will complete a square, minimaxDecision isMaximizing is true
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
