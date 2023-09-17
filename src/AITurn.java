import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AITurn extends Turn {
    int[] point1;
    int[] point2;

//    int[] numbers;
//    int r1;
//    int c1;
//    int r2;
//    int c2;

    int boardSize;
    //HashMap<int[][], Integer> gameBoard = new HashMap<>();

    Board gameBoard;

    public AITurn(/*HashMap<int[][], Integer> currentBoard,*/ Board theGameBoard, int sizeOfCurrentBoard, int whichPlayer){
        boardSize = sizeOfCurrentBoard;
        gameBoard = theGameBoard;
        System.out.println("steve's Turn!");
    }

    public boolean takeTurn(Integer whichPlayer){
        Minimax minimax = new Minimax();
        double start = System.currentTimeMillis();
        Edge decision = minimax.bestMove(gameBoard);
        double finish = System.currentTimeMillis();
        String moveToMake = "steve " + decision.row1 + "," + decision.col1 + " " + decision.row2 + "," + decision.col2;
        System.out.println(moveToMake);
        System.out.println("Time Taken: " + (finish-start)/1000);
        int[] numbers = getNums(moveToMake);
        int r1 = numbers[0];
        int c1 = numbers[1];
        int r2 = numbers[2];
        int c2 = numbers[3];

        //Update real board
        boolean complete = updateBoard(r1, c1, r2, c2, gameBoard, "steve");
        gameBoard.edges.remove(decision);
        return complete;

    }


    private int[] getNums(String num){
        Pattern numberPattern = Pattern.compile("-?\\d+");
        Matcher matcher = numberPattern.matcher(num);
        int[] numbers = new int[4];
        int index = 0;

        while (matcher.find() && index < 4) {
            numbers[index++] = Integer.parseInt(matcher.group());
        }

        return numbers;
    }

}
