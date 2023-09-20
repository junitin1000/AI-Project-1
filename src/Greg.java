import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Greg {
    static int boardSize = 2;
    public static void main(String[] args) throws InterruptedException {


        Game game = new Game(boardSize, boardSize);
        game.printBoard();
        String playerLastWent;
        Board gameBoard = new Board();
        boolean goAgain = true;
        AITurn gregTurn = new AITurn(gameBoard, boardSize, "greg");
        //System.out.println("Do you want to play with AI vs. AI or Player vs. AI?");

        //Scanner scan = new Scanner(System.in);
        //String AIorPlayer = scan.nextLine();
        //Turn gregTurn;
        //gregTurn = new PlayerTurn(gameBoard, boardSize, "greg");

        int counter = 1;
        Path greggo = Paths.get(System.getProperty("user.dir") + "/src/greg.go");
        Path gregpass = Paths.get(System.getProperty("user.dir") +"/src/greg.pass");
        Path endgame = Paths.get(System.getProperty("user.dir") +"/src/end_game");
        Path move_file = Paths.get(System.getProperty("user.dir") +"/src/move_file");
        System.out.println(greggo);
        while (true) {
            // check if the greggo file is in the directory and the moves file is empty
            System.out.println(Files.exists(greggo));
            // if the move file is in the directory, the game has started
            if(!Files.exists(move_file)) {
                System.out.println("Waiting for game to start");
                Thread.sleep(3000);
                continue;
            } else if(Files.exists(greggo)) { // If the greg.go file is in the directory, it is greg's turn
                System.out.println("greg's turn to go");
            } else if (Files.exists(gregpass)) { // If the greg.pass file is in the directory, it is greg's turn again
                System.out.println("Other player's turn to go. greg passed");
                continue;
            } else if (Files.exists(endgame)) { // If the end_game file is in the directory, the game is over
                System.out.println("Game over");
                break;
            } else { // Otherwise, wait for the other player to go
                System.out.println("Waiting for other player to go");
                Thread.sleep(3000);
                continue;
            }

            // If the game is over announce the winner
            if (counter == boardSize * (boardSize+1)*2) /*game is complete*/{
                //Initiate ENDGAME
                String winningPlayer;
                if (gameBoard.getScore("greg") > 0){
                    winningPlayer = "greg";
                }
                else if (gameBoard.getScore("greg") < 0){
                    winningPlayer = "greg";
                }
                else{
                    System.out.println("GAME OVER: It's a tie!");
                    break;
                }
                System.out.println("GAME OVER: " + " WINS!");
                break;
            }
            // Otherwise take greg's turn
            else if (goAgain){
                goAgain = gregTurn.takeTurn();
                playerLastWent = "greg";
                Thread.sleep(3000);
            }
            counter++;
        }

    }

    public static boolean gregGoesFirst(){
        Random rand = new Random();
        int goFirst = rand.nextInt(100) % 2;
        return (goFirst == 0);
        //TODO integrate with referee later
    }
}