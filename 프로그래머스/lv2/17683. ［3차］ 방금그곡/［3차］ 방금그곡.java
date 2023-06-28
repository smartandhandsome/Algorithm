import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        PriorityQueue<Candidate> pq = new PriorityQueue<>(
                (Candidate a, Candidate b) ->
                        Integer.compare(b.songLength, a.songLength) == 0 ?
                                Integer.compare(a.order, b.order) : Integer.compare(b.songLength, a.songLength));
        for (int i = 0; i < musicinfos.length; i++) {
            String[] split = musicinfos[i].split(",");
            String convertedM = convert(m);
            int time = calculateTime(split[0], split[1]);

            StringBuilder sb = new StringBuilder();
            String song = convert(split[3]);

            int songLength = 0;
            int index = 0;
            while (songLength < time) {
                sb.append(song.charAt(index));
                songLength++;
                index = (index + 1) % song.length();
            }
            song = sb.toString();
            if (song.contains(convertedM)) {
                pq.offer(new Candidate(songLength, split[2], i));
            }
        }
        if (!pq.isEmpty()) {
            answer = pq.poll().songName;
        }
        return answer;
    }

    private String convert(String a) {
        return a.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "A");
    }

    private int calculateTime(String a, String b) {
        String[] aSplit = a.split(":");
        int aTime = Integer.parseInt(aSplit[0]) * 60 + Integer.parseInt(aSplit[1]);
        String[] bSplit = b.split(":");
        int bTime = Integer.parseInt(bSplit[0]) * 60 + Integer.parseInt(bSplit[1]);
        return bTime - aTime;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().solution("ABC", new String[]{"12:00,12:06,HELLO,ABC#ABC#ABC"}));  // "(None)"
        System.out.println(new Solution().solution("ABC", new String[]{"12:00,12:10,HELLO,ABC#ABC#ABC"})); // "HELLO"
        System.out.println(new Solution().solution("ABC", new String[]{"12:04,13:00,HELLO,ABC#ABC#ABC"})); // "HELLO"
        System.out.println(new Solution().solution("C#C", new String[]{"12:00,12:06,HELLO,C#C#CC#"})); // "HELLO"
        System.out.println(new Solution().solution("CCB", new String[]{"03:00,03:10,FOO,CCB#CCB", "04:00,04:08,BAR,ABC"})); // "HELLO"

    }

    class Candidate {
        int songLength;
        String songName;
        int order;

        public Candidate(int songLength, String songName, int order) {
            this.songLength = songLength;
            this.songName = songName;
            this.order = order;
        }
    }
}
