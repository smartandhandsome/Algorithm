import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        long temp = 1;
        boolean overflow = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int gcd = gcd(A[i], B[j]);
                temp *= gcd;
                if (temp >= 1000000000) {
                    temp %= 1000000000;
                    overflow = true;
                }
                A[i] /= gcd;
                B[j] /= gcd;
            }
        }
        String answer = String.valueOf(temp);
        StringBuilder zero = new StringBuilder();
        if (answer.length() < 9 && overflow) {
            for (int i = 0; 9 > answer.length() + i; i++) {
                zero.append("0");
            }
        }
        System.out.print(zero + answer);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}