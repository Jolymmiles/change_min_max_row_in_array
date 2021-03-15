import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String expected = "3\t3\t3\t\r\n2\t2\t\r\n1\t\r\n4\t4\t4\t";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testMainSecond() {
        String data = "3\r\n1 2 3\r\n4 5 6\r\n7 6 9";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "7\t6\t9\t\r\n4\t5\t6\t\r\n1\t2\t3\t";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testMainThird() {
        String data = "2\r\n1 2 3 4 5\r\n2 2 2 2 2";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "2\t2\t2\t2\t2\t\r\n1\t2\t3\t4\t5\t";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testMainForth() {
        String data = "6\r\n1 2 3\r\n1 2 3\r\n4 4 4 4 4\r\n5 5 5 5 5\r\n9 9 9 9 9 9\r\n1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "1\t2\t3\t\r\n1\t2\t3\t\r\n4\t4\t4\t4\t4\t\r\n5\t5\t5\t5\t5\t\r\n1\t\r\n9\t9\t9\t9\t9\t9\t";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

    @Test
    public void testMainFifth() {
        String data = "2\r\n1 1\r\n1 1";
        System.setIn(new ByteArrayInputStream(data.getBytes()));

        Main.main(null);
        String expected = "1\t1\t\r\n1\t1\t";
        String actual = outContent.toString();

        assertEquals(expected, actual);
    }

}