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
        game.printBoard();
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain;
        AITurn steveTurn = new AITurn(gameBoard, boardSize, "steve");
        System.out.println("Do you want to play with AI vs. AI or Player vs. AI?");
        Scanner scan = new Scanner(System.in);
        String AIorPlayer = scan.nextLine();
        Turn gregTurn;

        if (AIorPlayer.equals("AI")){
            gregTurn = new AITurn(gameBoard, boardSize, "greg");
        }
        else {
            gregTurn = new PlayerTurn(gameBoard, boardSize, "greg");
        }

        int counter = 1;
        Path stevego = Paths.get(System.getProperty("user.dir") + "/src/steve.go");
        Path stevepass = Paths.get(System.getProperty("user.dir") +"/src/steve.pass");
        Path endgame = Paths.get(System.getProperty("user.dir") +"/src/end_game");
        Path move_file = Paths.get(System.getProperty("user.dir") +"/src/move_file");
        System.out.println(stevego);
        while (true) {
            // check if the stevego file is in the directory and the moves file is empty
            System.out.println(Files.exists(stevego));
            if(!Files.exists(move_file)) {
                System.out.println("Waiting for game to start");
                continue;
            } else if(Files.exists(stevego)) {
                System.out.println("Steve's turn to go");
            } else if (Files.exists(stevepass)) {
                System.out.println("Other player's turn to go. Steve passed");
            } else if (Files.exists(endgame)) {
                System.out.println("Game over");
                break;
            } else {
                System.out.println("Waiting for other player to go");
                Thread.sleep(1000);
                continue;
            }

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
            else{
                goAgain = steveTurn.takeTurn();
                playerLastWent = "steve";
            }
            counter++;
        }

    }

    public static boolean steveGoesFirst(){
        Random rand = new Random();
        int goFirst = rand.nextInt(100) % 2;
        return (goFirst == 0);
        //TODO integrate with referee later
    }
}