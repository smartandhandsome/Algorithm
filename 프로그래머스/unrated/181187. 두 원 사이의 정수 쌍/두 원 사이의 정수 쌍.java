import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long shortC = (long) Math.pow(r1, 2);
        long longC = (long) Math.pow(r2, 2);
        
        for (int x = 1; x <= r2; x++) {  
            int MIN = (int) Math.ceil(Math.sqrt(shortC - Math.pow(x, 2)));
            int MAX = (int) Math.floor(Math.sqrt(longC - Math.pow(x, 2)));
            answer += (MAX - MIN + 1);
        }
        return answer * 4;
    }
}