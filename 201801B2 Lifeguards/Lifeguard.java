import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Lifeguard {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("lifeguards.in"));
        int n = sc.nextInt();

        int[] starts = new int[n];
        int[] ends = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = sc.nextInt();
            ends[i] = sc.nextInt();
        }
        sc.close();

        int answer = 0;

        for(int i = 0; i < n; i++) {
            boolean[] slots = new boolean[1000];
            int count = 0;

            for (int j = 0; j < n;  j++) {
                if (j == i) {
                    continue;
                }

                for(int k = starts[j]; k < ends[j]; k++) {
                    if (!slots[k]) {
                        slots[k] = true;
                        count ++;
                    }
                }
            }

            answer = Math.max(answer, count);
        }


        BufferedWriter bw = new BufferedWriter(new FileWriter("lifeguards.out"));
        bw.write(String.format("%d", answer));
        bw.close();

    }
}