import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static int boardSize = 2;
    public static void main(String[] args) {

        //Main basically just starts the game and makes it n x n, then passes it off to Game
        //TODO switch off between player and ai, also make ai lmao oops
        /*boolean startGame = false;
        System.out.println("Hello! Would you like to play a game of Dots and Boxes?!");
        Scanner scan = new Scanner(System.in);
        for(;;) {
            System.out.println("Enter 'Y' for yes and 'N' for no.");
            String response = scan.nextLine();
            if (response.equalsIgnoreCase("Y") || response.equalsIgnoreCase("yes")) {
                startGame = true;
                break;
            } else if (response.equalsIgnoreCase("N") || response.equalsIgnoreCase("no")) {
                System.out.println("Okay! Goodbye! Smoochies!!");
                break;
            } else {
                System.out.println("Sorry, I didn't get that.");
            }
        }

        if (startGame){
            System.out.println("Great! How large of a game would you like to play?");
            while (!scan.hasNextInt()){
                System.out.println("I'm sorry, please try again.");
                scan.next();
            }
            int n = scan.nextInt();
            System.out.println("Slay, let's start!");*/
        Game game = new Game(boardSize, boardSize);
        game.printBoard();
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain;
        AITurn steveTurn = new AITurn(gameBoard, boardSize, 1);
        PlayerTurn gregTurn = new PlayerTurn(gameBoard, boardSize, 2);
        if (steveGoesFirst()){
            System.out.println("steve's turn:");
            goAgain = steveTurn.takeTurn(1);
            playerLastWent = "steve";
        }
        else{
            System.out.println("greg's turn:");
            goAgain = gregTurn.takeTurn(2);
            playerLastWent = "greg";
        }
        int counter = 1;
        while (true) {

            if (counter == boardSize * (boardSize+1)*2) /*game is complete*/{
                //Initiate ENDGAME
                System.out.println("GAME OVER: " + playerLastWent + " WINS!");
                break;
            }
            else if (goAgain){
                if (playerLastWent.equals("steve")) {
                    System.out.println("steve's turn: ");
                    goAgain = steveTurn.takeTurn(1);
                    playerLastWent = "steve";
                }
                else{
                    System.out.println("greg's turn: ");
                    goAgain = gregTurn.takeTurn(2);
                    playerLastWent = "greg";
                }
            }
            else{
                if (playerLastWent.equals("steve")) {
                    System.out.println("greg's turn: ");
                    goAgain = gregTurn.takeTurn(2);
                    playerLastWent = "greg";
                }
                else{
                    System.out.println("steve's turn: ");
                    goAgain = steveTurn.takeTurn(1);
                    playerLastWent = "steve";
                }
            }
            counter++;
        }

        /*//HashMap<int[][], Integer> lineList = new HashMap<>();
        Board gameBoard = new Board();
        if (steveGoesFirst()){

        }

        while (true*//*win*//*) {
            int scoreBefore = gameBoard.score;
            if (*//*The total score is equal to the size of the board*//*){
                //END GAME
                break;
            }

            if (*//*score changed*//*){
                if (*//**//*)
                AITurn steveTurn = new AITurn(gameBoard, boardSize, 1);
            }
            else if (*//*score did not change*//*)
            AITurn steveTurn = new AITurn(gameBoard, boardSize, 1);
            //If win, end
            if (false*//*win*//*)
                break;

            PlayerTurn playerTurn = new PlayerTurn(gameBoard, boardSize, 2);
            //If win, end*/
        //}

        //}

    }

    public static boolean steveGoesFirst(){
        //TODO integrate with referee later
        return true;
    }
}