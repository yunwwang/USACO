import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class CensorSplit {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Path.of("censor.in"));
        String source = sc.next();
        String target = sc.next();
        sc.close();
        String[] split;

        do {
            split = source.split(target);
            if (split.length > 1) {
                StringBuilder sb = new StringBuilder();
                for(String substr : split) {
                    sb.append(substr);
                }
                source = sb.toString();
            }
        }while(split.length > 1);

        BufferedWriter writer = new BufferedWriter(new FileWriter("censor.out"));
        writer.write(source);
        writer.close();
    }

}
