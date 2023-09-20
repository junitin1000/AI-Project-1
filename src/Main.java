import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int boardSize = 9;
    static Path stevego = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/steve.go");
    static Path stevepass = Paths.get(System.getProperty("user.dir") +"/src/dots_boxes_referee/steve.pass");
    static Path endgame = Paths.get(System.getProperty("user.dir") +"/src/dots_boxes_referee/end_game");
    static Path move_file = Paths.get(System.getProperty("user.dir") +"/src/dots_boxes_referee/move_file");

    public static void main(String[] args) {
        refMain();
        //nonRefMain();

    }

    public static void refMain(){
        Game game = new Game(boardSize, boardSize);
        game.printBoard();
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain;
        AITurn steveTurn = new AITurn(gameBoard, boardSize, "steve");
        RefTurn opponentTurn = new RefTurn(gameBoard, boardSize, "greg");
        /* in while loop */
        // look for steve.go file


        // condition: steve.pass file

    }

    public static boolean ourTurn(AITurn steveTurn, RefTurn opponentTurn, boolean goAgain, String playerLastWent) throws IOException {

        if (Files.exists(endgame)) {
            return false;  // game ended
        } else {

            List<String> moveList = Files.readAllLines(move_file);
            String move = moveList.get(0);

            if (!move.equals("") && !move.contains("0,0 0,0")) {  // if move is empty and not passing
                // update edges and Board
                opponentTurn.takeTurn(move);
            }

            // steve goes now
            goAgain = steveTurn.takeTurn();
            // add to movefile


            if (goAgain && Files.exists(stevego)) {
                if (playerLastWent.equals("steve")) {
                    goAgain = steveTurn.takeTurn();
                    playerLastWent = "steve";
                }


            }
            return true;
        }
    }

    public static boolean steveGoesFirst() {
        Random rand = new Random();
        int goFirst = rand.nextInt(100) % 2;
        return (goFirst == 0);
        //TODO integrate with referee later
    }

    public static void nonRefMain(){
        Scanner scan = new Scanner(System.in);

        Game game = new Game(boardSize, boardSize);
        game.printBoard();
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain;
        AITurn steveTurn = new AITurn(gameBoard, boardSize, "steve");
        System.out.println("Do you want to play with AI vs. AI or Player vs. AI? (AI = 1, Player = 2)");
        Turn gregTurn;
        while (true) {

            String AIorPlayer = scan.nextLine();

            if (AIorPlayer.equals("1")) {
                gregTurn = new AITurn(gameBoard, boardSize, "greg");
                break;
            } else if (AIorPlayer.equals("2")) {
                gregTurn = new PlayerTurn(gameBoard, boardSize, "greg");
                break;
            } else {
                System.out.println("Sorry, I didn't get that.");
            }
        }


        if (steveGoesFirst()) {
            goAgain = steveTurn.takeTurn();
            playerLastWent = "steve";
        } else {
            goAgain = gregTurn.takeTurn();
            playerLastWent = "greg";
        }
        int counter = 1;

        while (true) {

            if (counter == boardSize * (boardSize + 1) * 2) /*game is complete*/ {
                //Initiate ENDGAME
                String winningPlayer;
                if (gameBoard.getScore("steve") > 0) {
                    winningPlayer = "steve";
                } else if (gameBoard.getScore("steve") < 0) {
                    winningPlayer = "greg";
                } else {
                    System.out.println("GAME OVER: It's a tie!");
                    break;
                }
                System.out.println("GAME OVER: " + winningPlayer + " WINS!");
                break;
            } else if (goAgain) {
                if (playerLastWent.equals("steve")) {
                    goAgain = steveTurn.takeTurn();
                    playerLastWent = "steve";
                } else {
                    goAgain = gregTurn.takeTurn();
                    playerLastWent = "greg";
                }
            } else {
                if (playerLastWent.equals("steve")) {
                    goAgain = gregTurn.takeTurn();
                    playerLastWent = "greg";
                } else {
                    goAgain = steveTurn.takeTurn();
                    playerLastWent = "steve";
                }
            }
            counter++;
        }
    }
}