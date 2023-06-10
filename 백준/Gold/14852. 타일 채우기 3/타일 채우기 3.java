import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        dp[0] = 1;
        dp[1] = 2;
        if (N > 1) {
            dp[2] = 7;
        }
        long sum = dp[0];
        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] * 2 + (dp[i - 2] * 3) + sum * 2) % 1000000007;
            sum = (sum + dp[i - 2]) % 1000000007;
        }
        bw.write(String.valueOf(dp[N]));
        bw.flush();
        bw.close();
    }
}