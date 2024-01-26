import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Solution {
    int maxi = 0;
    List<Integer> list = new ArrayList<>();

    public int[] solution(int[][] dice) {
        List<Integer> a = new ArrayList<>(List.of(0));
        List<Integer> b = new ArrayList<>();
        for (int i = 1; i < dice.length; i++) {
            b.add(i);
        }
        selectDice(dice, a, b, 1);
        return list.stream()
                .mapToInt(value -> value + 1)
                .sorted()
                .toArray();
    }

    void selectDice(int[][] dice, List<Integer> a, List<Integer> b, int index) {
        if (a.size() == dice.length / 2) {
            victory(dice, a, b);
            return;
        }

        for (int i = index; i < dice.length; i++) {
            a.add(i);
            b.remove((Integer) i);
            selectDice(dice, a, b, i + 1);
            a.remove((Integer) i);
            b.add(i);
        }
    }

    public void victory(int[][] dice, List<Integer> a, List<Integer> b) {
        Map<Integer, Integer> aScores = new TreeMap();
        roll(dice, a, 0, 0, aScores);

        Map<Integer, Integer> bScores = new TreeMap();
        roll(dice, b, 0, 0, bScores);

        int[] result = compare(aScores, bScores);
        System.out.println("result: " + result[0] + " " + result[1]);
        if (result[0] > maxi) {
            maxi = result[0];
            list = List.copyOf(a);
        }

        if (result[1] > maxi) {
            maxi = result[1];
            list = List.copyOf(b);
        }
    }

    public void roll(int[][] dice, List<Integer> selected, int index, int sum, Map<Integer, Integer> scores) {
        if (index == selected.size()) {
            scores.put(sum, scores.getOrDefault(sum, 0) + 1);
            return;
        }

        int[] sDice = dice[selected.get(index)];
        for (int diceNum : sDice) {
            roll(dice, selected, index + 1, sum + diceNum, scores);
        }
    }

    public int[] compare(Map<Integer, Integer> a, Map<Integer, Integer> b) {
        int win = 0;
        int lose = 0;
        for (Map.Entry<Integer, Integer> aEntry : a.entrySet()) {
            for (Map.Entry<Integer, Integer> bEntry : b.entrySet()) {
                if (aEntry.getKey() > bEntry.getKey()) {
                    win += aEntry.getValue() * bEntry.getValue();
                } else if (aEntry.getKey() < bEntry.getKey()) {
                    lose += aEntry.getValue() * bEntry.getValue();
                }
            }
        }
        return new int[]{win, lose};
    }
}
