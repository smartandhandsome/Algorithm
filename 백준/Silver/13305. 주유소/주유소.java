import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        long answer = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        AtomicLong sum = new AtomicLong();
        List<Long> sumDistance = Arrays.stream(br.readLine().split(" "))
                .map(s -> sum.addAndGet(Long.parseLong(s)))
                .collect(Collectors.toList());

        List<Long> oilPricePerLiter = Arrays.stream(br.readLine().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());


        int arrived = 0;
        int pivot = arrived + 1;
        while (pivot < n - 1) {
            if (oilPricePerLiter.get(pivot) < oilPricePerLiter.get(arrived)) {
                answer += oilPricePerLiter.get(arrived) *
                        (sumDistance.get(pivot - 1) - (arrived == 0 ? 0 : sumDistance.get(arrived - 1)));
                arrived = pivot;
            }
            pivot++;
        }

        if (arrived != n - 1) {
            answer += oilPricePerLiter.get(arrived) *
                    (sumDistance.get(n - 2) - (arrived == 0 ? 0 : sumDistance.get(arrived - 1)));
        }

        System.out.println(answer);
    }
}
