import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] dp = new int[T + 1][W + 1];

        // 자두 첫 위치 1
        for (int i = 1; i <= T; i++) {
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j <= W; j++) {
                if (n == 1) {
                    if (j % 2 == 0) {
                        if (j == 0) {
                            dp[i][0] = dp[i - 1][0] + 1;
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1);
                        }
                    }
                    if (j % 2 == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                    }
                }
                if (n == 2) {
                    if (j % 2 == 0) {
                        if (j == 0) {
                            dp[i][0] = dp[i - 1][0];
                        } else {
                            dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - 1]);
                        }
                    }
                    if (j % 2 == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1);
                    }
                }
            }
        }
        int answer = 0;
        for (int i = 0; i <= W; i++) {
            answer = Math.max(answer, dp[T][i]);
        }
        System.out.println(answer);
    }
}
