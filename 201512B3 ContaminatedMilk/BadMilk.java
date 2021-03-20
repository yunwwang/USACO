import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for USACO 2015 December Contest, Bronze Problem 3. Contaminated Milk
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=569
 */
public class BadMilk {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("badmilk.in"));
        sc.nextInt();
        sc.nextInt();
        int d = sc.nextInt();
        int s = sc.nextInt();

        Data[] data = new Data[d];

        for (int i = 0; i < d; i++) {
            int p = sc.nextInt();
            int mm = sc.nextInt();
            int t = sc.nextInt();
            data[i] = new Data(p, mm, t);
        }

        HashSet<Integer> badMilk = new HashSet<>();

        for (int i = 0; i < s; i++) {
            int p = sc.nextInt();
            int t = sc.nextInt();
            HashSet<Integer> hs = new HashSet<>();

            for (Data row : data) {
                if (row.person == p) {
                    if (row.time < t) {
                        hs.add(row.milk);
                    }
                }
            }

            if (badMilk.size() == 0) {
                badMilk = hs;
            } else {
                badMilk.retainAll(hs);
            }
        }
        sc.close();

        int answer = 0;

        for(Integer i : badMilk) {
            HashSet<Integer> person = new HashSet<>();
            for(Data row : data) {
                if (row.milk == i) {
                    person.add(row.person);
                }
            }

            answer = Math.max(answer, person.size());

        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("badmilk.out"));
        bw.write(String.format("%d", answer));
        bw.close();
    }
}

class Data {
    public int person;
    public int milk;
    public int time;

    public Data(int p, int m, int t) {
        person = p;
        milk = m;
        time = t;
    }
}