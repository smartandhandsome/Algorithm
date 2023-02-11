import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    int n;
    int k;
    int[] coins;

    public Main(int n, int k) {
        this.n = n;
        this.k = k;
        this.coins = new int[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Main m = new Main(n, k);
        for (int i = 0; i < n; i++) {
            m.coins[i] = Integer.parseInt(br.readLine());
        }
        System.out.print(m.solution());
    }

    public int solution() {
        int[] dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            for (int money = 1; money <= k; money++) {
                if (money - coins[i] >= 0) {
                    dp[money] += dp[money - coins[i]];
                }
            }
        }
        return dp[k];
    }
}