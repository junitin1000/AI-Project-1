import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AITurn extends Turn {
    int[] point1;
    int[] point2;

    // int[] numbers;
    // int r1;
    // int c1;
    // int r2;
    // int c2;

    int boardSize;
    // HashMap<int[][], Integer> gameBoard = new HashMap<>();

    Board gameBoard;
    String name;

    public AITurn(/* HashMap<int[][], Integer> currentBoard, */ Board theGameBoard, int sizeOfCurrentBoard,
            String name) {
        boardSize = sizeOfCurrentBoard;
        gameBoard = theGameBoard;
        this.name = name;
    }

    @Override
    public boolean takeTurn() {
        System.out.println("\n" + name + "'s Turn!");
        Minimax minimax = new Minimax();
        double start = System.currentTimeMillis();
        Edge decision = minimax.bestMove(gameBoard, name);
        double finish = System.currentTimeMillis();
        String moveToMake = "";
        boolean complete = false;
        // if (decision != null) {
        moveToMake = name + " " + decision.row1 + "," + decision.col1 + " " + decision.row2 + "," + decision.col2;

        System.out.println("\tTime Taken: " + (finish - start) / 1000);
        System.out.println(moveToMake.toUpperCase());
        int[] numbers = getNums(moveToMake);
        int r1 = numbers[0];
        int c1 = numbers[1];
        int r2 = numbers[2];
        int c2 = numbers[3];

        // Update real board
        complete = updateBoard(r1, c1, r2, c2, gameBoard, name, true);
        gameBoard.edges.remove(decision);
        // }
        // Write the move to the move file
        Path move_file = Paths.get(System.getProperty("user.dir") + "/move_file");
        try {
            // System.out.println("Writing to move file");
            Files.write(move_file, moveToMake.getBytes());
        } catch (Exception e) {
            System.out.println("Error writing to move file");
        }

        return complete;

    }

    private int[] getNums(String num) {
        Pattern numberPattern = Pattern.compile("-?\\d+");
        Matcher matcher = numberPattern.matcher(num);
        int[] numbers = new int[4];
        int index = 0;

        while (matcher.find() && index < 4) {
            numbers[index++] = Integer.parseInt(matcher.group());
        }

        return numbers;
    }

}
