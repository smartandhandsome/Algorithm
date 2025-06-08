import java.util.*;

class Solution {

    int n;

    List<Integer> list = new ArrayList<>();
    int maxWin = -1;
    int[][] dice;

    public int[] solution(int[][] dice) {
        n = dice.length;
        this.dice = dice;

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            b.add(i);
        }

        dfs(a, b, 0);

        return list.stream().sorted().mapToInt(value -> value + 1).toArray();
    }

    void dfs(List<Integer> a, List<Integer> b, int index) {
        if (a.size() == n / 2) {
            
            int[] stat = getStat(a, b);
            if (stat[0] > maxWin) {
                maxWin = stat[0];
                list = List.copyOf(a);
            }
            if (stat[1] > maxWin) {
                maxWin = stat[1];
                list = List.copyOf(b);
            }
            return;
        }

        for(int i = index; i < n; i++) {
            a.add(i);
            b.remove((Integer) i);
            
            System.out.println("index = " + index);
            System.out.println("a: " + a + ", b:" + b);
            
            dfs(a, b, i + 1);
            b.add(i);
            a.remove((Integer) i);
        }
    }

    int[] getStat(List<Integer> a, List<Integer> b) {
        Map<Integer, Integer> aScores = new HashMap<>();
        Map<Integer, Integer> bScores = new HashMap<>();
        buildScore(0, a, 0, aScores);
        buildScore(0, b, 0, bScores);

        int win = 0;
        int loss = 0;
        for (Map.Entry<Integer, Integer> aEntry : aScores.entrySet()) {
            for (Map.Entry<Integer, Integer> bEntry : bScores.entrySet()) {
                if (aEntry.getKey() > bEntry.getKey()) {
                    win += aEntry.getValue() * bEntry.getValue();
                } else if (aEntry.getKey() < bEntry.getKey()) {
                    loss += aEntry.getValue() * bEntry.getValue();
                }
            }
        }

        return new int[] {win, loss};
    }

    void buildScore(int sum, List<Integer> selected,  int index, Map<Integer, Integer> map) {
        if (index == selected.size()) {
            map.compute(sum, (k, v) -> v == null ? 1 : v + 1);
            return;
        }
        int number = selected.get(index);
        for (int d : dice[number]) {
            buildScore(sum + d, selected, index + 1, map);
        }
    }

}
