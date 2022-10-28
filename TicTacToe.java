import java.util.Scanner;

public class TicTacToe {
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

    public static void main(String[] args) {
        // Get game input
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        int gridSpaces = 9;

        // print game table
        for (int i = 0; i <= gridSpaces; i++) {
            if (i == gridSpaces) {
                System.out.println("---------");
            } else if (i == 0) {
                System.out.print("---------\n| " + userInput.charAt(i) + " ");
            } else if (i % 3 == 0) {
                System.out.print("| " + userInput.charAt(i) + " ");
            } else if (i % 3 == 1) {
                System.out.print(userInput.charAt(i) + " ");
            } else if (i % 3 == 2) {
                System.out.print(userInput.charAt(i) + " |\n");
            }
        }

        // Check game result
        if (checkWinner("X", userInput) && checkWinner("O", userInput) || checkCheating(userInput)) {
            System.out.println("Impossible");
        } else if (checkWinner("X", userInput)) {
            System.out.println("X wins");
        } else if (checkWinner("O", userInput)) {
            System.out.println("O wins");
        } else if (gameNotFinished(userInput)) {
            System.out.println("Game not finished");
        } else {
            System.out.println("Draw");
        }
    }
}