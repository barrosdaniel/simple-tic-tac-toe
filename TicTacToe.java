import java.util.Scanner;

public class TicTacToe {
    private static final int gridSpaces = 9;
    private static final Scanner scanner = new Scanner(System.in);
    private static String tableString = "";
    private static String playerTurn = "X";

    public static void main(String[] args) {
        updateTableString(getInitialTableString());
        printGameTable();
        getPlayerMove();
        printGameTable();
        checkGameResult();
    }

    private static String getInitialTableString() {
        return scanner.nextLine();
    }

    private static void updateTableString(String newTableString) {
        tableString = newTableString;
    }

    private static void printGameTable() {
        for (int i = 0; i <= gridSpaces; i++) {
            if (i == gridSpaces) {
                System.out.println("---------");
            } else if (i == 0) {
                System.out.print("---------\n| " + tableString.charAt(i) + " ");
            } else if (i % 3 == 0) {
                System.out.print("| " + tableString.charAt(i) + " ");
            } else if (i % 3 == 1) {
                System.out.print(tableString.charAt(i) + " ");
            } else if (i % 3 == 2) {
                System.out.print(tableString.charAt(i) + " |\n");
            }
        }
    }

    private static void getPlayerMove() {
        int row = scanner.nextInt();
        int column = scanner.nextInt();
        int playerMoveIndex = getPlayerMoveIndex(column, row);
        tableString = tableString.substring(0, playerMoveIndex) + playerTurn +
                tableString.substring(playerMoveIndex + 1);
    }

    private static int getPlayerMoveIndex(int column, int row) {
        int playerMoveIndex = -1;
        if (column == 1 && row == 1) {
            playerMoveIndex = 0;
        } else if (column == 2 && row == 1) {
            playerMoveIndex = 1;
        } else if (column == 3 && row == 1) {
            playerMoveIndex = 2;
        } else if (column == 1 && row == 2) {
            playerMoveIndex = 3;
        } else if (column == 2 && row == 2) {
            playerMoveIndex = 4;
        } else if (column == 3 && row == 2) {
            playerMoveIndex = 5;
        } else if (column == 1 && row == 3) {
            playerMoveIndex = 6;
        } else if (column == 2 && row == 3) {
            playerMoveIndex = 7;
        } else if (column == 3 && row == 3) {
            playerMoveIndex = 8;
        }
        return playerMoveIndex;
    }

    private static void checkGameResult() {
        if (checkWinner("X", tableString) && checkWinner("O", tableString) || checkCheating(tableString)) {
            System.out.println("Impossible");
        } else if (checkWinner("X", tableString)) {
            System.out.println("X wins");
        } else if (checkWinner("O", tableString)) {
            System.out.println("O wins");
        } else if (gameNotFinished(tableString)) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }

    private static boolean gameNotFinished(String userInput) {
        return userInput.contains("_") || userInput.contains(" ");
    }

    private static boolean checkWinner(String player, String game) {
        boolean winner = false;
        String[] gameArray = game.split("");
        String playerPlays = "";
        for (int i = 0; i < gameArray.length; i++) {
            if (gameArray[i].equals(player)) {
                playerPlays += i;
            }
        }
        if (
            // Horizontal lines
                playerPlays.contains("012") ||
                playerPlays.contains("345") ||
                playerPlays.contains("678") ||
                // Vertical lines
                playerPlays.contains("0") && playerPlays.contains("3") && playerPlays.contains("6") ||
                playerPlays.contains("1") && playerPlays.contains("4") && playerPlays.contains("7") ||
                playerPlays.contains("2") && playerPlays.contains("5") && playerPlays.contains("8") ||
                // Diagonals
                playerPlays.contains("0") && playerPlays.contains("4") && playerPlays.contains("8") ||
                playerPlays.contains("2") && playerPlays.contains("4") && playerPlays.contains("6")
        ) {
            winner = true;
        }
        return winner;
    }

    private static boolean checkCheating(String game) {
        boolean cheating = false;
        int numberOfXPlays = 0;
        int numberOfOPlays = 0;
        String[] plays = game.split("");
        for (String play : plays) {
            if (play.equals("X")) {
                numberOfXPlays++;
            } else if (play.equals("O")) {
                numberOfOPlays++;
            }
        }
        if (numberOfXPlays - numberOfOPlays > 1 || numberOfOPlays - numberOfXPlays > 1) {
            cheating = true;
        }
        return cheating;
    }
}