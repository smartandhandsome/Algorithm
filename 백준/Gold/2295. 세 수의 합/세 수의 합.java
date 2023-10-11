import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());


        int[] U = new int[N];
        for (int i = 0; i < N; i++) {
            U[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(U);

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                set.add(U[i] + U[j]);
            }
        }
        System.out.println(a(N, U, set));
    }

    private static int a(int N, int[] U, Set<Integer> set) {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (set.contains(U[i] - U[j])) {
                    return U[i];
                }
            }
        }
        return 0;
    }
}
