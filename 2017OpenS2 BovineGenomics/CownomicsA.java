import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Second Solution for USACO 2017 US Open Contest, Silver Problem 2. Bovine Genomics
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=739
 */
public class CownomicsA {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(Path.of("cownomics.in"));
    int n = sc.nextInt();
    int m = sc.nextInt();
    String[] spots = new String[n];
    String[] plains = new String[n];

    for(int i = 0; i < n; i++) {
      spots[i] = sc.next();
    }

    for(int i = 0; i < n; i++) {
      plains[i] = sc.next();
    }

    sc.close();

    int answer = 0;

    for(int i = 0; i < m; i++) {
      for (int j = i+1; j < m; j++) {
        for (int k = j+1; k < m; k++) {
          HashSet<String> spotSequence = new HashSet<String>();
          boolean match = false;
          for (int x = 0; x < n; x++) {
            StringBuilder sb = new StringBuilder();
            sb.append(spots[x].charAt(i));
            sb.append(spots[x].charAt(j));
            sb.append(spots[x].charAt(k));
            spotSequence.add(sb.toString());
          }

          for (int x = 0; x < n; x++) {
            StringBuilder sb = new StringBuilder();
            sb.append(plains[x].charAt(i));
            sb.append(plains[x].charAt(j));
            sb.append(plains[x].charAt(k));
            if (spotSequence.contains(sb.toString())) {
              match = true;
              break;
            }
          }
          if (!match) {
            answer ++;
          }

        }
      }
    }

    BufferedWriter bw = new BufferedWriter(new FileWriter("cownomics.out"));
    bw.write(String.format("%d", answer));
    bw.close();
  }
}