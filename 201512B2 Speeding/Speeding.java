import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Speeding{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("speeding.in"));
        int limit = sc.nextInt();
        int cow = sc.nextInt();
        Segment[] speedLimit = new Segment[limit];
        Segment[] speedCow = new Segment[cow];

        for (int i = 0; i < limit; i++) {
            speedLimit[i] = new Segment();
            speedLimit[i].length = sc.nextInt();
            speedLimit[i].speed = sc.nextInt();
        }

        for (int j = 0; j < cow; j++) {
            speedCow[j] = new Segment();
            speedCow[j].length = sc.nextInt();
            speedCow[j].speed = sc.nextInt();
        }

        sc.close();

        int max = 0;
        int i = 0; 
        int j = 0;

        while (i < limit && j < cow) {
            max = Math.max(max, speedCow[j].speed - speedLimit[i].speed);
            
            if (speedCow[j].length > speedLimit[i].length) {
                speedCow[j].length -= speedLimit[i].length;
                i++;
            } else if (speedLimit[i].length > speedCow[j].length) {
                speedLimit[i].length -= speedCow[j].length;
                j++;
            } else {
                i++;
                j++;
            }

        }

        Files.writeString(Path.of("speeding.out"), String.format("%d", max));
    }
}

class Segment {
    public int length;
    public int speed;
}