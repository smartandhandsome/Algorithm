import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        TreeMap<String, Integer> answer = new TreeMap<>();
        HashMap<String, Integer> IN = new HashMap<>();
        
        for (int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            String number = st.nextToken();
            String io = st.nextToken();
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(3, 5));
            int total = hour * 60 + min;
            if (io.equals("IN")) {    
                IN.put(number, total);
            } else {
                int inTime = IN.get(number);
                IN.remove(number);
                int parkingTime = total - inTime;
                answer.put(number, answer.getOrDefault(number, 0) + parkingTime);
            }
        }
        
        int midnight = 23 * 60 + 59;
        for (Map.Entry<String, Integer> en : IN.entrySet()) {
            String number = en.getKey();
            int parkingTime = midnight - en.getValue();
            answer.put(number, answer.getOrDefault(number, 0) + parkingTime);
        }
        int i = 0;
        int[] ret = new int[answer.size()];
        for (Map.Entry<String, Integer> en : answer.entrySet()) {
            String number = en.getKey();
            int totalTime = en.getValue();
            int overTime = ((double)(totalTime - fees[0]) / fees[2]) > 0 ? (int) Math.ceil((double) (totalTime - fees[0]) / fees[2]) : 0;
            ret[i++] = fees[1] + overTime * fees[3];
        }
        
        
        return ret;
    }
}