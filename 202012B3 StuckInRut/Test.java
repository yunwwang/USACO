import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        String name = "measurement";

        for (int i = 1; i <= 10; i++) {
            test(i, name);
        }

        System.out.println("Done");
    }

    private static void test(int i, String name) throws IOException {
        String inputFile = "test/" + i + ".in";
        String outputFile = "test/" + i + ".out";

        System.out.print("Testing " + inputFile + " ... ");


        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        InputStream stdin = System.in;
        PrintStream stdout = System.out;

        System.setIn(new FileInputStream(inputFile));
        System.setOut(new PrintStream(bos));
        Rut.main(null);


        Scanner sourceScanner = new Scanner(Path.of(outputFile));
        Scanner targetScanner = new Scanner(bos.toString());
        bos.close();
        System.setIn(stdin);
        System.setOut(stdout);

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

        sourceScanner.close();
        targetScanner.close();
    }

    private static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
