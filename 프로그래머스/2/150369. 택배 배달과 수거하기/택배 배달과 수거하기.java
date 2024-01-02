class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;

        int maxD = n - 1;
        int maxP = n - 1;
        int c = 0;
        while (true) {
            while(maxD >= 0 && deliveries[maxD] == 0) maxD--;
            while(maxP >= 0 && pickups[maxP] == 0) maxP--;
            if (maxD == -1 && maxP == -1) {
                break;
            }
            
            int d = 0;
            for (int i = maxD; i >= 0; i--) {
                d += deliveries[i];
                deliveries[i] = 0;
                if (d >= cap) {
                    deliveries[i] = d - cap;
                    break;
                }
            }
            
            int p = 0;
            for (int i = maxP; i >= 0; i--) {
                p += pickups[i];
                pickups[i] = 0;
                if (p >= cap) {
                    pickups[i] = p - cap;
                    break;
                }
            }
            answer += (Math.max(maxD, maxP) + 1) * 2;
        }
        return answer;
    }
}