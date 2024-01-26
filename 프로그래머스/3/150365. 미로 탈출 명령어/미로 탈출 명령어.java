import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('d', 0);
        map.put('l', 0);
        map.put('u', 0);
        map.put('r', 0);

        // 같은 격자를 두 번 이상 방문 가능
        // 탈출한 경로를 문자열이 사전 순으로 가장 빠른 경로로 탈출 해야 함
        if (r > x) {
            map.put('d', r - x);
        } else {
            map.put('u', x - r);
        }
        if (c > y) {
            map.put('r', c - y);
        } else {
            map.put('l', y - c);
        }
        k -= (Math.abs(c - y) + Math.abs(r - x));
        if (k < 0 || k % 2 == 1) {
            System.out.println(k);
            return "impossible";
        }
        int can = k / 2;

        if (n > r) {
            int a = Math.min(n - map.get('d') - x, can);
            map.put('d', map.get('d') + a);
            map.put('u', map.get('u') + a);
            can -= a;
        }
        if (c > 1) {
            int a = Math.min(y - 1 - map.get('l'), can);
            map.put('l', map.get('l') + a);
            map.put('r', map.get('r') + a);
            can -= a;
        }

        return "d".repeat(map.get('d')) +
                "l".repeat(map.get('l')) +
                "rl".repeat(can) +
                "r".repeat(map.get('r')) +
                "u".repeat(map.get('u'));
    }


}
