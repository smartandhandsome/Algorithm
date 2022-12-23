import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] studentsCounts = new int[N];
        for (int i = 0; i < N; i++) {
            studentsCounts[i] = sc.nextInt();
        }
        int mainCapacity = sc.nextInt();
        int subCapacity = sc.nextInt();
        long answer = N;
        for (int i = 0; i < N; i++) {
            if (mainCapacity >= studentsCounts[i]) {
                continue;
            }
            int temp = studentsCounts[i] - mainCapacity;
            answer += temp / subCapacity;
            if (temp % subCapacity != 0) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }
}