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

        int count = 0;
        while (true) {
            int step = Integer.MAX_VALUE;
            for (int i = 0; i < length && step > 1; i++) {
                for (int j = i + i; j < length && step > 1; j++) {
                    step = Math.min(step, calculateStep(cows[i], cows[j]));
                }
            }

            for (Cow c : cows) {
                c.move(step);
            }

            for (Cow c : cows) {
                isBlockedOrInfinit(c, cows);
            }

            boolean flag = false;
            for (Cow c : cows) {
                if (!c.blocked && !c.infinite) {
                    flag = true;
                    break;
                }
            }

            long blockedCount = Arrays.stream(cows).filter(c -> c.blocked).count();
            long infinitCount = Arrays.stream(cows).filter(c -> c.infinite).count();
            count++;

            if (!flag) {
                break;
            }

        }

        for(Cow c : cows) {
            if (c.infinite) {
                System.out.println("Infinity");
            } else {
                if (c.direction == 'E') {
                    System.out.println( c.x - c.initX + 1);
                } else {
                    System.out.println(c.y - c.initY + 1);
                }
            }
        }
    }

    public static int calculateStep(Cow a, Cow b) {
        if (a.direction == b.direction || (a.x == b.x && a.y == b.y) || (a.blocked && b.blocked)) {
            return Integer.MAX_VALUE;
        }

        if (a.x == b.x) {
            return Math.abs(a.y - b.y);
        }
        if (a.y == b.y){
            return Math.abs(a.x - b.x);
        }
        
        if (a.direction == 'E') {            
            if (a.x > b.x && a.y < b.y) {
                return Integer.MAX_VALUE;
            }

            if (a.blocked) {
                return Math.abs(b.y - a.y);
            }

            if (b.blocked) {
                return Math.abs(a.x - b.x);
            }

            return Math.min(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
        }

        return calculateStep(b, a);
    }

    public static void isBlockedOrInfinit(Cow a, Cow[] cows) {
        if (a.blocked || a.infinite) {
            return;
        }

        boolean infinit = true;
        for (Cow b : cows) {
            if (a.direction == b.direction) {
                continue;
            }

            if (a.direction == 'E' && b.initY < a.initY) {
                if (b.x > a.x && (b.y > a.y || !b.blocked)) {
                    infinit = false;
                } else if (b.x == a.x && b.y > a.y) {
                    a.move(-1);
                    a.blocked = true;
                }
            } else if (a.direction == 'N' && b.initX< a.initX) {
                if (b.y > a.y && (b.x > a.x || !b.blocked)) {
                    infinit = false;
                } else if (b.y == a.y && b.x > a.x) {
                    a.move(-1);
                    a.blocked = true;
                }
            }
        }

        if (!a.blocked) {
            a.infinite = infinit;
        }
    }
}

class Cow {
    public char direction;
    public int initX;
    public int initY;
    public int x;
    public int y;
    public boolean blocked;
    public boolean infinite;

    public Cow(char direction, int x, int y) {
        this.direction = direction;
        this.x = this.initX = x;
        this.y = this.initY = y;
    }

    public void move(int step) {
        if (this.blocked) {
            return;
        }

        if (this.direction == 'E') {
            this.x += step;
        } else {
            this.y += step;
        }
    }
}