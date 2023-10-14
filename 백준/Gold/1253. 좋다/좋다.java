import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int cnt = 0;
        int[] A = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();

        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;

            while (true) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }
                if (left >= right) {
                    break;
                }

                int sum = A[left] + A[right];
                if (sum == A[i]) {
                    cnt++;
                    break;
                } else if (sum > A[i]) {
                    right--;
                } else if (sum < A[i]) {
                    left++;
                }
            }
        }
        System.out.println(cnt);
    }
}
