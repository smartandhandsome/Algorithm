import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, ArrayList<Song>> map = new HashMap<>();
        int N = genres.length;
        for (int i = 0; i < N; i++) {
            ArrayList<Song> value = map.getOrDefault(genres[i], new ArrayList<>());
            value.add(new Song(i, plays[i]));
            map.put(genres[i], value);
        }
        Map<String, ArrayList<Song>> tmap = new TreeMap<>(new Comparator<String>() {
            public int compare(String a, String b) {
                ArrayList<Song> one = map.get(a);
                ArrayList<Song> two = map.get(b);
                int sOne = 0;
                int tOne = 0;
                for (Song s : one) {
                    sOne += s.p;
                }
                for (Song s : two) {
                    tOne += s.p;
                }
                return Integer.compare(tOne, sOne);
            }
        });
        tmap.putAll(map);
        ArrayList<Integer> answer = new ArrayList<>();
        for (ArrayList<Song> value : tmap.values()) {
            Collections.sort(value, Comparator.comparingInt(o -> -o.p));
            int first = value.get(0).t;
            answer.add(first);
            if (value.size() > 1) {
                int second = value.get(1).t;
                answer.add(second);
            }
        }
        int[] ans = new int[answer.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = answer.get(i);
        }
        
        return ans;
    }
}

class Song {
    int t;
    int p;

    Song(int t, int p) {
        this.t = t;
        this.p = p;
    }

    public String toString() {
        return "track number: " + t + " play count: " + p;
    }
}