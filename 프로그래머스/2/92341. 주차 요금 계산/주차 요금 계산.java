import java.util.*;

class Solution {
    static int LAST = 23 * 60 + 59;
    
    public int[] solution(int[] fees, String[] records) {        
        Map<String, Integer> in = new HashMap<>();
        Map<String, Integer> day = new TreeMap<>();
        
        for (String record : records) {
            String[] tmp = record.split(" ");
            String[] time = tmp[0].split(":");
            
            int min = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
            
            if ("IN".equals(tmp[2])) {
                in.put(tmp[1], min);
            } else {
                int t = min - in.get(tmp[1]);
                in.remove(tmp[1]);
                day.put(tmp[1], day.getOrDefault(tmp[1], 0) + t);
            }
        }
        
        for (Map.Entry<String, Integer> e : in.entrySet()) {
            String key = e.getKey();
            int value = e.getValue();
            day.put(key, day.getOrDefault(key, 0) + LAST - value);
        }
        System.out.println(day);
        int[] answer = new int[day.size()];
        int i = 0;
        for (Map.Entry<String, Integer> e : day.entrySet()) {
            String key = e.getKey();
            int value = e.getValue();    
            
            int money = fees[1];
            int extra = (int) Math.ceil((value - fees[0]) / (double) fees[2]) * fees[3];
            money += extra <= 0 ? 0 : extra;
            answer[i++] = money;
        }
        
        
        return answer;
    }
}