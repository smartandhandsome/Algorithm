class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String line = Integer.toBinaryString(arr1[i] | arr2[i]);
            StringBuilder replaced = new StringBuilder(line.replace('1', '#').replace('0', ' '));
            if (n > replaced.length()) {
                int cnt = n - replaced.length();
                while(cnt-- > 0) {
                    replaced.insert(0, " ");
                }
            }
            answer[i] = replaced.toString();
            System.out.println(replaced);
        }
        return answer;
    }
}