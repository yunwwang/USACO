import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2019 February Contest, Bronze Problem 3. Measuring Traffic
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=917
 */
public class Traffic {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("traffic.in"));
        int count = sc.nextInt();
        Sensor[] sensors = new Sensor[count];

        for (int i = 0; i < count; i++) {
            sensors[i] = new Sensor() {
                {
                    type = sc.next();
                    low = sc.nextInt();
                    high = sc.nextInt();
                }
            };
        }

        sc.close();

        Sensor before = new Sensor() {
            {
                low = 0;
                high = 10000;
                type = "before";
            }
        };

        Sensor after = new Sensor() {
            {
                low = 0;
                high = 10000;
                type = "after";
            }
        };

        for (int i = 0; i < count; i++) {
            before.update(sensors[count - i - 1]);
            after.update(sensors[i]);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("traffic.out"));
        writer.write(String.format("%d %d\n%d %d", before.low, before.high, after.low, after.high));
        writer.close();
    }
}

class Sensor {
    public int low;
    public int high;
    public String type;

    public void update(Sensor s) {
        if (s.type.equals("none")) {
            this.low = Math.max(this.low, s.low);
            this.high = Math.min(this.high, s.high);
        } else if ((this.type.equals("before") && s.type.equals("off"))
                || (this.type.equals("after") && s.type.equals("on"))) {
            this.low += s.low;
            this.high += s.high;
        } else if ((this.type.equals("before") && s.type.equals("on"))
                || (this.type.equals("after") && s.type.equals("off"))) {
            this.low = this.low > s.high ? this.low - s.high : 0;
            this.high = this.high > s.low ? this.high - s.low : 0;
        }
    }
}