import java.util.Scanner;

/**
 * Created by patil215 on 9/23/16.
 */
public class Tennis {

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numCases = scanner.nextInt();

        for(int i = 0; i < numCases; i++) {
            String[] playerNames = new String[2];
            int[] playerScores = new int[2];

            int rounds = scanner.nextInt();
            playerNames[0] = scanner.next();
            playerNames[1] = scanner.next();

            for(int d = 0; d < rounds; d++) {
                handleScore(playerScores, scanner.next(), playerNames);
            }

        }
    }

    public static void handleScore(int[] playerScores, String playerName, String[] players) {
        int playerIndex = -1;
        for(int i = 0; i < players.length; i++) {
            if(players[i].equals(playerName)) {
                playerIndex = i;
            }
        }

        int curScore = playerScores[playerIndex];
        int otherScore = playerScores[(playerIndex + 1) % 2];
        if(curScore == 0) {
            curScore = 15;
        } else if(curScore == 15) {
            curScore = 30;
        } else if(curScore == 30) {
            curScore = 40;
        } else if(curScore == 40 && otherScore == 50) {
            otherScore = 40;
        } else if(curScore == 40) {
            curScore = 50;
        } else if(curScore == 50) {
            curScore = 60;
        }
        playerScores[playerIndex] = curScore;
        playerScores[(playerIndex + 1) % 2] = otherScore;

        printScores(playerScores, players);
    }

    public static void printScores(int[] playerScores, String[] players) {
        if(playerScores[0] == 40 && playerScores[1] == 40) {
            System.out.println("deuce");
        } else if(playerScores[1] > 40 && playerScores[1] - playerScores[0] > 10) {
            System.out.println(players[1] + " won!");
        } else if(playerScores[0] > 40 && playerScores[0] - playerScores[1] > 10) {
            System.out.println(players[0] + " won!");
        } else if(playerScores[0] == 40 && playerScores[1] > 40) {
            System.out.println("advantage " + players[1]);
        } else if(playerScores[0] > 40 && playerScores[1] == 40) {
            System.out.println("advantage " + players[0]);
        } else {
            if(playerScores[0] == playerScores[1]) {
                System.out.println(playerScores[0] + " all");
            } else {
                if(playerScores[0] == 0) {
                    System.out.print("love ");
                } else {
                    System.out.print(playerScores[0] + " ");
                }
                if(playerScores[1] == 0) {
                    System.out.print("love");
                } else {
                    System.out.print(playerScores[1] + " ");
                }
                System.out.println();
            }
        }
    }

}
