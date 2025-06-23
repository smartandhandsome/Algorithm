import java.util.*;

class Solution {
    boolean solution(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.add(c);
            } else if (c == ')') {
                if (st.isEmpty()) {
                    return false;
                }
                st.pop();
            }
            
        }
        if (st.isEmpty()) {
            return true;
        }
        return false;
    }
}