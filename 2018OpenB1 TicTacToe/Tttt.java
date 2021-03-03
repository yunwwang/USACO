import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for USACO 2018 US Open Contest, Bronze Problem 1. Team Tic Tac Toe
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=831
 */
public class Tttt {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("tttt.in"));
        char[][] board = new char[3][3];
        HashSet<Character> singleWinner = new HashSet<Character>();
        HashSet<String> teamWinner = new HashSet<String>();

        for (int i = 0; i < 3; i++) {
            String line = sc.next();

            for (int j = 0; j < 3; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        sc.close();

        int i = 0;
        int j = 0;

        for (i = 0; i < 3; i++) {
            HashSet<Character> row = new HashSet<Character>();
            HashSet<Character> column = new HashSet<Character>();

            for (j = 0; j < 3; j++) {                
                if (!row.contains(board[i][j])) {
                    row.add(board[i][j]);
                }
                if (!column.contains(board[j][i])) {
                    column.add(board[j][i]);
                }
            }

            countWinner(row, singleWinner, teamWinner);
            countWinner(column, singleWinner, teamWinner);
        }

        i = 0;

        HashSet<Character> diagA = new HashSet<Character>();
        HashSet<Character> diagB = new HashSet<Character>();

        for(i=0; i< 3; i++) {
            if (!diagA.contains(board[i][i])) {
                diagA.add(board[i][i]);
            }
            if (!diagB.contains(board[i][2-i])) {
                diagB.add(board[i][2-i]);
            }
        }

        countWinner(diagA, singleWinner, teamWinner);
        countWinner(diagB, singleWinner, teamWinner);


        BufferedWriter writer = new BufferedWriter(new FileWriter("tttt.out"));
        writer.write(String.format("%d\n%d", singleWinner.size(), teamWinner.size()));
        writer.close();
    }

    private static void countWinner(HashSet<Character> set, HashSet<Character> singleWinner, HashSet<String> teamWinner) {
        if (set.size() == 1){
            char winner = set.stream().findFirst().get();
            if (!singleWinner.contains(winner)) {
                singleWinner.add(winner);
            }
        } else if (set.size() == 2) {
            StringBuilder sb = new StringBuilder();
            for(Object c: set.stream().sorted().toArray()){
                sb.append(c);
            }

            String winner = sb.toString();
            if (!teamWinner.contains(winner)) {
                teamWinner.add(winner);
            }
        }
    }
}