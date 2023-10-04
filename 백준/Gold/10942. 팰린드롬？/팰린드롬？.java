import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] number = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] dp = new boolean[N + 1][N + 1]; // [길이][시작 index]
        Arrays.fill(dp[1], true);
        for (int i = 2; i <= N; i++) {
            for (int j = 1; j <= N - i + 1; j++) {
                if (i == 2) {
                    if (number[j] == number[j + 1]) {
                        dp[i][j] = true;
                    }
                } else {
                    if (number[j] == number[j + i - 1] && dp[i - 2][j + 1]) {
                        dp[i][j] = true;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append((dp[E - S + 1][S] ? 1 : 0)).append("\n");
        }

        System.out.println(sb);
    }
}
