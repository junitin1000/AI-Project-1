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
    static Path stevepass = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/steve.pass");
    static Path endgame = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/end_game");
    static Path greggo = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/greg.go");
    static Path gregpass = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/greg.pass");
    static Path move_file = Paths.get(System.getProperty("user.dir") + "/src/dots_boxes_referee/move_file");

    public static void main(String[] args) throws InterruptedException, IOException {
        refMain();
        //nonRefMain();

    }

    public static void refMain() throws InterruptedException, IOException {
        Board gameBoardSteve = new Board();
        Board gameBoardGreg = new Board();
        AITurn steveTurn = new AITurn(gameBoardSteve, boardSize, "steve");
        AITurn gregTurn = new AITurn(gameBoardGreg, boardSize, "greg");
        RefTurn stevesOpponentTurn = new RefTurn(gameBoardSteve, boardSize, "steveOppDude");
        RefTurn gregsOpponentTurn = new RefTurn(gameBoardGreg, boardSize, "gregOppDude");
        /* in while loop */
        while (!Files.exists(endgame)) {
            //sense steve.go
            if (Files.exists(stevego)) {
                ourTurn(steveTurn, stevesOpponentTurn);
            } else if (Files.exists(stevepass)) {
                ourPass(stevesOpponentTurn, "steve");
            }

            //sense greg.go
            if (Files.exists(greggo)) {
                ourTurn(gregTurn, gregsOpponentTurn);
            } else if (Files.exists(gregpass)) {
                ourPass(gregsOpponentTurn, "greg");
            }

            Thread.sleep(100);
        }

    }

    public static boolean ourTurn(AITurn myTurn, RefTurn opponentTurn) throws IOException {

        List<String> moveList = Files.readAllLines(move_file);

        if(!moveList.isEmpty()) {
            String move = moveList.get(0);

            if(move != null){  // if move is empty and not passing
                if(!move.contains("0,0 0,0")){
                    // update edges and Board
                    opponentTurn.takeTurn(move);
                    System.out.println(move);
                }
            }
        }

        // steve goes now and writes his turn to move file
        myTurn.takeTurn();

        return true;
    }

    public static void ourPass(RefTurn opponentTurn, String name) throws IOException {
        List<String> moveList = Files.readAllLines(move_file);

        if(!moveList.isEmpty()){
            String move = moveList.get(0);
            // read their move and add to our board
            if (!move.equals("") && !move.contains("0,0 0,0")) {  // if move is empty and not passing
                // update edges and Board
                opponentTurn.takeTurn(move);
            }
        }

        // acknowledge move by writing 0,0 0,0
        try {
            System.out.println("Writing to move file");
            String passMove = name + " 0,0 0,0";
            Files.write(move_file, passMove.getBytes());
        } catch (Exception e) {
            System.out.println("Error writing to move file");
        }

    }


    public static boolean steveGoesFirst() {
        Random rand = new Random();
        int goFirst = rand.nextInt(100) % 2;
        return (goFirst == 0);
        //TODO integrate with referee later
    }

    public static void nonRefMain() {
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