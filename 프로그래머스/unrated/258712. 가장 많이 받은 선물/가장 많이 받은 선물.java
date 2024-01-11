import java.util.*;

class Solution {
    static Map<String, Friend> map = new HashMap<>();
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        for (String friend : friends) {
            map.put(friend, new Friend(friend, friends));
        }
        
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            
            String tempA = temp[0];
            Friend A = map.get(tempA);
                
            String tempB = temp[1];
            Friend B = map.get(tempB);
            A.give(tempB);
            B.take(tempA);
        }
        
        for (Friend f: map.values()) {
            f.calc();
        }
        for (Friend f: map.values()) {
            answer = Math.max(answer, f.nextMonth);
        }
        return answer;
    }
    
    static class Friend {
        Map<String, int[]> achieve;
        int giveCount;
        int takeCount;
        int nextMonth = 0;
        
        Friend(String myName, String[] names) {
            this.achieve = new HashMap<>();
            for (String name : names) {
                if (myName.equals(name)) {
                    continue;
                }
                achieve.put(name, new int[2]);
            }
            this.giveCount = 0;
            this.takeCount = 0;
        }
        
        void give(String name) {
            int[] temp = achieve.getOrDefault(name, new int[2]);
            temp[0]++;
            
            giveCount++;
        }
        
        void take(String name) {
            int[] temp = achieve.getOrDefault(name, new int[2]);
            temp[1]++;
            
            takeCount++;
        }
        
        void calc() {
            for (String name : achieve.keySet()) {
                int[] temp = achieve.get(name);
                
                int value = temp[0] - temp[1];
                if (value > 0) {
                    this.nextMonth++;
                } else if (value == 0) {
                    int myPoint = this.giveCount - this.takeCount;
                    
                    Friend f = map.get(name);
                    int another = f.giveCount - f.takeCount;
                    
                    if (myPoint > another) {
                        this.nextMonth++;
                    }
                }
            }
        }
    }
}