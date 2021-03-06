import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2017 December Contest, Bronze Problem 3. Milk Measurement
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=761
 */
public class Measurement {
    public static void main(String[] args) throws IOException {
        int bessieMilk = 7;
        int elsieMilk = 7;
        int mildredMilk = 7;

        boolean bessieWin = true;
        boolean elsieWin = true;
        boolean mildredWin = true;

        Cow[] changes = new Cow[100];

        int count = 0;

        Scanner sc = new Scanner(Path.of("measurement.in"));
        int length = sc.nextInt();

        for (int i = 0; i < length; i++) {
            int index = sc.nextInt() - 1;
            changes[index] = new Cow(sc.next(), sc.nextInt());
        }

        sc.close();

        for (int i = 0; i < changes.length; i++) {
            if (changes[i] == null) {
                continue;
            }

            switch (changes[i].name) {
                case "Bessie":
                    bessieMilk += changes[i].milk;
                    break;

                case "Elsie":
                    elsieMilk += changes[i].milk;
                    break;

                case "Mildred":
                    mildredMilk += changes[i].milk;
                    break;
            }

            int highest = Math.max(bessieMilk, Math.max(elsieMilk, mildredMilk));

            boolean bessieToWin = bessieMilk == highest;
            boolean elsieToWin = elsieMilk == highest;
            boolean mildredToWin = mildredMilk == highest;

            if ((bessieToWin != bessieWin) || (elsieToWin != elsieWin) || (mildredToWin != mildredWin)) {
                bessieWin = bessieToWin;
                elsieWin = elsieToWin;
                mildredWin = mildredToWin;
    
                count++;
            }
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("measurement.out"));
        writer.write(String.format("%d", count));
        writer.close();
    }
}

class Cow {
    public String name;
    public int milk;

    public Cow(String name, int milk) {
        this.name = name;
        this.milk = milk;
    }
}