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
        takeTurn(whichPlayer);
    }

    private void takeTurn(Integer whichPlayer){
        Minimax minimax = new Minimax();
        double start = System.currentTimeMillis();
        String moveToMake = minimax.bestMove(gameBoard);
        double finish = System.currentTimeMillis();
        System.out.println(moveToMake);
        System.out.println("Time Taken: " + (finish-start)/1000);
        int[] numbers = getNums(moveToMake);
        int r1 = numbers[0];
        int c1 = numbers[1];
        int r2 = numbers[2];
        int c2 = numbers[3];

        //Update real board
        updateBoard(r1, c1, r2, c2, gameBoard);

    }

    /**
     * Important method where we update the board with given move that someone made
     * Move was either made by 'steve', 'greg' (player2), or minimax making fake moves
     */
    public static void updateBoard(int r1, int c1, int r2, int c2, Board aBoard) {
        int distanceBetweenRows = Math.abs(r1-r2);
        int distanceBetweenCols = Math.abs(c1-c2);
        if (((distanceBetweenRows == 1) && (distanceBetweenCols == 0))){
            //Vertical Line
            if (c1 == 0){
                Box rightBox = aBoard.getBox(Math.min(r1,r2),c1);
                rightBox.left = true;
            }
            else if (c1 == 9){
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                leftBox.right = true;
            }
            else {
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                Box rightBox = aBoard.getBox(Math.min(r1, r2), c1);

                leftBox.right = true;
                rightBox.left = true;
            }

        } //Checks to see if rows or columns but not both are next to each other
        if(((distanceBetweenCols == 1) && (distanceBetweenRows == 0))) {
            //Horizontal Line
            if (r1 == 0){
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));
                belowBox.top = true;
            }
            else if (r1 == 9){
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                aboveBox.bottom = true;
            }
            else {
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));

                aboveBox.bottom = true;
                belowBox.top = true;
            }
        }
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
