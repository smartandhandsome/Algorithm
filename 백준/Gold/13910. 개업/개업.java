import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] woks = new int[M];
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 10001);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            woks[i] = Integer.parseInt(st.nextToken());
            if (woks[i] <= N) {
                dp[woks[i]] = 1; // 한 손
            }
        }
        // 양손
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (woks[i] + woks[j] <= N && i != j) {
                    dp[woks[i] + woks[j]] = 1;
                }
            }
        }

        // 횟수 증가
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if (i - j < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }
//        System.out.println(Arrays.toString(dp));
        System.out.println(dp[N] == 10001 ? -1 : dp[N]);
    }
}
