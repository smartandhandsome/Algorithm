import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static boolean[] check;
    static int[] nums;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        int sum = 0;
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            sum += nums[i];
        }
        check = new boolean[sum + 2];

        for (int i = 0; i < N; i++) {
            solution(i+1, nums[i]);
        }
        int answer = 0;
        for (int i = 1; i <= sum + 1; i++) {
            if(!check[i]){
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    static void solution(int index, int num) {
        check[num] = true;
        for (int i = index; i < N; i++) {
            solution(i + 1, num + nums[i]);
        }
    }
}
