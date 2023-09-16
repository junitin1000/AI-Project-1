public class Turn {

    /**
     * Important method where we update the board with given move that someone made
     * Move was either made by 'steve', 'greg' (player2), or minimax making fake moves
     */
    public static void updateBoard(int r1, int c1, int r2, int c2, Board aBoard, String name) {
        int distanceBetweenRows = Math.abs(r1-r2);
        int distanceBetweenCols = Math.abs(c1-c2);
        if (((distanceBetweenRows == 1) && (distanceBetweenCols == 0))){
            //Vertical Line
            if (c1 == 0){
                Box rightBox = aBoard.getBox(Math.min(r1,r2),c1);
                rightBox.left = true;
                rightBox.checkComplete(name);
            }
            else if (c1 == 9){
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                leftBox.right = true;
                leftBox.checkComplete(name);
            }
            else {
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                Box rightBox = aBoard.getBox(Math.min(r1, r2), c1);

                leftBox.right = true;
                rightBox.left = true;
                leftBox.checkComplete(name);
                rightBox.checkComplete(name);
            }

        } //Checks to see if rows or columns but not both are next to each other
        if(((distanceBetweenCols == 1) && (distanceBetweenRows == 0))) {
            //Horizontal Line
            if (r1 == 0){
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));
                belowBox.top = true;
                belowBox.checkComplete(name);
            }
            else if (r1 == 9){
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                aboveBox.bottom = true;
                aboveBox.checkComplete(name);
            }
            else {
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));

                aboveBox.bottom = true;
                belowBox.top = true;
                aboveBox.checkComplete(name);
                belowBox.checkComplete(name);
            }
        }
    }



}
