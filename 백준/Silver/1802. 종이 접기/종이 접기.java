import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            sb.append(check(s)).append("\n");
        }
        System.out.println(sb);
    }

    public static String check(String s) {
        int L = s.length();
        for (int j = 0; j < L / 2; j++) {
            if (s.charAt(j) == s.charAt(L - 1 - j)) {
                return "NO";
            }
        }
        if (L <= 3) {
            return "YES";
        }
        return check(s.substring(0, L / 2));
    }
}
