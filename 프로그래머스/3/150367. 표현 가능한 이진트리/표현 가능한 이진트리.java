import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String bi = convertBinary(numbers[i]);
            
            // System.out.println(bi);
            
            answer[i] = isBinaryTree(bi, 0, bi.length() - 1) == true ? 1 : 0;
        }
        return answer;
    }
    
    public String convertBinary(long number) {
        StringBuilder sb = new StringBuilder();
        
        long tmp = number;
        while (tmp > 0) {
            sb.append(tmp % 2);
            tmp /= 2;
        }
        
        int n = 1;
        while (n - 1 < sb.length()) {
            n*=2;
        }
        
        int need = (n - 1) - sb.length();
        while (need-- > 0) {
            sb.append("0");
        }
        
        return String.valueOf(sb.reverse());
    }
    
    public boolean isBinaryTree(String number, int start, int end) {
        if (start == end) {
            return true;
        }
        
        int mid = (start + end) / 2;
        if (number.charAt(mid) != '1') {
            for (int i = start; i <= end; i++) {
                if (number.charAt(i) != '0'){
                    return false;
                }
            }
            return true;
        }
        
        return isBinaryTree(number, start, mid - 1) && isBinaryTree(number, mid + 1, end);
    }
}