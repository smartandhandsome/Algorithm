class Solution {
    private int N;
    
    public String solution(String p) {
        StringBuilder sb = new StringBuilder();
        N = p.length();
        if (N == 0) {
            return "";
        }
        
        return seperate(p, 0);
    }
    
    public String seperate(String s, int start) {
        if (start >= N) {
            return "";
        }
        int index = start;
        int count = 0;
        
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        do {
            char c = s.charAt(index);
            sb.append(c);
            count += convert(c);
            
            if (count < 0) {
                flag = true;
            }
            index++;
        } while(index < N && count != 0);
        
        
        String u = sb.toString();
        if (flag) {
            return "(" + seperate(s, index) + ")" + flip(u.substring(1, u.length() - 1));
        }
        
        return u + seperate(s, index);
    }
    
    public String flip(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                sb.append(')');
            } else if (c == ')') {
                sb.append('(');
            }
        }
        return sb.toString();
    }
    
    public int convert(char c) {
        if (c == '(') {
            return 1;
        } 
        
        if (c == ')') {
            return -1;
        }
        
        return 0;
    }
}