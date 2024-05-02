import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int leng = -1;

        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine()
                    .split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            map.put(a, map.getOrDefault(a, 0) + 1);
            map.put(b, map.getOrDefault(b, 0) - 1);
        }

        int answer = 0;
        int temp = 0;
        for (int key : map.keySet()) {
            temp += map.get(key);
            answer = Math.max(answer, temp);
        }
        System.out.println(answer);
    }
}
