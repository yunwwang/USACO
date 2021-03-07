import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Gymnastic {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("gymnastic.in"));
        int k = sc.nextInt();
        int n = sc.nextInt();
        int[][] sessions = new int[k][n];

        for (int i = 0; i < k; i++) {
            for (int j = 0; j < n; j++) {
                sessions[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int answer = 0;

        for (int a = 1; a <= n; a++) {
            for (int b = 1 ; b <= n; b++) {
                if (a == b)
                    continue;

                int count = 0;
                for (int[] session : sessions) {
                    if (isBetter(a, b, session)) {
                        count ++;
                    }
                }

                if (count == k)
                    answer ++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("gymnastic.out"));
        bw.write(String.format("%d", answer));
        bw.close();
    }

    private static boolean isBetter(int a, int b, int[] session) {
        boolean foundA = false;;
        for (int i = 0; i < session.length; i++) {
            if (session[i] == a) {
                foundA = true;
            } else if (session[i] == b) {
                return foundA;
            }
        }
        return false;
    }
}

