import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(br.readLine()
                        .split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int answer = 280;
        if (12 <= array[0] && array[0] <= 16 && array[1] == 0) {
            answer = 320;
        }
        System.out.println(answer);
    }
}
