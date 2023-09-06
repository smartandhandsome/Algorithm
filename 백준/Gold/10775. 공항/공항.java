import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[] disjoint;

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(br.readLine());
        disjoint = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            disjoint[i] = i;
        }

        int P = Integer.parseInt(br.readLine());
        int[] plane = new int[P];
        for (int i = 0; i < P; i++) {
            plane[i] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        for (int i = 0; i < P; i++) {
            int target = find(plane[i]);
            if (target == 0) {
                break;
            }
            disjoint[target] = disjoint[target - 1];
            answer++;
        }

        System.out.println(answer);
    }

    public static int find(int n) {
        if (disjoint[n] == n) {
            return n;
        }
        disjoint[n] = find(disjoint[n]);
        return disjoint[n];
    }

}
