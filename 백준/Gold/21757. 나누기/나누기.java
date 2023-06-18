import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] nums;
    static long[] sum;
    static long count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        sum = new long[N];
        sum[0] = nums[0];
        for (int i = 1; i < N; i++) {
            sum[i] += sum[i - 1] + nums[i];
        }


        if (sum[N - 1] % 4 != 0) {
            System.out.println(0);
        } else if (sum[N - 1] == 0) {  // 누적합이 0일때 N-1 을 제외하고 zero 개수 세서 zeroC3(조합)
            int zero = 0;
            for (int i = 0; i < N - 1; i++) {
                if (nums[i] == 0) {
                    zero += 1;
                }
            }
            count = (long) zero * (zero - 1) * (zero - 2) / (3 * 2); // zero C 3
            System.out.println(count);
        } else {
            long[] dp = {1, 0, 0, 0, 0}; // 0, 1구간 2구간 3구간 4구간
            long part = sum[N - 1] / 4;
            for (int i = 0; i < N; i++) {
                if (sum[i] % part != 0) {
                    continue;
                }
                int d = (int) (sum[i] / part);
                if (d < 1 || d >= 4) {
                    continue;
                }
                dp[d] += dp[d - 1];
            }
            System.out.println(dp[4]);
        }
    }
}