import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2017 US Open Contest, Bronze Problem 2. Bovine Genomics
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=736
 */
public class Cownomics {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("cownomics.in"));
        int n = sc.nextInt();
        int m = sc.nextInt();
        String[] spots = new String[n];
        String[] plains = new String[n];

        for (int i = 0; i < n; i++) {
            spots[i] = sc.next();
        }

        for (int i = 0; i < n; i++) {
            plains[i] = sc.next();
        }

        sc.close();

        int answer = 0;

        for (int i = 0; i < m; i++) {
            boolean[][] flags = new boolean[2][4];

            for (int j = 0; j < n; j++) {
                flagGene(spots[j].charAt(i), flags[0]);
            }

            for (int j = 0; j < n; j++) {
                flagGene(plains[j].charAt(i), flags[1]);
            }

            boolean checker = true;
            for (int k = 0; k < 4; k++) {
                if (flags[0][k] && flags[1][k]) {
                    checker = false;
                    break;
                }
            }

            if (checker) {
                answer++;
            }
        }

        Files.writeString(Path.of("cownomics.out"), String.format("%d", answer));
    }

    private static void flagGene(char gene, boolean[] flags) {
        if (gene == 'A') {
            flags[0] = true;
        } else if (gene == 'C') {
            flags[1] = true;
        } else if (gene == 'G') {
            flags[2] = true;
        } else if (gene == 'T') {
            flags[3] = true;
        }
    }
}