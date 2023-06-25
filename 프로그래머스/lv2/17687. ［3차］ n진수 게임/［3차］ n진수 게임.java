import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, String> map = new HashMap<>(
            Map.of(10, "A", 11, "B", 12, "C", 13, "D", 14, "E", 15, "F")
    );

    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int num = 0;
        int order = 0;
        int count = 0;

        while (count < t) {
            String s = toNBase(n, num);
            for (int i = 0; i < s.length(); i++) {
                if (order == p - 1) {
                    answer.append(s.charAt(i));
                    count++;
                    if (count >= t) {
                        break;
                    }
                }
                order = (order + 1) % m;
            }
            num++;
        }
        return answer.toString();
    }

    private String toNBase(int n, int num) {
        if (num == 0) {
            return "0";
        }
        StringBuilder temp = new StringBuilder();
        while (num > 0) {
            int a = num % n;
            if (a >= 10) {
                temp.append(map.get(a));
            } else {
                temp.append(a);
            }
            num /= n;
        }
        return temp.reverse().toString();
    }
}
