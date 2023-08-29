import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        int[] times = Arrays.stream(s).mapToInt(t -> {
            String[] temp = t.split(":");
            return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
        }).toArray();

        Set<String> set = new HashSet<>();
        Set<String> answer = new HashSet<>();
        try {
            while (true) {
                String[] line = br.readLine().split(" ");
                String[] temp = line[0].split(":");
                int time = Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
                if (time <= times[0]) {
                    set.add(line[1]);
                    continue;
                }
                if (times[1] <= time && time <= times[2] && set.contains(line[1])) {
                    answer.add(line[1]);
                }
            }
        } catch (Exception ignored) {
        }
        System.out.println(answer.size());
    }

}
