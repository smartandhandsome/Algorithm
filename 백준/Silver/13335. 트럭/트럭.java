import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 총 트럭개수: n
        int w = Integer.parseInt(st.nextToken()); // 다리길이: w
        int L = Integer.parseInt(st.nextToken());// 다리 최대 하중: L

        Queue<Integer> ts = Arrays.stream(
                        br.readLine().split(" ")
                )
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(LinkedList::new));// 트럭 무게

        Queue<Integer[]> b = new LinkedList<>();
        int bW = 0;
        int t = 0;
        while (!ts.isEmpty() || !b.isEmpty()) {
            if (!ts.isEmpty() && bW + ts.peek() <= L) {
                int truckW = ts.poll();
                bW += truckW;
                b.add(new Integer[]{truckW, 0});
            }

            b.forEach(integers -> integers[1]++);
            while (!b.isEmpty()) {
                if (b.peek()[1] < w) {
                    break;
                }
                bW -= b.poll()[0];
            }
            t++;
        }

        System.out.println(t + 1);
    }
}

