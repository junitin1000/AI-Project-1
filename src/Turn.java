public class Turn {

    /**
     * Important method where we update the board with given move that someone made
     * Move was either made by 'steve', 'greg' (player2), or minimax making fake moves
     * @return false if the move doesn't completes a box, true if it completes 1 or if it completes 2
     */
    public static boolean updateBoard(int r1, int c1, int r2, int c2, Board aBoard, String name, boolean realTurn) {
        int distanceBetweenRows = Math.abs(r1-r2);
        int distanceBetweenCols = Math.abs(c1-c2);
        int completedBox = 0;
        if (((distanceBetweenRows == 1) && (distanceBetweenCols == 0))){
            //Vertical Line
            if (c1 == 0){
                Box rightBox = aBoard.getBox(Math.min(r1,r2),c1);
                rightBox.left = true;
                completedBox = rightBox.checkComplete(name, realTurn) ? 1 : 0;
            }
            else if (c1 == Main.boardSize){
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                leftBox.right = true;
                completedBox = leftBox.checkComplete(name, realTurn) ? 1 : 0;
            }
            else {
                Box leftBox = aBoard.getBox(Math.min(r1, r2), c1 - 1);
                Box rightBox = aBoard.getBox(Math.min(r1, r2), c1);

                leftBox.right = true;
                rightBox.left = true;
//                boolean leftCheck = leftBox.checkComplete(name, realTurn);
//                boolean rightCheck = rightBox.checkComplete(name, realTurn);
                completedBox = ((leftBox.checkComplete(name, realTurn)) ? 1 : 0) + ((rightBox.checkComplete(name, realTurn)) ? 1 : 0);
            }

        } //Checks to see if rows or columns but not both are next to each other
        if(((distanceBetweenCols == 1) && (distanceBetweenRows == 0))) {
            //Horizontal Line
            if (r1 == 0){
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));
                belowBox.top = true;
                completedBox = belowBox.checkComplete(name, realTurn) ? 1 : 0;
            }
            else if (r1 == Main.boardSize){
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                aboveBox.bottom = true;
                completedBox = aboveBox.checkComplete(name, realTurn) ? 1 : 0;
            }
            else {
                Box aboveBox = aBoard.getBox(r1 - 1, Math.min(c1, c2));
                Box belowBox = aBoard.getBox(r1, Math.min(c1, c2));

                aboveBox.bottom = true;
                belowBox.top = true;
                completedBox = (aboveBox.checkComplete(name, realTurn) ? 1 : 0) + (belowBox.checkComplete(name, realTurn) ? 1 : 0);
            }
        }

        // If box is completed update the score
        if(completedBox > 0) {
            if(name.equals("steve")) {
                aBoard.score++;
            }else {
                aBoard.score--;
            }
            if(completedBox > 1){
                if(name.equals("steve")) {
                    aBoard.score++;
                }else {
                    aBoard.score--;
                }
            }
            if(realTurn) {
                System.out.println("Score: " + aBoard.score);
            }
        }

        return completedBox > 0;
    }


    public boolean takeTurn(){
        return false;
    }


}
