import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Files;

public class AITurn extends Turn {
    int[] point1;
    int[] point2;

//    int[] numbers;
//    int r1;
//    int c1;
//    int r2;
//    int c2;

    int boardSize;
    //HashMap<int[][], Integer> gameBoard = new HashMap<>();

    Board gameBoard;
    String name;

    public AITurn(/*HashMap<int[][], Integer> currentBoard,*/ Board theGameBoard, int sizeOfCurrentBoard, String name){
        boardSize = sizeOfCurrentBoard;
        gameBoard = theGameBoard;
        this.name = name;
    }

    @Override
    public boolean takeTurn(){
        System.out.println(name + "'s Turn!");
        System.getProperty("user.dir");
        Minimax minimax = new Minimax();
        double start = System.currentTimeMillis();
        Edge decision = minimax.bestMove(gameBoard, name);
        double finish = System.currentTimeMillis();
        String moveToMake = name+ " " + decision.row1 + "," + decision.col1 + " " + decision.row2 + "," + decision.col2;
        System.out.println(moveToMake);
        System.out.println("Time Taken: " + (finish-start)/1000);
        int[] numbers = getNums(moveToMake);
        int r1 = numbers[0];
        int c1 = numbers[1];
        int r2 = numbers[2];
        int c2 = numbers[3];

        // Write the move to the move file
        Path move_file = Paths.get(System.getProperty("user.dir") + "/src/move_file");
        try {
            System.out.println("Writing to move file");
            Files.write(move_file, moveToMake.getBytes());
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println("Error writing to move file");
        }

        //Update real board
        boolean complete = updateBoard(r1, c1, r2, c2, gameBoard, name, true);

        gameBoard.edges.remove(decision);


        return complete;

    }


    private int[] getNums(String num){
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
