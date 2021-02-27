import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class LostCow {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("lostcow.in"));
        int start = sc.nextInt();
        int end = sc.nextInt();
        sc.close();

        int step = 1;
        int current = start + step;
        boolean found = false;
        int distance = 0;

        while(!found) {
            if (current > start) {
                if (current < end || end < start) {
                    step *= 2;
                    distance += step;
                    current = start - step;
                } else {
                    found = true;
                }
            } else {
                if (current > end || end > start) {
                    step *= 2;
                    distance += step;
                    current = start + step;
                } else {
                    found = true;
                }
            }
        }

        distance += Math.abs(end - start);

        Files.writeString(Path.of("lostcow.out"), String.format("%d", distance));        
    }
}