import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] team;
    static boolean[] fixed = new boolean[11];
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            team = new int[11][11];
            answer = 0;
            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int[] score = new int[11];
                for (int j = 0; j < 11; j++) {
                    score[j] = Integer.parseInt(st.nextToken());
                }
                team[i] = score;
            }
            dfs(0, 0);
            System.out.println(answer);
        }
    }

    public static void dfs(int person, int ret) {
        if (person == 11) {
            answer = Math.max(answer, ret);
            return;
        }
        for (int i = 0; i < 11; i++) {
            if (!fixed[i] && team[person][i] != 0) {
                fixed[i] = true;
                dfs(person + 1, ret + team[person][i]);
                fixed[i] = false;
            }
        }
    }
}