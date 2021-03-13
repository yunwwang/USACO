import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/** 
 * Solution for USACO 2016 February Contest, Bronze Problem 3. Load Balancing
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=617
 */
public class Balancing{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("balancing.in"));
        int n = sc.nextInt();
        sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }

        sc.close();

        int answer = n;

        for (int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int a = x[i] + 1;
                int b = y[j] + 1;

                int[] count = new int[4];

                for (int k = 0; k < n; k++) {
                    if (x[k] < a && y[k] < b) {
                        count[0] ++;
                    } else if (x[k] > a && y[k] > b) {
                        count[1] ++;
                    } else if (x[k] < a && y[k] > b) {
                        count[2] ++;
                    } else {
                        count[3] ++;
                    }
                }

                int m = 0;

                for (int c : count) {
                    m = m < c ? c : m;
                }

                answer = Math.min(answer, m);
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("balancing.out"));
        writer.write(String.format("%d", answer));
        writer.close();
    }
}



