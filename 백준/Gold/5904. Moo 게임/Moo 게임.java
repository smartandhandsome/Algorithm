import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) - 1;

        System.out.println(moo(0, -1));
    }

    public static char moo(int prev, int prevIdx) {
        int index = prevIdx + 1;
        int cur = prev * 2 + index + 3;
        if (N <= cur) {
            if (N == prev) {
                return 'm';
            } else if (N < prev + index + 3) {
                return 'o';
            } else if (N == prev + index + 3) {
                return 'm';
            } else {
                N -= (prev + index + 3);
                return moo(0, -1);
            }
        } else {
            return moo(cur, index);
        }
    }
}