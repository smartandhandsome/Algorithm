import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int n = progresses.length;
        
    	int time = -1;
        int index = 0;
        int count = 0;
        while(index < n) {
            int remain = 100 - progresses[index];
            int newTime = (remain / speeds[index]) + (remain % speeds[index] == 0 ? 0 : 1);
            System.out.println(newTime);
            
            if (time == -1) {
                time = newTime;
            }
            
            if (time < newTime) {
                answer.add(count);
                
                time = newTime;
                count = 1;
                index++;
                continue;
            }
            
            count++;
            index++;
        }
        
        if (count > 0) {
            answer.add(count);
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}