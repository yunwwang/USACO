import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            String inputFile = "test/" + i + ".in";
            String outputFile = "test/" + i + ".out";

            System.out.print("Testing " + inputFile + " ... ");

            Path source = Path.of(inputFile);
            Path target = Path.of("shuffle.in");

            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

            Shuffle.main(null);

            Scanner sourceScanner = new Scanner(Path.of(outputFile));
            Scanner targetScanner = new Scanner(Path.of("shuffle.out"));

            String sourceLine = sourceScanner.nextLine().trim();
            String targetLine = targetScanner.nextLine().trim();

            while (!isEmpty(sourceLine) && !isEmpty(targetLine)) {
                if (!sourceLine.equals(targetLine)) {
                    System.out.println("failed!");
                    break;
                }

                sourceLine = sourceScanner.hasNextLine() ? sourceScanner.nextLine().trim() : "";
                targetLine = targetScanner.hasNextLine() ? targetScanner.nextLine().trim() : "";
            }

            if (!isEmpty(sourceLine) || !isEmpty(targetLine)) {
                System.out.println("failed!");
            }

            System.out.println("passed!");
        }

        System.out.println("Done");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}