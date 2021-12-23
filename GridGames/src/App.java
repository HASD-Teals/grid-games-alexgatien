import java.util.Scanner;

public class gridGames {
    public static Scanner input = new Scanner(System.in);
    static int score1;
    static int score2;
    static int scoreGoal;

    public static void main(String[] args) {
        System.out.print("Enter Desired Score Goal: ");
        scoreGoal = input.nextInt();
        System.out.println();
        startNewRound();

    }

    public static void reverseRow(char grid[][], int selectedRow, int row) {
selectedRow = selectedRow-1;
        char temp[] = new char[row];
        int b = 0;

        for (int a = grid[selectedRow].length - 1; a >= 0; a = a - 1) {
            temp[b] = grid[selectedRow][a];
            b++;
        }
        for (int c = 0; c < temp.length; c++) {
            grid[selectedRow][c] = temp[c];
        }

    }
    public static void reverseCol(char grid[][], int selectedCol, int col) {
        selectedCol = selectedCol-1;
                char temp[] = new char[col];
                int b = 0;
        
                for (int a = grid[selectedCol].length - 1; a >= 0; a = a - 1) {
                    temp[b] = grid[a][selectedCol];
                    b++;
                }
                for (int c = 0; c < temp.length; c++) {
                    grid[c][selectedCol] = temp[c];
                }
        
            }

    public static void checkForNewRound(int whoWon) {
        if (whoWon == 1) {
            score1++;
        }
        if (whoWon == 2) {
            score2++;
        }
        if (score1 >= scoreGoal) {
            System.out.println("Player One Wins the Game!");
            System.exit(0);
        }
        if (score2 >= scoreGoal) {
            System.out.println("Player Two Wins the Game!");
            System.exit(0);
        }
        System.out.println();
        startNewRound();
    }

    public static void startNewRound() {
        System.out.println("Round " + (score1 + score2 + 1));
        System.out.println("Player One Score: " + score1);
        System.out.println("Player Two Score: " + score2);
        System.out.print("Input Background Symbol: ");
        String backgroundIn = input.next();
        char background = backgroundIn.charAt(0);
        System.out.println();
        System.out.print("Input Player One Piece: ");
        String playerOnePieceIn = input.next();
        char playerOnePiece = playerOnePieceIn.charAt(0);
        System.out.println();
        System.out.print("Input Player Two Piece: ");
        String playerTwoPieceIn = input.next();
        char playerTwoPiece = playerTwoPieceIn.charAt(0);
        System.out.println();
        int row = 3;
        int col = 3;
        char[][] gameboard = new char[row][col];
        createGrid(gameboard, row, col, background);
        showBoard(gameboard, row, col);
        if ((score1 + score2 + 1) % 2 == 1) {
            takeTurnOne(gameboard, row, col, playerOnePiece, playerTwoPiece, background);
        }
        if ((score1 + score2 + 1) % 2 == 0) {
            takeTurnTwo(gameboard, row, col, playerOnePiece, playerTwoPiece, background);
        }
    }

    public static void checkTie(char grid[][], int r, int c, char symbol) {
        int l = 0;
        for (int a = 0; a < r; a++) {

            for (int b = 0; b < c; b++) {
                if (grid[a][b] == (symbol)) {
                    l++;
                }
            }
        }
        if (l == 0) {
            System.out.println("The Game Has Tied");
            checkForNewRound(3);
        }
    }

    public static void takeTurnOne(char[][] grid, int r, int c, char playerOnePiece, char playerTwoPiece,
            char background) {
        System.out.print("Player One Input Row: ");
        int selectedRow = input.nextInt();
        if (selectedRow > 3) {
            System.out.println("You must pick 1,2 or Three");
            takeTurnOne(grid, r, c, playerOnePiece, playerTwoPiece, background);
        }
        System.out.println();
        System.out.print("Player One Input Column: ");
        int selectedCol = input.nextInt();
        if (selectedCol > 3) {
            System.out.println("You must pick 1,2 or Three");
            takeTurnOne(grid, r, c, playerOnePiece, playerTwoPiece, background);
        }

        writeBlock(grid, selectedRow - 1, selectedCol - 1, playerOnePiece, background);
        //reverseRow(grid, selectedRow, r);
        //reverseCol(grid, selectedCol, c);
        showBoard(grid, r, c);
        checkWin(grid, r, c, playerOnePiece, playerTwoPiece, background);
        takeTurnTwo(grid, r, c, playerOnePiece, playerTwoPiece, background);

    }

