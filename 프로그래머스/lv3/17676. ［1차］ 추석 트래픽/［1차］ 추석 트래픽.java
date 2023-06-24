import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[] lines) {
        List<int[]> inAndOut = Arrays.stream(lines)
                .map(s -> {
                    String dateTime = s.substring(0, 23);
                    String x = s.substring(24);
                    return xSecondsAgo(dateTime, x);
                })
                .sorted((o1, o2) -> o1[1] == o2[1] ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]))
                .collect(Collectors.toList());

        int answer = 0;
        for (int i = 0; i < inAndOut.size(); i++) {
            int end = inAndOut.get(i)[1];
            int cnt = 1;
            for (int j = i + 1; j < inAndOut.size(); j++) {
                if (end + 1000 > inAndOut.get(j)[0]) {
                    cnt++;
                }
            }
            answer = Math.max(answer, cnt);
        }
        return answer;
    }

    private int[] xSecondsAgo(String dateTime, String x) {
        String[] splitDateAndTime = dateTime.split(" ");
        String time = splitDateAndTime[1];

        String[] splitTime = time.split(":");
        int hour = Integer.parseInt(splitTime[0]) * 60 * 60 * 1000;
        int min = Integer.parseInt(splitTime[1]) * 60 * 1000;
        int second = (int) (Double.parseDouble(splitTime[2]) * 1000);
        int endTime = hour + min + second;

        int et = (int) (Double.parseDouble(x.substring(0, x.length() - 1)) * 1000);
        int startTime = endTime - et + 1;
        if (startTime < 0) {
            startTime = 0;
        }
        return new int[]{startTime, endTime};
    }
}