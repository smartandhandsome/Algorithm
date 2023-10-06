import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int M;
    private static int[] m;
    private static int[] c;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        m = new int[N + 1];
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            m[i + 1] = Integer.parseInt(s1[i]);
        }
        c = new int[N + 1];
        String[] s2 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            c[i + 1] = Integer.parseInt(s2[i]);
        }

        int totalC = Arrays.stream(c).sum();
        int[][] dp = new int[N + 1][totalC + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= totalC; j++) {
                if (j < c[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - c[i]] + m[i], dp[i - 1][j]);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= totalC; i++) {
            if (dp[N][i] >= M) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

}
