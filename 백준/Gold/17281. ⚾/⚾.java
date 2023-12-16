import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static List<int[]> lineups = new ArrayList<>();
    private static List<int[]> inning = new ArrayList<>();
    private static int answer = 0;

    public static void main(String args[]) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            inning.add(Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray());
        }

        int[] temp = new int[9];
        boolean[] isSelected = new boolean[9];
        setting(temp, isSelected, 0);

        for (int[] lineup : lineups) { // 8! = 40320
            int score = 0;
            int order = 0;


            for (int n = 0; n < N; n++) { // 이닝 수, 최대 50, 2,016,000
                boolean[] bases = new boolean[4];
                int outCount = 0;

                int[] results = inning.get(n);

                while (outCount < 3) { // 라인 업 9, 18,144,000
                    int result = results[lineup[order]];

                    if (result == 0) {
                        outCount++;
                    } else {
                        score += gogo(bases, result);
                    }

                    order = (order + 1) % 9;
                }
            }
            answer = Math.max(score, answer);
        }

        System.out.println(answer);
    }

    private static int gogo(boolean[] bases, int go) {
        int count = 0;

        for (int i = 3; i >= 1; i--) {
            if (bases[i]) {
                bases[i] = false;
                if (i + go > 3) {
                    count++;
                } else {
                    bases[i + go] = true;
                }
            }
        }

        if (go == 4) {
            count++;
        } else {
            bases[go] = true;
        }
        return count;
    }

    private static void setting(int[] lineup, boolean[] isSelected, int order) {
        if (order >= 9) {
            lineups.add(Arrays.copyOf(lineup, lineup.length));
            return;
        }


        for (int playerNumber = 1; playerNumber < 9; playerNumber++) {
            if (isSelected[playerNumber]) {
                continue;
            }

            lineup[order] = playerNumber;
            isSelected[playerNumber] = true;

            if (order == 2) { // 4번타자 뛰어 넘기
                order += 1;
                isSelected[playerNumber] = true;
            }

            setting(lineup, isSelected, order + 1);

            if (order == 3) { // 4번타자 뛰어 넘은거 복구
                isSelected[playerNumber] = false;
                order -= 1;
            }
            lineup[order] = 0;
            isSelected[playerNumber] = false;
        }
    }

    private static int solution(int[] lineup) {
        return 0;
    }
}
