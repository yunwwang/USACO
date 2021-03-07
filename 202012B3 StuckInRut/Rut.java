import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Rut {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int length = sc.nextInt();
        Cow[] cows = new Cow[length];

        for (int i = 0; i < length; i++) {
            cows[i] = new Cow(sc.next().charAt(0), sc.nextInt(), sc.nextInt());
        }

        sc.close();

        while (true) {
            for (Cow c : cows) {
                if (!c.blocked) {
                    c.distance = Integer.MAX_VALUE;
                }
            }

            for (int i = 0; i < length; i++) {
                if (cows[i].direction != 'E')
                    continue;

                for (int j = 0; j < length; j++) {
                    if (cows[j].direction != 'N')
                        continue;

                    calculateDistance(cows[i], cows[j]);
                }
            }

            // Block the cow with minimum distance
            ArrayList<Cow> tobeBlocked = new ArrayList<Cow>();
            for (Cow c : cows) {
                if (c.blocked || c.infinite) {
                    continue;
                }

                if (c.distance == Integer.MAX_VALUE) {
                    c.infinite = true;
                } else if (tobeBlocked.isEmpty() || c.distance == tobeBlocked.get(0).distance) {
                    tobeBlocked.add(c);
                } else if (c.distance < tobeBlocked.get(0).distance) {
                    tobeBlocked.clear();
                    tobeBlocked.add(c);
                }
            }

            for (Cow c : tobeBlocked) {
                c.blocked = true;
            }

            boolean flag = Arrays.stream(cows).allMatch(c -> c.blocked || c.infinite);

            if (flag) {
                break;
            }

        }

        for (Cow c : cows) {
            if (c.infinite) {
                System.out.println("Infinity");
            } else {
                System.out.println(c.distance);
            }
        }
    }

    public static void calculateDistance(Cow a, Cow b) {
        if (a.x == b.x && a.y == b.y) {
            return;
        }

        if (a.y > Math.max(b.distance, b.y + b.distance) || b.x > Math.max(a.distance, a.x + a.distance)) {
            return;
        }

        int dx = b.x - a.x;
        int dy = a.y - b.y;

        if (dx < 0 || dy < 0) {
            return;
        }

        if (dx > dy) {
            a.distance = Math.min(a.distance, dx);
        } else if (dy > dx) {
            b.distance = Math.min(b.distance, dy);
        }
    }
}

class Cow {
    public char direction;
    public int x;
    public int y;
    public boolean blocked;
    public boolean infinite;
    public int distance;

    public Cow(char direction, int x, int y) {
        this.direction = direction;
        this.x = x;
        this.y = y;
    }

}