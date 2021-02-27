import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class MixMilk
{
    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(Path.of("mixmilk.in"));
        Bucket b1 = new Bucket();
        Bucket b2 = new Bucket();
        Bucket b3 = new Bucket();
        b1.capacity = sc.nextInt();
        b1.amount = sc.nextInt();
        b2.capacity = sc.nextInt();
        b2.amount = sc.nextInt();
        b3.capacity = sc.nextInt();
        b3.amount = sc.nextInt();
        sc.close();

        for (int i = 0; i < 99; i+=3) {
            b2.add(b1);
            b3.add(b2);
            b1.add(b3);
        }

        b2.add(b1);

        Files.writeString(Path.of("mixmilk.out"), String.format("%d\n%d\n%d", b1.amount, b2.amount, b3.amount));
    }
}

class Bucket
{
    public int capacity;
    public int amount;

    public void add(Bucket in) {
        int remain = this.capacity - this.amount;

        if (in.amount > remain) {
            in.amount -= remain;
            this.amount = this.capacity;
        } else {
            this.amount += in.amount;
            in.amount = 0;
        }
    }
}