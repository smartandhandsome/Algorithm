import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        AtomicInteger sum = new AtomicInteger();
        int N = Integer.parseInt(br.readLine());
        List<Integer> sumDistance = Arrays.stream(br.readLine().split(" "))
                .map(s -> sum.addAndGet(Integer.parseInt(s)))
                .collect(Collectors.toList());

        List<Integer> oilPricePerLiter = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        int arrived = 0;
        int pivot = arrived + 1;
        while (pivot < N - 1) {
            if (oilPricePerLiter.get(pivot) < oilPricePerLiter.get(arrived)) {
                answer += oilPricePerLiter.get(arrived) *
                        (sumDistance.get(pivot - 1) - (arrived == 0 ? 0 : sumDistance.get(arrived - 1)));
                arrived = pivot;
            }
            pivot++;
        }

        if (arrived != N - 1) {
            answer += oilPricePerLiter.get(arrived) *
                    (sumDistance.get(N - 2) - (arrived == 0 ? 0 : sumDistance.get(arrived - 1)));
        }

        System.out.println(answer);
    }
}
