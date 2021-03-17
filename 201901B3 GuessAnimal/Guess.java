import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Solution for USACO 2019 January Contest, Bronze Problem 3. Guess the Animal
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=893
 * Some tests failed. Need to figure out the reason.
 */
public class Guess {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(Path.of("guess.in"));
    int n = sc.nextInt();
    ArrayList<HashSet<String>> terms = new ArrayList<HashSet<String>>();
    sc.nextLine();

    for (int i = 0; i < n; i++) {
      String line = sc.nextLine();
      String[] tokens = line.split(" ");
      terms.add(new HashSet<String>(Arrays.asList(tokens)));
    }
    sc.close();

    int answer = 0;

    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        HashSet<String> t1 = new HashSet<String>(terms.get(i));
        t1.retainAll(terms.get(j));
        answer = Math.max(answer, t1.size());
      }
    }

    BufferedWriter wr = new BufferedWriter(new FileWriter("guess.out"));
    wr.write(String.format("%d", answer + 1));
    wr.close();
  }
}