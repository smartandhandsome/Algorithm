import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] children = Arrays.stream(
                        br.readLine()
                                .split(" ")
                )
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] cha = new int[N - 1];
        for (int i = 0; i < children.length - 1; i++) {
            cha[i] = children[i + 1] - children[i];
        }
        Arrays.sort(cha);

        int answer = 0;
        for (int i = 0; i < N - K; i++) {
            answer += cha[i];
        }
        System.out.println(answer);
    }
}
