import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int boardSize = 2;
    public static void main(String[] args) throws InterruptedException {


        Game game = new Game(boardSize, boardSize);
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain = true;
        AITurn steveTurn = new AITurn(gameBoard, boardSize, "steve");
        //System.out.println("Do you want to play with AI vs. AI or Player vs. AI?");


        int counter = 1;
        Path stevego = Paths.get(System.getProperty("user.dir") + "/src/steve.go");
        Path stevepass = Paths.get(System.getProperty("user.dir") +"/src/steve.pass");
        Path endgame = Paths.get(System.getProperty("user.dir") +"/src/end_game");
        Path move_file = Paths.get(System.getProperty("user.dir") +"/src/move_file");

        // Gameplay loop
        while (true) {
            // if the move file is in the directory, the game has started
            if(!Files.exists(move_file)) {
                System.out.println("Waiting for game to start");
                Thread.sleep(1000);
                continue;
            } else if(!Files.exists(stevego)) { // If the steve.go file is not in the directory, its the other player's turn
                System.out.println("Waiting for other player to go");
                Thread.sleep(1000);
            } else if(Files.exists(stevego)) { // If the steve.go file is in the directory, it is steve's turn
                System.out.println("Steve's turn to go");

            // If the game is over announce the winner
            if (counter == boardSize * (boardSize+1)*2) /*game is complete*/{
                //Initiate ENDGAME
                String winningPlayer;
                if (gameBoard.getScore("steve") > 0){
                    winningPlayer = "steve";
                }
                else if (gameBoard.getScore("steve") < 0){
                    winningPlayer = "greg";
                }
                else{
                    System.out.println("GAME OVER: It's a tie!");
                    break;
                }
                System.out.println("GAME OVER: " + " WINS!");
                break;
            }

            // Otherwise take steve's turn
            else if (goAgain){
                goAgain = steveTurn.takeTurn();
                playerLastWent = "steve";
                Thread.sleep(3000);
            }
            counter++;
        }

    }
}}