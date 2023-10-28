import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        House[] h = new House[N];
        long total = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h[i] = new House(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            total += h[i].people;
        }

        Arrays.sort(h);
        long answer = 0;
        long count = 0;
        for (int i = 0; i < N; i++) {
            count += h[i].people;
            if ((total + 1) / 2 <= count) {
                answer = h[i].pos;
                break;
            }
        }

        System.out.println(answer);
    }

    static class House implements Comparable<House> {
        int pos;
        int people;

        public House(int pos, int a) {
            this.pos = pos;
            people = a;
        }

        @Override
        public int compareTo(House o) {
            return Integer.compare(this.pos, o.pos);
        }
    }
}
