import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 10001;

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        int target = Integer.parseInt(s[0]);
        int[] dp = new int[target + 1];
        Arrays.fill(dp, MAX);
        int[] woks = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.stream(woks).forEach(value -> dp[value] = 1);

        for (int i = 0; i < woks.length; i++) {
            for (int j = i + 1; j < woks.length; j++) {
                if (woks[i] + woks[j] > target) {
                    continue;
                }
                dp[woks[i] + woks[j]] = 1;
            }
        }

        for (int i = 0; i <= target; i++) {
            for (int j = 0; j <= target; j++) {
                if (i - j < 0) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }

        System.out.println(dp[target] == 10001 ? -1 : dp[target]);
    }
}
