import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int change = Integer.parseInt(br.readLine());
        int answer = -1;

        if (change < 5) {
            if (change % 2 == 0) {
                answer = change / 2;
            }
        } else {
            if (change % 5 == 0) {
                answer = change / 5;
            } else {
                int div = change / 5;
                int rem = change % 5;
                if (rem % 2 == 0) {
                    answer = div + rem / 2;
                } else {
                    div--;
                    rem += 5;
                    if (rem % 2 == 0) {
                        answer = div + rem / 2;
                    }

                }
            }
        }
        System.out.println(answer);
    }
}
