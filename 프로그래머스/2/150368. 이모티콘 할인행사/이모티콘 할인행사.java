import java.util.*;

class Solution {
    static int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        // user 최대 100명
        // emoticons 최대 7개
        
        // 할인율 10, 20, 30, 40 고정
        // 이모티콘 플러스 서비스 가입자를 최대한 늘리는 것
        // 이모티콘 판매액을 최대한으로 늘리는 것
        
        // 10이면  6300, 8100
        // 20이면  5600, 7200
        // 30이면  4900, 6300
        // 40이면  4200, 5400
        
        // emoticon 모든 경우의수 4 * 4 * 4 * 4 = 256
        // 유저 * 이모티콘 100 * 7 = 700

        cases(users, emoticons, new int[emoticons.length], new int[]{10, 20, 30, 40}, 0);
        
        return answer;
    }
    
    public void cases(int[][] users, int[] emoticons, int[] discount, int[] percent, int depth) {
        int size = emoticons.length;
        if (depth == size) {
            int[] r = calc(users, emoticons, discount);
            if (r[0] > answer[0]) {
                answer = r;
            } else if (r[0] == answer[0] && r[1] >= answer[1]) {
                answer = r;
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            discount[depth] = percent[i];
            cases(users, emoticons, discount, percent, depth + 1);
        }
    }
    
    public int[] calc(int[][] users, int[] emoticons, int[] discount) {
        int[] ret = new int[2];
        // System.out.println("emo: " + Arrays.toString(emoticons));
        // System.out.println("dis: " + Arrays.toString(discount));
        
        int size = users.length;
        for (int i = 0; i < size; i++) {
            int[] user = users[i];
            
            int th = user[0];
            int price = user[1];
            
            // System.out.println("users: " + Arrays.toString(users[i]));
            
            double p = 0;
            int eSize = emoticons.length;
            for (int e = 0; e < eSize; e++) {
                if (th > discount[e]) {
                    continue;
                }
                
                p += (double) ((emoticons[e] * (100 - discount[e])) / 100);
            }
            if (p >= price) {
                ret[0]++;
            } else {
                ret[1] += (int) p;
            }
        }
        // System.out.println("ret: " + Arrays.toString(ret));
        // System.out.println();
        return ret;
    }
    
}