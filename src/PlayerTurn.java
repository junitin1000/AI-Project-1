import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTurn {

    //TODO NEED TO GO OVER HOW WE WANT TO HANDLE PARSING INFO GIVEN TO SELECT A LINE ON THE BOARD

    Scanner scan = new Scanner(System.in);
    int row;
    int col;
    public PlayerTurn(){
        System.out.println("Your Turn!");
        takeTurn();
    }

    private void takeTurn(){
        //Get response from Player ("row,col")
        if(isValidResponse(scan.nextLine())) {
            row = getRow(/*substring row*/"1");
            col = getCol(/*substring col*/"1");
        }
        else{
            //invalid, do it again
        }
    }

    private int getRow(String row){
        return Integer.parseInt(row);
    }

    private int getCol(String col){
        return Integer.parseInt(col);
    }

    /**
     * isValidResponse should return true if the string taken from the command line is a valid move and false otherwise.
     * A response is valid if the input is in this format: "((X1,Y1),(X2,Y2))" where X and Y are positions on the board
     * All values must be less the size of the game (X1,X2,Y1,Y2 cannot be 3 in a 3x3 game)
     * The line must not have already been drawn in the game (ie ((X1,Y1),(X2,Y2)) must not have already been played)
     * and the move must create a straight line (ie |X1-X2| = 1 XOR |Y1-Y2| = 1)
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
            //We're in boys
            return true;
        }
        return false;
    }
}
