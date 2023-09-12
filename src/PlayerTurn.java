import java.util.Scanner;

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

    private boolean isValidResponse(String response){
        return true;
    }
}
