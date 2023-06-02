import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String code = sc.nextLine();
        int N = code.length();
        int[] dp = new int[N + 1];
        dp[0] = 1;
        if (code.charAt(0) == '0') {
            System.out.println(0);
            System.exit(0);
        }
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            int prev = (code.charAt(i - 2) - '0') * 10;
            int cur = code.charAt(i - 1) - '0';
            if (cur >= 1 && cur <= 9) {
                dp[i] += dp[i - 1];
            }
            if (prev == 0) {
                continue;
            }
            if (prev + cur >= 10 && prev + cur <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %= 1000000;
            }
        }
        System.out.println(dp[N]);
    }
}