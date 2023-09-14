import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTurn {

    //TODO NEED TO GO OVER HOW WE WANT TO HANDLE PARSING INFO GIVEN TO SELECT A LINE ON THE BOARD

    Scanner scan = new Scanner(System.in);
    int boardSize;
    HashMap<int[], Character> linesDrawn = new HashMap<>();

    public PlayerTurn(){
        System.out.println("Your Turn!");
        takeTurn();
    }

    private void takeTurn(){
        //Get response from Player ("row,col")
        if(isValidResponse(scan.nextLine())) {

        }
        else{
            //invalid, do it again
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
        String workingResp = response.replaceAll("\\s", ""); //A trimmed version of response getting rid of all white space
        String pattern = "\\(\\(-?\\d+,\\d+\\),\\(-?\\d+,\\d+\\)\\)"; //The pattern '((X1,Y1),(X2,Y2))'
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(workingResp);
        if (matcher.matches()){
            int[] numbers = getNums(workingResp);
            int r1 = numbers[0];
            int c1 = numbers[1];
            int r2 = numbers[2];
            int c2 = numbers[3];

            boolean inRange = false;

            for (int eachNumber : numbers){
                if (eachNumber > boardSize){
                    inRange = false;
                    break;
                }
                inRange = true;
            }

            if (inRange){
                int distanceBetweenRows = Math.abs(r1-r2);
                int distanceBetweenCols = Math.abs(c1-c2);
                if (((distanceBetweenRows == 1) && (distanceBetweenCols == 0)) || //Checks to see if rows or columns but not both are next to each other
                        ((distanceBetweenCols == 1) && (distanceBetweenRows == 0))){
                    //If the current line has not already been drawn
                    return true;
                }

            }
        }
        return false;
    }
}
