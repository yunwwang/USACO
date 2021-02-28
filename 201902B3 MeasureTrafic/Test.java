import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        String name = "traffic";
        for (int i = 1; i <= 10; i++) {
            String inputFile = "test/" + i + ".in";
            String outputFile = "test/" + i + ".out";

            System.out.print("Testing " + inputFile + " ... ");

            Path source = Path.of(inputFile);
            Path target = Path.of(name + ".in");

            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

            Traffic.main(null);

            Scanner sourceScanner = new Scanner(Path.of(outputFile));
            Scanner targetScanner = new Scanner(Path.of(name + ".out"));

            String sourceLine = sourceScanner.nextLine().trim();
            String targetLine = targetScanner.nextLine().trim();

            boolean failed = false;

            while (!isEmpty(sourceLine) && !isEmpty(targetLine) && !failed) {
                if (!sourceLine.equals(targetLine)) {
                    failed = true;
                    System.out.println("failed!");
                } else {
                    sourceLine = sourceScanner.hasNextLine() ? sourceScanner.nextLine().trim() : "";
                    targetLine = targetScanner.hasNextLine() ? targetScanner.nextLine().trim() : "";
                }
            }

            if (!failed) {
                if (!isEmpty(sourceLine) || !isEmpty(targetLine)) {
                    System.out.println("failed!");
                } else {
                    System.out.println("passed!");
                }
            }
        }

        System.out.println("Done");
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}