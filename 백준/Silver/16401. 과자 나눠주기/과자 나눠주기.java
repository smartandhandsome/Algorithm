import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] L = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(L);

        int min = 1;
        int max = L[L.length - 1];
        int mid = (min + max) / 2;

        while (min <= max) {
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += L[i] / mid;
            }
            if (cnt >= M) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
            mid = (min + max) / 2;
        }

        System.out.println(mid);
    }

}
