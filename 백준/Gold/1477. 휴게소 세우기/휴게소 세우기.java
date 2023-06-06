import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        // 0 ~ length 까지 휴게소가 없는 구간의 길이의 최댓값이 최소로
        List<Integer> rest = new ArrayList<>();
        rest.add(0);
        rest.add(length);
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rest.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(rest);
        int left = 1, right = length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;  // 거리가 mid 보다 크거나 같으면 휴게소 지어줘야됨
            int cnt = 0;
            for (int i = 0; i < N + 1; i++) {
                int gap = rest.get(i + 1) - rest.get(i) - 1;
                cnt += gap / mid;
            }
            if (cnt > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }
}
