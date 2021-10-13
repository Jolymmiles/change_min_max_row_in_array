import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = Integer.parseInt(sc.nextLine()), max = 0, maxLine = 0, min = 100, minLine = 0, check = 0;
        String timeLine;
        String[] array = new String[N];
        for (int line = 0; line < N; line++) {
            array[line] = sc.nextLine();
        }
        for (int line = 0; line < N; line++) {
            if (array[line].length() < min) {
                min = array[line].length();
                minLine = line;
            } else if (array[line].length() > max) {
                max = array[line].length();
                maxLine = line;
            }
            check += array[line].length();
        }
        if (check % N != 0) {
            timeLine = array[minLine];
            array[minLine] = array[maxLine];
            array[maxLine] = timeLine;
        } else if (check % N == 0) {
            timeLine = array[0];
            array[minLine] = array[N-1];
            array[N-1] = timeLine;
        }
        for (String line : array) {
            for (String digit : line.split(" ")) {
                System.out.print(Integer.parseInt(digit) + "\t");
            }
            System.out.println();
        }

    }
}