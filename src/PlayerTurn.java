import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTurn extends Turn{

    //TODO NEED TO GO OVER HOW WE WANT TO HANDLE PARSING INFO GIVEN TO SELECT A LINE ON THE BOARD

    Scanner scan = new Scanner(System.in);
    int[] point1;
    int[] point2;

    int[] numbers;
    int r1;
    int c1;
    int r2;
    int c2;

    int boardSize;
    //HashMap<int[][], Integer> gameBoard = new HashMap<>();

    Board gameBoard;

    public PlayerTurn(/*HashMap<int[][], Integer> currentBoard,*/ Board theGameBoard, int sizeOfCurrentBoard, int whichPlayer){
        boardSize = sizeOfCurrentBoard;
        gameBoard = theGameBoard;
        System.out.println("Player " + whichPlayer + "'s Turn!");
    }

    public boolean takeTurn(Integer whichPlayer) {
        //Get response from Player "((r1,c1),(r2,c2))"
        String response = scan.nextLine();
        boolean complete = false;
        if (isValidResponse(response)) {
            complete = updateBoard(r1, c1, r2, c2, gameBoard, response.substring(0, response.indexOf(" ")));
            for (Edge edge : gameBoard.edges){
                if (edge.row1 == r1 && edge.col1 == c1 && edge.row2 == r2 && edge.col2 == c2){
                    gameBoard.edges.remove(edge);
                    System.out.println("Removed Edge");
                    break;
                }
            }

        }
        else {
                //invalid, do it again
                System.out.println("Fuck!");
            }
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


    /**
     * isValidResponse should return true if the string taken from the command line is a valid move and false otherwise.
     * A response is valid if the input is in this format: "((R1,C1),(R2,C2))" where R and C are positions on the board
     * All values must be less the size of the game (R1,R2,C1,C2 cannot be 3 in a 3x3 game)
     * The line must not have already been drawn in the game (ie ((R1,C1),(R2,C2)) must not have already been played)
     * and the move must create a straight line (ie |R1-R2| = 1 XOR |C1-C2| = 1)
     *
     * @param response - a user input from the command line
     * @return true if the above conditions are satisfied, false otherwise
     */
    private boolean isValidResponse(String response){
        //String pattern = "\\(\\(-?\\d+,\\d+\\),\\(-?\\d+,\\d+\\)\\)"; //The pattern '((X1,Y1),(X2,Y2))'
        //Pattern regex = Pattern.compile(pattern);
        //Matcher matcher = regex.matcher(workingResp);
        //if (matcher.matches()){
        numbers = getNums(response);
        r1 = numbers[0];
        c1 = numbers[1];
        r2 = numbers[2];
        c2 = numbers[3];

            boolean inRange = true;

            for (int eachNumber : numbers){
                if (eachNumber > boardSize || eachNumber < 0){
                    inRange = false;
                    break;
                }
            }

            if (inRange){
                    //If the current line has not already been drawn
                    //point1 = new int[]{r1, c1};
                    //point2 = new int[]{r2, c2};
                    //if (!gameBoard.containsKey(new int[][]{point1, point2})){
                        return true;
                    //
                }

        //}
        return false;
    }
}
