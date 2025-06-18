import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int n = clothes.length;
        
        Map<String, Set<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            Set<String> set = map.computeIfAbsent(clothes[i][1], k -> new HashSet<>());
            set.add(clothes[i][0]);
        }
        
        int answer = 1;
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            int size = entry.getValue().size() + 1;
            answer *= size;
        }
        answer--; // 아무것도 안 입은 경우
        
        return answer;
    }
}