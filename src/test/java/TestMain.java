import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestMain {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    public TestMain() {
        Locale.setDefault(new Locale("en", "US"));
    }

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainFirst() {
        String data = "4\r\n1\r\n2 2\r\n3 3 3\r\n4 4 4";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{3, 3, 3, 2, 2, 1, 4, 4, 4};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r\\n]", "")
                        .split("\\t")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainSecond() {
        String data = "3\r\n1 2 3\r\n4 5 6\r\n7 6 9";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{7, 6, 9, 4, 5, 6, 1, 2, 3};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r\\n]", "")
                        .split("\\t")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainThird() {
        String data = "2\r\n1 2 3 4 5\r\n2 2 2 2 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{2, 2, 2, 2, 2, 1, 2, 3, 4, 5};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r\\n]", "")
                        .split("\\t")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainForth() {
        String data = "6\r\n1 2 3\r\n1 2 3\r\n4 4 4 4 4\r\n5 5 5 5 5\r\n9 9 9 9 9 9\r\n1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{1, 2, 3, 1, 2, 3, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 1, 9, 9, 9, 9, 9, 9};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r\\n]", "")
                        .split("\\t")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void testMainFifth() {
        String data = "2\r\n1 1\r\n1 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        int[] expected = new int[]{1, 1, 1, 1};
        int[] actual = Arrays.stream(
                outContent.toString()
                        .replaceAll("[\\r\\n]", "")
                        .split("\\t")
        ).mapToInt(Integer::parseInt).toArray();

        assertArrayEquals(expected, actual);
    }

}