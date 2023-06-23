import java.util.stream.Collectors;
import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        Queue<Integer> queue = Arrays.stream(timetable)
                .sorted()
                .map(str -> (Integer.parseInt(str.substring(0, 2)) * 60) + Integer.parseInt(str.substring(3, 5)))
                .collect(Collectors.toCollection(LinkedList::new));

        int busTime = 9 * 60;
        int last = 0;
        while (n-- > 0) {
            for (int i = 0; i < m; i++) {
                if (queue.isEmpty()) {
                    last = busTime;
                    break;
                }
                Integer peek = queue.peek();
                if (peek > busTime) {
                    last = busTime;
                    break;
                }
                last = queue.poll() - 1;
            }
            busTime += t;
        }
        String hour = String.format("%02d", last / 60);
        String min = String.format("%02d", last % 60);
        return hour + ":" + min;
    }
}