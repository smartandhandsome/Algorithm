import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static long[] nums;
    static long[] sum;
    static long part;
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
        } else if (sum[N - 1] == 0) {
            int zero = 0;
            for (int i = 0; i < N - 1; i++) {
                if (nums[i] == 0) {
                    zero += 1;
                }
            }
            count = (long) zero * (zero - 1) * (zero - 2) / (3 * 2); // zeroCr
            System.out.println(count);
        } else {
            part = sum[N - 1] / 4;
            dfs(0, part);
            System.out.println(count);
        }
    }

    private static void dfs(int idx, long target) {
        if (idx == N && target == part * 5) {
            count++;
        }
        for (int i = idx; i < N; i++) {
            if (sum[i] == target) {
                dfs(i + 1, target + part);
            }
        }
    }
}