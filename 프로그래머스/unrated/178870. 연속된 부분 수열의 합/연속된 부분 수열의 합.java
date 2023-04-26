class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {0, Integer.MAX_VALUE};
        int left = 0, right = 0;
        int SUM = sequence[left];
        int N = sequence.length;
        while (true) {
            if (SUM < k) {
                right++;
                if (right >= N) {
                    break;   
                }
                SUM += sequence[right];
            } else {
                if (k == SUM && answer[1] - answer[0] > right - left) {
                    answer = new int[]{left, right};
                }
                SUM -= sequence[left];
                left++;
            }
        }
        return answer;
    }
}