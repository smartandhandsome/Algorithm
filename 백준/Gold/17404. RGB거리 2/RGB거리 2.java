import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static final int MAX = 1000000;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] prices = new int[N][];
        for (int i = 0; i < N; i++) {
            prices[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int[][] dp = new int[N][3];
            for (int j = 0; j < N; j++) {
                dp[j] = new int[]{MAX, MAX, MAX};
            }

            dp[0][i] = prices[0][i];
            for (int j = 1; j < N; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + prices[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + prices[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + prices[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    answer = Math.min(answer, dp[N - 1][j]);
                }
            }
        }
        System.out.println(answer);
    }
}
