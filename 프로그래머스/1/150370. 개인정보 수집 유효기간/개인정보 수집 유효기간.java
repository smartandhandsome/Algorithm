import java.util.*;

class Solution {
    // 모든 달은 28일까지 있다
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> a = new ArrayList<>();

        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] temp = term.split(" ");
            map.put(temp[0], Integer.parseInt(temp[1]));
        }

        String[] nows = today.split("\\.");
        int ny = Integer.parseInt(nows[0]);
        int nm = Integer.parseInt(nows[1]);
        int nd = Integer.parseInt(nows[2]);

        for (int i = 0; i < privacies.length; i++) {
            String[] temp = privacies[i].split(" ");

            String[] standard = temp[0].split("\\.");
            int y = Integer.parseInt(standard[0]);
            int m = Integer.parseInt(standard[1]);
            int d = Integer.parseInt(standard[2]);
            
            int t = map.get(temp[1]);

            int q = (m + t);
            m = q % 12;
            if (m == 0) {
                m = 12;
            }

            y += q / 12;
            if (q % 12 == 0) {
                y--;
            }
            
            d--;
            if (d == 0) {
                m--;
                d+=28;
            }
            System.out.println("i: "+ i + " time: " + y + " " + m + " " + d);
            if (ny > y || (ny == y && nm > m) || (ny == y && nm == m && nd > d)) {
                a.add(i+1);
            }
        }
        
        return a.stream().mapToInt(Integer::intValue).toArray();
    }
}