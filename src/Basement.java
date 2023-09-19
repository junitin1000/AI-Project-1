import java.util.ArrayList;

public class Basement {

//    int depth = 5;
//
//    //TODO From Minimax
//    /**
//     * Returns the best move given a certain board state
//     * ASSUMES: depth tree of length=5, maximizing player is playing
//     * @param currentBoard is the games currentBoard state
//     * @return playername row1,col1 row2,col2 best move to play
//     */
//    public Edge bestMoveNonIterative(Board currentBoard, String name) {
//
//        ArrayList<Edge> moves = currentBoard.edges;
//        //BestEdge decision = minimaxDecision(moves, currentBoard, true,5);
//        BestEdge decision = minimaxAB(moves, currentBoard, true, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, name,0);
//        System.out.println(name + " picked with value of " + decision.getValue());
//        return decision.getEdge();
//    }
//
//    /**
//            * Recursive alg that Decides the BestEdge
//     * @param moves ArrayList<Edge> of all possible moves remaining
//     * @param board is the currect game Board
//     * @param isMaximizing is if the player is the maximizing player
//     * @param depth is how deep in th tree to look before cutting off
//     * @return the BestEdge (which edge to pick along with the value of why we chose it)
//     */
//
//    public BestEdge minimaxDecision(ArrayList<Edge> moves, Board board, Boolean isMaximizing, int depth, String name){
//        Edge bestEdgeSoFar = null;
//        if (depth == 0 || moves.size() == 0){
//            return new BestEdge(bestEdgeSoFar, board.getScore(name));
//        }
//        int max = -81;
//        int min = 81;
//        if (isMaximizing){
//            for (Edge move : moves){
//                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
//                otherMoves.remove(move);
//                Board potentialBoard = board.deepCopy();
//                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, "steve");
//                int potentialMax = minimaxDecision(otherMoves, potentialBoard, completed, depth-1, name).getValue();
//                if (potentialMax > max){
//                    max = potentialMax;
//                    bestEdgeSoFar = move;
//                }
//            }
//            return new BestEdge(bestEdgeSoFar, max);
//        }
//        else{
//            for (Edge move : moves){
//                @SuppressWarnings("unchecked") ArrayList<Edge> otherMoves = (ArrayList<Edge>) moves.clone();
//                otherMoves.remove(move);
//                Board potentialBoard = board.deepCopy();
//                boolean completed = makeMove(move.row1, move.col1, move.row2, move.col2, potentialBoard, "mind greg");
//                int potentialMin = minimaxDecision(otherMoves, potentialBoard, !completed, depth-1, name).getValue();
//                if (potentialMin < min){
//                    min = potentialMin;
//                    bestEdgeSoFar = move;
//                }
//            }
//            return new BestEdge(bestEdgeSoFar, min);
//        }
//    }
//
//
//



}
