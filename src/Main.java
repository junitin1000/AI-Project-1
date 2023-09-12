import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Main basically just starts the game and makes it n x n, then passes it off to Game
        //TODO switch off between player and ai, also make ai lmao oops
        boolean startGame = false;
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
            System.out.println("Slay, let's start!");
            Game game = new Game(n,n);
            game.printBoard();

        }

    }
}