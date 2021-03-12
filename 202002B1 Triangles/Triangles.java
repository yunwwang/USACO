import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Triangles {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("triangles.in"));
        int n = sc.nextInt();
        int[] x = new int[n];
        int[] y = new int[n];

        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        sc.close();

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (x[i] != x[j] && y[i] != y[i]) {
                    continue;
                }
                for (int k = 0; k < n && k != j; k++) {
                    if (x[i] == x[j] && (y[k] == y[i] || y[k] == y[j])) {
                        answer = Math.max(Math.abs(y[i] - y[j]) * Math.abs(x[i] - x[k]), answer);
                    } else if (y[i] == y[j] && (x[k] == x[i] || x[k] == x[j])) {
                        answer = Math.max(Math.abs(x[i] - x[j]) * Math.abs(y[i] - y[k]), answer);
                    }
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter("triangles.out"));
        bw.write(String.format("%d", answer));
        bw.close();
    }
}
