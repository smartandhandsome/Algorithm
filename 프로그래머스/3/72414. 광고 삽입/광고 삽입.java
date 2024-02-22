import java.util.*;


class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convert(play_time);
        int adTime = convert(adv_time);
        
        // 체킹
        long[] times = new long[playTime + 1];
        for (int i = 0; i < logs.length; i++) {
            String[] log = logs[i].split("-");
            
            int start = convert(log[0]);
            int end = convert(log[1]);
            times[start]++;
            times[end]--;
        }
        
        // 시청자 수
        for (int i = 1; i <= playTime; i++) {
            times[i] += times[i - 1]; 
        }
        
        // 재생시간 
        for (int i = 1; i <= playTime; i++) {
            times[i] += times[i - 1]; 
        }
        
        // 슬라이딩 윈도우 초기 기준
        int startTime = 0;
        long MAX = times[adTime - 1];

        // 슬라이딩 윈도우
        for(int i = 0; i + adTime <= playTime; i++) {
            long sum = times[i + adTime] - times[i];
            if (sum > MAX) {
                MAX = sum;
                startTime = i + 1;
            }
        }
        
        return convert(startTime);
    }
    
    static String convert(int e) {
        String h = e/60/60 + "";
        if (h.length() == 1) {
            h = "0" + h;
        }
        String m = e/60%60 + "";            
        if (m.length() == 1) {
            m = "0" + m;
        }
        String s = e%60%60 + "";
        if (s.length() == 1) {
            s = "0" + s;
        }
        return h + ":" + m + ":" + s;
    }
    
    static int convert(String s) {
        String[] splited = s.split(":");
        
        int ret = 0;
        ret += Integer.parseInt(splited[0]) * 60 * 60;
        ret += Integer.parseInt(splited[1]) * 60;
        ret += Integer.parseInt(splited[2]);
        
        return ret;
    }
    
    static void print(Object o) {
        System.out.println(o);
    }
}