package model;

public class TTT {

    private int row, col;
    private char board[][] = new char[3][3];
    private char player = 'x';

    public TTT(){
        initializeBorad();
    }


    public void initializeBorad() {
        for (int i = 0; i<3; i++)
            for (int j =0; j<3 ; j++)
                board[i][j]= '_';
    }//end initilizeBoard()

    public void printBoard() {
        System.out.println("turn for player "+player);
        for (int i = 0; i<3; i++) {
            System.out.println();
            for (int j =0; j<3 ; j++) {
                if (j==0)
                    System.out.print(" | ");
                System.out.print(board[i][j] + " | ");
            }
        }
    }//end printBoard()

    public boolean play() {

        boolean playing = true;

            board[row][col] = player;

            if(gameOver(row,col)) {
                playing = false;
                System.out.println("Game over! player " + player + " Wins");
            }
            printBoard();
            if (player =='x')
                player = 'o';
            else
                player='x';

            return playing;

    }//end play()

    public boolean gameOver(int rMove, int cMove) {

        if (board[0][cMove] == board[1][cMove] && board[0][cMove] == board[2][cMove])
            return true;
        if (board[rMove][0] == board[rMove][1] && board[rMove][0] == board[rMove][2])
            return true;
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[1][1]!='_')
            return true;
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[1][1]!='_')
            return true;

        return false;
    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getPlayer() {
        return player;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }


}
