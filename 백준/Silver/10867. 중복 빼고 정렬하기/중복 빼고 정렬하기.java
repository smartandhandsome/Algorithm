import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<Integer> hs = new HashSet<>();
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            hs.add(Integer.valueOf(st.nextToken()));
        }
        Integer[] nums = hs.toArray(new Integer[0]);
        Arrays.sort(nums);
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            System.out.print(nums[i] + " ");
        }
    }
}
