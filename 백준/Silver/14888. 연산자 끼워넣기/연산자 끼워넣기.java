import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int MAX = -2147483638;
    static int MIN = 2147483637;
    static int N;
    static int[] nums;
    static int[] arithmetic = new int[4];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            arithmetic[i] = Integer.parseInt(st.nextToken());
        }

        getMINMAX(nums[0], 1);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void getMINMAX(int num, int idx) {
        if (idx == N) {
            MAX = Math.max(num, MAX);
            MIN = Math.min(num, MIN);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (arithmetic[i] == 0) {
                continue;
            }
            arithmetic[i]--;
            switch (i) {
                case 0:
                    getMINMAX(num + nums[idx], idx + 1);
                    break;
                case 1:
                    getMINMAX(num - nums[idx], idx + 1);
                    break;
                case 2:
                    getMINMAX(num * nums[idx], idx + 1);
                    break;
                case 3:
                    getMINMAX(num / nums[idx], idx + 1);
                    break;
            }
            arithmetic[i]++;
        }
    }
}