    public static void takeTurnTwo(char[][] grid, int r, int c, char playerOnePiece, char playerTwoPiece,
            char background) {
        System.out.print("Player Two Input Row: ");
        int selectedRow = input.nextInt();
        if (selectedRow > 3) {
            System.out.println("You must pick 1,2 or Three");
            takeTurnTwo(grid, r, c, playerOnePiece, playerTwoPiece, background);
        }
        System.out.println();
        System.out.print("Player Two Input Column: ");
        int selectedCol = input.nextInt();
        if (selectedCol > 3) {
            System.out.println("You must pick 1,2 or Three");
            takeTurnTwo(grid, r, c, playerOnePiece, playerTwoPiece, background);
        }
        writeBlock(grid, selectedRow - 1, selectedCol - 1, playerTwoPiece, background);
        showBoard(grid, r, c);
        checkWin(grid, r, c, playerOnePiece, playerTwoPiece, background);
        takeTurnOne(grid, r, c, playerOnePiece, playerTwoPiece, background);
    }

    public static void checkWin(char[][] grid, int r, int c, char one, char two, char symbol) {
        hWin(grid, r, c, one, two);
        vWin(grid, r, c, one, two);
        dWin(grid, r, c, one, two);
        checkTie(grid, r, c, symbol);
    }

    public static void createGrid(char grid[][], int r, int c, char symbol) {

        for (int a = 0; a < r; a++) {

            for (int b = 0; b < c; b++) {
                grid[a][b] = (symbol);
            }
        }
    }

    public static void writeBlock(char[][] grid, int r, int c, char piece, char back) {
        char potential = grid[r][c];
        if (potential == back) {
            grid[r][c] = piece;
        } else {
            System.out.println("That space is taken");

        }
    }

    public static void hWin(char[][] grid, int r, int c, char one, char two) {
        for (int a = 0; a < r; a++) {
            int stay = 0;
            for (int b = 0; b < c; b++) {
                if (grid[a][b] == one) {
                    stay++;
                }
                if (grid[a][b] == two) {
                    stay = stay - 1;
                }
            }
            if (stay == c) {
                System.out.println("Player One Wins");
                checkForNewRound(1);
            }
            if (stay == 0 - c) {
                System.out.println("Player Two Wins");
                checkForNewRound(2);
            }
        }
    }

    public static void vWin(char[][] grid, int r, int c, char one, char two) {
        for (int a = 0; a < c; a++) {
            int stay = 0;
            for (int b = 0; b < r; b++) {
                if (grid[b][a] == one) {
                    stay++;
                }
                if (grid[b][a] == two) {
                    stay = stay - 1;
                }
            }
            if (stay == c) {
                System.out.println("Player One Wins");
                checkForNewRound(1);
            }
            if (stay == 0 - c) {
                System.out.println("Player Two Wins");
                checkForNewRound(2);
            }
        }
    }

    public static void showBoard(char[][] grid, int r, int c) {
        for (int a = 0; a < r; a++) {
            System.out.println();
            for (int b = 0; b < c; b++) {

                System.out.print(grid[a][b] + " ");
            }
        }
        System.out.println();
    }

    public static void dWin(char[][] grid, int r, int c, char one, char two) {
        int a = 0;
        for (int i = 0; i < r; i++) {
            if (grid[i][i] == one) {
                a++;
            }
            if (grid[i][i] == two) {
                a = a - 1;
            }
        }
        if (a == r) {
            System.out.println("Player One Wins");
            checkForNewRound(1);
        }
        if (a == 0 - r) {
            System.out.println("Player Two Wins");
            checkForNewRound(2);
        }
        int b = 0;
        int x = r - 1;
        for (int i = 0; i < r; i++) {
            if (grid[x][i] == one) {
                b++;
            }
            if (grid[x][i] == two) {
                b = b - 1;
            }
            x = x - 1;
        }
        if (b == r) {
            System.out.println("Player One Wins");
            checkForNewRound(1);
        }
        if (b == 0 - r) {
            System.out.println("Player Two Wins");
            checkForNewRound(2);
        }

    }
}
