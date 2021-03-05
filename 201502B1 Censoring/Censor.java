import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2015 February Contest, Bronze Problem 1. Censoring (Bronze)
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=526
 */
public class Censor {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("censor.in"));

        String source = sc.next();
        String target = sc.next();

        sc.close();

        StringBuilder out = new StringBuilder();

        for(int i =0; i<source.length(); i++) {
            out.append(source.charAt(i));

            if (out.length() >= target.length() && out.substring(out.length() - target.length()).equals(target)) {
                out.delete(out.length() - target.length(),  out.length());
            }
        }   
        
        BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
        writer.write(out.toString());
        writer.close();
    }
}
