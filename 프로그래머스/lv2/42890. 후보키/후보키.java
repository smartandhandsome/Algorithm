import java.util.*;

class Solution {
    List<int[]> combi = new ArrayList<>();
    List<Integer> candi = new ArrayList<>();


    public int solution(String[][] relation) {
        int answer = 0;
        for (int i = 0; i < relation[0].length; i++) {
            combination(new int[i + 1], relation[0].length, 0, -1);
        }
        // for (int[] com : combi) {
        //     System.out.println(Arrays.toString(com));
        // }

        for (int[] com : combi) {
            Integer bit = convertBit(com);
            if (candi.stream()
                    .anyMatch(x -> (x & bit) == x)) {
                continue;
            }

            Map<String, Boolean> map = new HashMap<>();
            boolean flag = false;
            for (int i = 0; i < relation.length; i++) {
                StringBuilder sb2 = new StringBuilder();
                for (int j : com) {
                    sb2.append(relation[i][j]).append(" ");
                }
                if (map.containsKey(sb2.toString())) {
                    flag = true;
                    break;
                }
                map.put(sb2.toString(), true);
            }
            if (!flag) {
                candi.add(bit);
                answer++;
            }

        }
        return answer;
    }

    private Integer convertBit(int[] com) {
        Integer bit = 0;
        for (int i : com) {
            bit = bit | 1 << i;
        }
        return bit;
    }


    private void combination(int[] ret, int n, int depth, int index) {
        if (depth == ret.length) {
            combi.add(Arrays.copyOf(ret, ret.length));
            return;
        }
        if (depth >= n) {
            return;
        }
        for (int i = index + 1; i < n; i++) {
            ret[depth] = i;
            combination(ret, n, depth + 1, i);
            ret[depth] = 0;
        }
    }
}

