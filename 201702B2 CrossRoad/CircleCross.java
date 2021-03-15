import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * Solution for USACO 2017 February Contest, Bronze Problem 2. Why Did the Cow Cross the Road II
 * http://www.usaco.org/index.php?page=viewproblem2&cpid=712
 */
public class CircleCross{
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(Path.of("circlecross.in"));
    String input = sc.next();
    sc.close();

    int[][] pos = new int[26][2];

    for(int i = 0; i < 52; i++) {
      char c = input.charAt(i);
      int index = c - 'A';
      if (pos[index][0] == 0) {
        pos[index][0] = i + 1;
      } else {
        pos[index][1] = i+1;
      }
    }

    int answer = 0;

    for (int i = 0; i < 26; i++) {
      for (int j = i+1; j < 26; j++) {
        if ((pos[i][0] < pos[j][0] && pos[j][0] < pos[i][1] && pos[i][1] < pos[j][1] )||(pos[j][0] < pos[i][0] && pos[i][0] < pos[j][1] && pos[j][1] < pos[i][1])) {
          answer ++;
        }
      }
    }

    BufferedWriter bw = new BufferedWriter(new FileWriter("circlecross.out"));
    bw.write(String.format("%d", answer));
    bw.close();
    
  }
}