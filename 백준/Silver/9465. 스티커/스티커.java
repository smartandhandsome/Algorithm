import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());

            int[][] sticker = new int[2][N];
            sticker[0] = getS(br);
            sticker[1] = getS(br);
            if (N != 1) {
                sticker[0][1] += sticker[1][0];
                sticker[1][1] += sticker[0][0];
                for (int i = 2; i < N; i++) {
                    sticker[0][i] += Math.max(sticker[1][i - 1], sticker[1][i - 2]);
                    sticker[1][i] += Math.max(sticker[0][i - 1], sticker[0][i - 2]);
                }
            }
            System.out.println(Math.max(sticker[0][N - 1], sticker[1][N - 1]));
        }

    }

    private static int[] getS(BufferedReader br) throws IOException {
        return Arrays.stream(
                        br.readLine()
                                .split(" ")
                )
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
