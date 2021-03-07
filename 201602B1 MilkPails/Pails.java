import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2016 February Contest, Bronze Problem 1. Milk Pails
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=615
 */
public class Pails {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("pails.in"));
        int x = sc.nextInt();
        int y = sc.nextInt();
        int m = sc.nextInt();
        sc.close();

        int answer = 0;

        for (int i = 0; i * x < m; i++) {
            int j = (m - i * x) / y;
            answer = Math.max(answer, i*x+j*y);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("pails.out"));
        writer.write(String.format("%d", answer));
        writer.close();
    }
}