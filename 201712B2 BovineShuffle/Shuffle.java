import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2017 December Contest, Bronze Problem 2. The Bovine
 * Shuffle http://www.usaco.org/index.php?page=viewproblem2&cpid=760
 */
public class Shuffle {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("shuffle.in"));
        int size = sc.nextInt();
        int[] rules = new int[size];
        String[] cows = new String[size];

        for (int i = 0; i < size; i++) {
            rules[i] = sc.nextInt();
        }

        for (int i = 0; i < size; i++) {
            cows[i] = sc.next();
        }

        sc.close();

        for (int i = 0; i < 3; i++) {
            String[] previous = new String[size];

            for (int j = 0; j < size; j++) {
                int k = rules[j] - 1;
                previous[j] = cows[k];
            }

            cows = previous;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("shuffle.out"));

        for (String s : cows) {
            writer.write(s + "\n");
        }

        writer.close();
    }
}