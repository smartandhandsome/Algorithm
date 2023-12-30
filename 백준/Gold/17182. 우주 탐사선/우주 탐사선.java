import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;
    static int K;

    static int[][] map;
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");

        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }


        dfs(new ArrayList<>(List.of(K)));
        System.out.println(answer);
    }

    public static void print() {
        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public static void dfs(List<Integer> list) {
        if (list.size() >= N) {
            answer = Math.min(gogo(list), answer);
//            System.out.println(list);
            return;

        }

        for (int i = 0; i < N; i++) {
            if (list.contains(i)) {
                continue;
            }
            list.add(i);
            dfs(list);
            list.remove((Integer) i);
        }
    }

    public static int gogo(List<Integer> list) {
        int dist = 0;
        for (int i = 0; i < N - 1; i++) {
            Integer i1 = list.get(i);
            Integer i2 = list.get(i + 1);
            dist += map[i1][i2];
        }
        return dist;
    }

}
