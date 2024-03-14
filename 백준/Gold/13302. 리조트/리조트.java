import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    static Set<Integer> holidays;
    static int N, M;
    static int[][] dp;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        if (M == 0) {
            holidays = Collections.emptySet();
        } else {
            holidays = Arrays.stream(br.readLine().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
        }

        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dfs(1, 0, 0);
        System.out.println(result);
    }

    public static void dfs(int day, int cp, int sum) {
        if (day > N) {
            result = Math.min(result, sum);
            return;
        }

        if (dp[day][cp] <= sum) {
            return;
        }

        dp[day][cp] = sum;
        if (holidays.contains(day)) {
            dfs(day + 1, cp, sum);
        }
        if (cp >= 3) {
            dfs(day + 1, cp - 3, sum);
        }
        dfs(day + 1, cp, sum + 10_000);
        dfs(day + 3, cp + 1, sum + 25_000);
        dfs(day + 5, cp + 2, sum + 37_000);
    }
}
