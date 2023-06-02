import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] honey = new int[N];
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            honey[i] = sc.nextInt();
            sum[i] = (i - 1 >= 0 ? sum[i - 1] : 0) + honey[i];
        }
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (i > 0) {
                // 1번 벌 (0), 2번 벌 (i), 꿀(N-1)
                int harvest1 = sum[N - 1] - honey[0] - honey[i];
                int harvest2 = sum[N - 1] - sum[i];
                answer = Math.max(harvest1 + harvest2, answer);
                // 양 옆에 벌, 꿀(i)
                int harvest5 = sum[N - 1] - honey[N - 1] - sum[i - 1];
                int harvest6 = sum[i] - honey[0];
                answer = Math.max(harvest5 + harvest6, answer);
            }
            if (i != N - 1) {
                // 1번 벌(N-1), 2번 벌(i), 꿀(0)
                int harvest3 = sum[N - 1] - honey[N - 1] - honey[i];
                int harvest4 = sum[i] - honey[i];
                answer = Math.max(harvest3 + harvest4, answer);
            }
        }
        System.out.println(answer);
        sc.close();
    }
}