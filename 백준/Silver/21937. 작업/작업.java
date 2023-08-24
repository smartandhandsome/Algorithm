import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        // 위상 정렬
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] info = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] finished = new boolean[info[0] + 1];
        HashMap<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < info[1]; i++) {
            String[] s = br.readLine().split(" ");
            int f = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[1]);
            List<Integer> orDefault = map.getOrDefault(e, new ArrayList<>());
            orDefault.add(f);
            map.put(e, orDefault);
        }

        int target = Integer.parseInt(br.readLine());

        int answer = 0;
        Queue<Integer> q = new LinkedList<>(map.getOrDefault(target, Collections.emptyList()));
        while (!q.isEmpty()) {
            int poll = q.poll();
            if (finished[poll]) {
                continue;
            }
            finished[poll] = true;
            answer++;

            List<Integer> integers = map.getOrDefault(poll, Collections.emptyList());
            q.addAll(integers);
        }

        System.out.println(answer);
    }
}
