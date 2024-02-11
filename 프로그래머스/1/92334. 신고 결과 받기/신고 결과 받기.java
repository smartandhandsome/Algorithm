import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = {};
        
        Map<String, Set<String>> res = new HashMap<>();
        Map<String, Integer> cnt = new LinkedHashMap<>();
        for (String id : id_list) {
            res.put(id, new HashSet<>());
            cnt.put(id, 0);
        }
        
        for (String r : report) {
            String[] tmp = r.split(" ");
            Set<String> set = res.get(tmp[1]);
            set.add(tmp[0]);
        }
        
        for (Set<String> reporters : res.values()) {
            if (reporters.size() >= k) {
                for (String r : reporters) {
                    cnt.put(r, cnt.get(r) + 1);
                }
            }
        }
        return cnt.values()
            .stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}