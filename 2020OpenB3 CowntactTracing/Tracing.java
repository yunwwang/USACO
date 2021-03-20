import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for USACO 2020 US Open Contest, Bronze Problem 3. Cowntact Tracing
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=1037
 */
public class Tracing {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("tracing.in"));
        int N = sc.nextInt();
        int T = sc.nextInt();

        String line = sc.next();
        boolean[] state = new boolean[N];
        for (int i = 0; i < line.length(); i++) {
            state[i] = line.charAt(i) == '1';
        }

        Pair[] input = new Pair[250];
        int minTime = 251;
        int maxTime = 0;
        for (int i = 0; i < T; i++) {
            int t = sc.nextInt();
            maxTime = Math.max(maxTime, t);
            minTime = Math.min(minTime, t);
            input[t - 1] = new Pair(sc.nextInt(), sc.nextInt());
        }
        sc.close();

        var patientZero = new HashSet<Integer>();
        int minK = maxTime;
        int maxK = 0;

        for (int i = 0; i < N; i++) {
            for (int K = 0; K <= maxTime; K++) {
                boolean[] simulation = new boolean[N];
                int[] handshakeCount = new int[N];

                simulation[i] = true;

                for (int t = minTime - 1; t < maxTime; t++) {
                    var pair = input[t];
                    if (pair == null) {
                        continue;
                    }
                    var x = pair.x - 1;
                    var y = pair.y - 1;
                    if (simulation[x]) {
                        handshakeCount[x]++;
                    }
                    if (simulation[y]) {
                        handshakeCount[y]++;
                    }

                    if (handshakeCount[x] <= K && simulation[x]) {
                        simulation[y] = true;
                    }
                    if (handshakeCount[y] <= K && simulation[y]) {
                        simulation[x] = true;
                    }
                }

                boolean match = true;
                for (int j = 0; j < N; j++) {
                    if (simulation[j] != state[j]) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    patientZero.add(i);
                    minK = Math.min(minK, K);
                    maxK = Math.max(maxK, K);
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("tracing.out"));
        bw.write(String.format("%d %d ", patientZero.size(), minK));

        if (maxK >= maxTime) {
            bw.write("Infinity");
        } else {
            bw.write(String.format("%d", maxK));
        }

        bw.close();
    }
}

class Pair {
    public int x;
    public int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}