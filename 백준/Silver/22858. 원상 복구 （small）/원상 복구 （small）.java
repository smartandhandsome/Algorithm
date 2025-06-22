import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] fLine = in.readLine().split(" ");
        int N = Integer.parseInt(fLine[0]);
        int K = Integer.parseInt(fLine[1]);

        int[] S = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] D = Arrays.stream(in.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        int[] answer = S.clone();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                answer[D[j] - 1] = S[j];
            }
            S = answer.clone();
        }
        Arrays.stream(answer)
                .forEach(i -> System.out.print(i + " "));
    }
}
