import java.util.*;

class Solution {
    public int solution(String s) {
        if (s.length()==1) {
            return 1;
        }
        
        int answer = Integer.MAX_VALUE;
        
        int n = s.length();
        
        for (int i = 1; i <= n / 2; i++) {
            int idx = i;
            String standard = s.substring(0, idx);
            StringBuilder sb = new StringBuilder();
            
            int cnt = 1;
            while(n >= idx + i) {
                String temp = s.substring(idx, idx + i);
                // System.out.println(temp);
                if (!temp.equals(standard)) {
                    if (cnt > 1) {
                        sb.append(cnt);
                    }
                    sb.append(standard);
                    standard = temp;
                    cnt = 0;
                }
                idx += i;
                cnt++;
            }
            if (cnt > 1) {
                sb.append(cnt);
            }
            sb.append(standard);
            
            if (idx < n) {
                sb.append(s.substring(idx, n));
            }
            
            // System.out.println(sb.toString());
            // System.out.println();
            
            answer = Math.min(sb.length(), answer);
        }
        
        return answer;
    }
}