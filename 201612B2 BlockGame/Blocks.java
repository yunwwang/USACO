import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2016 December Contest, Bronze Problem 2. Block Game
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=664
 */
public class Blocks {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("blocks.in"));
        int size = sc.nextInt();
        int[] count = new int[26];

        for(int i = 0; i < size; i++) {
            String wordA = sc.next();
            String wordB = sc.next();
            int[] countA = new int[26];
            int[] countB = new int[26];

            for(char c : wordA.toCharArray()){
                int index = c - 'a';
                countA[index] ++;
            }

            for(char c : wordB.toCharArray()) {
                int index = c-'a';
                countB[index] ++;
            }

            for(int j = 0; j < 26; j++) {
                count[j] += Math.max(countA[j], countB[j]);
            }
        }

        sc.close();

        BufferedWriter writer = new BufferedWriter(new FileWriter("blocks.out"));

        for(int i : count) {
            writer.write(String.format("%d%n", i));
        }

        writer.close();
    }
}
