import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Rut2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        Cow2[] cows = new Cow2[length];

        for (int i = 0; i < length; i++) {
            cows[i] = new Cow2(sc.next().charAt(0), sc.nextInt(), sc.nextInt());
        }

        sc.close();

        HashSet<Integer> hs = new HashSet<Integer>();

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                hs.add(Math.abs(cows[i].x - cows[j].x));
                hs.add(Math.abs(cows[i].y - cows[j].y));
            }
        }

        ArrayList<Integer> steps = new ArrayList<Integer>(hs);
        Collections.sort(steps);

        for (int step : steps) {
            for (int i = 0; i < length; i++) {
                if (cows[i].direction != 'E')
                    continue;

                for (int j = 0; j < length; j++) {
                    // Skip same direction
                    if (cows[j].direction != 'N')
                        continue;
                    // Skip if i is south of j or i is eash of j;
                    if (cows[i].x >= cows[j].x || cows[i].y <= cows[j].y)
                        continue;

                    if (cows[i].x + step == cows[j].x && cows[j].y + Math.min(cows[j].distance, step) > cows[i].y) {
                        cows[i].distance = Math.min(cows[i].distance, step);
                    } else if (cows[j].y + step == cows[i].y
                            && cows[i].x + Math.min(cows[i].distance, step) > cows[j].x) {
                        cows[j].distance = Math.min(cows[j].distance, step);
                    }
                }
            }
        }

        for (Cow2 c : cows) {
            System.out.println(c.distance == Integer.MAX_VALUE ? "Infinity" : c.distance);
        }
    }
}

class Cow2 {
    public char direction;
    public int x;
    public int y;
    public int distance = Integer.MAX_VALUE;

    public Cow2(char direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

}
