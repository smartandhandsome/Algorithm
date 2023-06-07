import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    static List<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        dfs(0, 0);
        Collections.sort(list);
        System.out.println(N > list.size() ? -1 : list.get(N - 1));
    }

    private static void dfs(long num, int index) {
        if (!list.contains(num)) {
            list.add(num);
        }
        if (index < 10) {
            dfs(num * 10 + nums[index], index + 1);
            dfs(num, index + 1);
        }
    }
}