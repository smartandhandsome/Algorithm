import java.util.*;

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
