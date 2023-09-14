public class Game {
    //Game Creates a board and prints it. Doesn't do much else rn

    //My thoughts: Store moves that have already happened in a hashmap<int[2],String> where the int[] is the line that was
    //drawn (ie "((0,0),(0,1))") and the String is either 'player' or 'ai', meaning it was drawn by the player or the ai

    private int rows;
    private int cols;
    private char[][] board;

    public Game(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows * 2 + 1][cols * 2 + 1];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (i % 2 == 0 && j % 2 == 0) {
                    board[i][j] = 'o'; // Represents dots
                } else {
                    board[i][j] = ' '; // Represents lines
                }
            }
        }
    }

    public void printBoard(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j]+"\t");
            }
            System.out.println();
        }
    }

}