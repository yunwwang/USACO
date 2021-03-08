import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Solution for USACO 2016 US Open Contest, Bronze Problem 1. Diamond Collector
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=639
 */
public class Diamond {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("diamond.in"));
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> diamonds = new ArrayList<Integer>();

        for (int i = 0; i < n; i++) {
            diamonds.add(sc.nextInt());
        }
        sc.close();

        Collections.sort(diamonds);

        int answer = 0;

        for (int i = 0; i < diamonds.size(); i++) {
            int j = Math.max(answer, i + 1);

            while (j < diamonds.size() && diamonds.get(j) - diamonds.get(i) <= k) {
                j++;
            }

            answer = Math.max(answer, j - i);
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("diamond.out"));
        bw.write(String.format("%d", answer));
        bw.close();
    }
}
