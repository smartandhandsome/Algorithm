import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static long[][] ground;
    static long[][] answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ground = new long[N][N];
        answer = new long[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ground[i][j] = -Long.parseLong(st.nextToken());
            }
        }
        int r = M / 2;
        for (int i = r; i < N - r; i++) {
            for (int j = r; j < N - r; j++) {
                answer[i][j] = ground[i - r][j - r];  // A
                if (i - r - 1 >= 0) {  // A 위로 한 칸
                    answer[i][j] -= ground[i - r - 1][j - r];
                }
                if (j - r - 1 >= 0) { // A 왼쪽으로 한 칸
                    answer[i][j] -= ground[i - r][j - r - 1];
                }
                if (i - r - 1 >= 0 && j - r - 1 >= 0) {  // 왼쪽 + 위로 한 칸씩
                    answer[i][j] += ground[i - r - 1][j - r - 1];
                }
                if (i - M >= 0) {
                    answer[i][j] += answer[i - M][j];
                }
                if (j - M >= 0) {
                    answer[i][j] += answer[i][j - M];
                }
                if (i - M >= 0 && j - M >= 0) {
                    answer[i][j] -= answer[i - M][j - M];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}