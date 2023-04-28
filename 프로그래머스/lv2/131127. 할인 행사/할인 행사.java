import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        
        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < 10; j++) {
                map.put(discount[i+j], map.getOrDefault(discount[i+j], 0) + 1);
            }
            boolean flag = false;
            // System.out.println(map);
            for (int j = 0; j < want.length; j++) {
                if (map.getOrDefault(want[j], 0) < number[j]) {
                    // System.out.println(want[j] + " " + map.getOrDefault(want[j], 0) + " " + number[j]);
                    flag = true;
                    continue;
                }
            }
            if (!flag) {
                answer++;
            }
        }
        
        return answer;
    }
}