import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> myCard = new HashMap<>();
        for (int i = 0; i < N; i++) {
            myCard.put(Integer.parseInt(st.nextToken()), 1);
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Integer[] nums = new Integer[M];
        for (int i = 0; i < M; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Integer[] answer = new Integer[M];
        for (int i = 0; i < M; i++) {
            answer[i] = myCard.get(nums[i]) == null ? 0 : 1;
        }

        for (int i = 0; i < M; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
