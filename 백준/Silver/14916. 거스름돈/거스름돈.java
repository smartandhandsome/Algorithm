import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int change = Integer.parseInt(br.readLine());

        int max = change / 5;

        for (int i = max; i >= 0; i--) {
            for (int j = 0; ; j++) {
                if (i * 5 + j * 2 == change) {
                    System.out.println(i + j);
                    System.exit(0);
                } else if (i * 5 + j * 2 > change) {
                    break;
                }
            }
        }

        System.out.println(-1);
    }
}
