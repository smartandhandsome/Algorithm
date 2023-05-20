import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, String> map;
    static HashMap<String, Integer> cnt; 
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            int F = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            cnt = new HashMap<>();
            for (int i = 0; i < F; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!map.containsKey(a)) {
                    map.put(a, a);
                    cnt.put(a, 1);
                }
                if (!map.containsKey(b)) {
                    map.put(b, b);
                    cnt.put(b, 1);
                }
                
                sb.append(union(a, b)).append("\n");
            }
        }
        System.out.print(sb);
    }
    public static String find(String a) {
        if (a == map.get(a)) {
            return a;
        }
        map.replace(a, find(map.get(a)));
        return map.get(a);
    }
    
    public static int union(String a, String b) {
        String retA = find(a);
        String retB = find(b);
        if (!retA.equals(retB)) {
            map.replace(retB, retA);
            cnt.replace(retA, cnt.get(retA) + cnt.get(retB));    
        }
        return cnt.get(retA);
    }
}