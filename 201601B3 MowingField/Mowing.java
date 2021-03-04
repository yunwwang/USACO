import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Solution for USACO 2016 January Contest, Bronze Problem 3. Mowing the Field
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=593
 */
public class Mowing {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("mowing.in"));
        int count = sc.nextInt();
        char[] directions = new char[count];
        int[] steps = new int[count];
        ArrayList<Cell> cells = new ArrayList<Cell>();
        int x = Integer.MAX_VALUE;

        int i = 0;
        for (i = 0; i < count; i++) {
            directions[i] = sc.next().charAt(0);
            steps[i] = sc.nextInt();
        }

        sc.close();

        for (i = 0; i < count; i++) {
            x = walk(directions[i], steps[i], cells, x);
        }

        if (x == Integer.MAX_VALUE) {
            x = -1;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("mowing.out"));
        writer.write(String.format("%d", x));
        writer.close();
    }

    private static int walk(char direction, int step, ArrayList<Cell> cells, int x) {
        int j = 0;
        if (cells.size() == 0) {
            cells.add((new Cell(0, 0)));
            j = 1;
        }

        int size = cells.size();
        Cell current = cells.get(size -1);

        for (; j < step; j++) {
            Cell next = current.nextCell(direction);
            int k = checkCrossing(cells, next, size);

            if (k > 0) {
                int distance = size - k + j;
                x = Math.min(distance, x);
            } 

            cells.add(next);
            current = next;
        }

        return x;
    }

    private static int checkCrossing(ArrayList<Cell> cells, Cell next, int size) {
        for(int k = size - 1; k >=0 ; k--) {
            if (cells.get(k).equals(next)) {
                return k;
            }
        }

        return -1;
    }

}

class Cell {
    public int x;
    public int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell nextCell(char direction) {
        switch (direction) {
            case 'N':
                return new Cell(this.x, this.y + 1);
            case 'E':
                return new Cell(this.x + 1, this.y);
            case 'S':
                return new Cell(this.x, this.y - 1);
            case 'W':
                return new Cell(this.x - 1, this.y);
            default:
                return null;
        }
    }

    public boolean equals(Cell cell) {
        if (this.x == cell.x && this.y == cell.y) {
            return true;
        }
        return false;
    }
}