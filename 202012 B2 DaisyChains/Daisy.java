import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for USACO 2020 December Contest, Bronze Problem 2. Daisy Chains
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1060
 */
public class Daisy {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] flowers = new int[n];

        for (int i = 0; i < n; i++) {
            flowers[i] = sc.nextInt();
        }
        sc.close();

        int answer = n;

        for (int i = 0; i < n; i++) {
            HashSet<Integer> pedals = new HashSet<Integer>();
            pedals.add(flowers[i]);
            
            int sum = flowers[i];

            for (int j = i + 1; j < n; j++) {
                sum += flowers[j];
                pedals.add(flowers[j]);

                if (sum % (j - i + 1) == 0 && pedals.contains(sum / (j - i + 1))) {
                    answer++;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out));
        bw.write(String.format("%d", answer));
        bw.close();
    }
}
