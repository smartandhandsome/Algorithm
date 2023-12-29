import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static List<int[]> coors;
    static int N;
    static int M;
    static int L;
    static int K;

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        L = Integer.parseInt(s[2]);
        K = Integer.parseInt(s[3]);

        coors = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            int[] coor = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            coors.add(coor);
        }

        int answer = 0;
        for (int[] star1 : coors) {
            for (int[] star2 : coors) {
                answer = Math.max(answer, count(star1[0], star2[1]));
            }
        }
        System.out.println(K - answer);
    }

    public static int count(int y, int x) {
        int ret = 0;

        int[] stand = new int[]{y, x};
        for (int[] coor : coors) {
            if (isIn(stand, coor)) {
                ret++;
            }
        }
        return ret;
    }

    public static boolean isIn(int[] stand, int[] target) {
        return stand[0] <= target[0] && target[0] <= stand[0] + L && stand[1] <= target[1] && target[1] <= stand[1] + L;
    }

}
