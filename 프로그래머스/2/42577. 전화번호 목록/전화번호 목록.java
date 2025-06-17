import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        int n = phone_book.length;
        
        Arrays.sort(phone_book, Comparator.comparing(s -> -s.length()));
        Set<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String number = phone_book[i];
            if (set.contains(number)) {
                return false;
            }
            
            String s = "";
            for (int j = 0; j < number.length(); j++) {
                s += number.charAt(j);
                set.add(s);
            }
        }
        
        return true;
    }
}