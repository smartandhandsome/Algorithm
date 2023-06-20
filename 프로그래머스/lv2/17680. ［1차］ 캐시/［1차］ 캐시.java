import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }
        int answer = 0;
        Map<String, Boolean> map = new HashMap<>();
        LinkedList<String> list = new LinkedList<>();
        for (String city : cities) {
            city = city.toLowerCase();
            if (!map.getOrDefault(city, false)) {
                if (list.size() >= cacheSize) {
                    map.replace(list.poll(), false);
                }
                map.put(city, true);
                list.add(city);
                answer += 5;
            } else {
                list.remove(city);
                list.add(city);
                answer += 1;
            }
        }
        return answer;
    }
}