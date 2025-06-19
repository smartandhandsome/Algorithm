import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 장르 별로 가장 많이 재생된 노래 최대 2개
        Map<String, Integer> ge = new HashMap<>();
        Map<String, Queue<Integer>> so = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCount = plays[i];
            ge.compute(genre, (k, v) -> v == null ? playCount : v + playCount);
            int finalI = i;
            so.compute(genre, (k, v) -> {
                if (v == null) {
                    v = new PriorityQueue<>(Comparator.comparingInt(j -> -plays[j]));
                }
                v.offer(finalI);
                return v;
            });
        }

        List<Integer> ans = new ArrayList<>();
        ge.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(entry -> -entry.getValue()))
                .forEach(entry -> {
                    Queue<Integer> q = so.get(entry.getKey());
                    ans.add(q.poll());
                    if (!q.isEmpty()) {
                        ans.add(q.poll());
                    }
                });
        return ans.stream().mapToInt(value -> value).toArray();
    }
}
