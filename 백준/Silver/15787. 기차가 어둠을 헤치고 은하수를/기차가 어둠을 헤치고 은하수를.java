import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] seat = new int[N];
        for (int num = 0; num < M; num++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken()) - 1;
            if (command == 1) {
                int x = Integer.parseInt(st.nextToken()) - 1;
                seat[i] |= (1 << x);
            } else if (command == 2) {
                int x = Integer.parseInt(st.nextToken()) - 1;
                seat[i] &= ~(1 << x);
            } else if (command == 3) {
                seat[i] = (seat[i] << 1) % (1 << 20);
            } else if (command == 4) {
                seat[i] = (seat[i] >> 1);
            }
        }
        List<Integer> seats = Arrays.stream(seat).boxed().collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(seats);
        System.out.println(set.size());
    }

}
