import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        
        String s = convert(n, k);
        // System.out.println(s);
        
        String[] strs = s.split("0");
        // System.out.println(Arrays.toString(strs));
        
        for (String str : strs) {
            if (str.isBlank()) {
                continue;
            }

            if (isPrime(str)) {
                answer++;
            }
        }
        return answer;
    }
    
    public String convert(int n, int k) {
        StringBuilder sb = new StringBuilder();
        
        int temp = n;
        while(temp > 0) {
            sb.append(temp % k);
            temp /= k;
        }
        
        return String.valueOf(sb.reverse());
    }
    
    public boolean isPrime(String n) {
        long p = Long.parseLong(n);
        if (p == 1) {
            return false;
        }
        
        for (int i = 2; i <= Math.sqrt(p); i++) {
            if (p % i == 0) {
                return false;
            }
        }
        return true;
    }
}