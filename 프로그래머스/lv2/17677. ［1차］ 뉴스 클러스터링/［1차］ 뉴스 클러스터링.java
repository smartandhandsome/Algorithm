import java.util.*;
import java.util.regex.Pattern;

class Solution {
    static Pattern pattern = Pattern.compile("[A-Za-z]{2}");
    static Integer NUM = 65536;

    public int solution(String str1, String str2) {
        Map<String, Integer> separate1 = separate(str1.toUpperCase());
        Map<String, Integer> separate2 = separate(str2.toUpperCase());
        
        Set<String> key1 = separate1.keySet();
        Set<String> key2 = separate2.keySet();

        Set<String> union = new HashSet<>(key1);
        union.addAll(key2);

        Set<String> intersection = new HashSet<>(key1);
        intersection.retainAll(key2);

        int numerator = 0;
        for (String s : intersection) {
            numerator += Math.min(separate1.getOrDefault(s, 1), separate2.getOrDefault(s, 1));
        }

        int denominator = 0;
        for (String s : union) {
            denominator += Math.max(separate1.getOrDefault(s, 1), separate2.getOrDefault(s, 1));
        }
        
        if (denominator == 0) {
            return NUM;
        }
        return (int) (((double) numerator / denominator) * NUM);
    }

    private Map<String, Integer> separate(String s) {
        int length = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < length - 1; i++) {
            String sub = s.substring(i, i + 2);
            if (pattern.matcher(sub).matches()) {
                map.put(sub, map.getOrDefault(sub, 0) + 1);
            }
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().solution("FRANCE", "french"));
    }
}