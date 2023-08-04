import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        long atk = Long.parseLong(s[1]);

        long[][] room = new long[N][3];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            room[i][0] = Long.parseLong(line[0]);
            room[i][1] = Long.parseLong(line[1]);
            room[i][2] = Long.parseLong(line[2]);
        }

        long a = 0;
        long answer = 0;
        for (int i = 0; i < N; i++) {
            if (room[i][0] == 1) {
                a -= ((room[i][2]) / atk + (room[i][2] % atk == 0 ? -1 : 0)) * room[i][1];
                answer = Math.min(answer, a);
            } else {
                atk += room[i][1];
                a += room[i][2];
                if (a > 0) {
                    a = 0;
                }
            }
        }
        System.out.println(-answer+1);
    }
}

