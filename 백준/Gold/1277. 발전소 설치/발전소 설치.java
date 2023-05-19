import java.io.*;
import java.util.*;

public class Main {
    static double[][] graph;
    static HashMap<Integer, Integer[]> locations;
    static double INF = 200000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        double M = Double.parseDouble(br.readLine());
        locations = new HashMap<>();
        graph = new double[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            locations.put(i, new Integer[]{y, x});
            Arrays.fill(graph[i], INF);
            for (int j = 0; j < i; j++) {
                double d = getDistance(i, j);x
                if (d <= M){ 
                    graph[i][j] = graph[j][i] = d;
                }
            }
            graph[i][i] = 0;
        }
        
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            graph[a][b] = graph[b][a] = 0;
        }
        
        PriorityQueue<double[]> pq = new PriorityQueue<>(Comparator.comparingDouble(o -> o[1]));
        pq.offer(new double[]{0, 0});
        double[] dijkstra = new double[N];
        Arrays.fill(dijkstra, INF);
        while(!pq.isEmpty()) {
            double[] temp = pq.poll();
            int node = (int) temp[0];
            double dist = temp[1];
            if (dist > graph[0][node]) {
                continue;
            }
            for (int i = 0; i < N; i++) {
                if (dijkstra[i] > dist + graph[node][i]) {
                    dijkstra[i] = dist + graph[node][i]; 
                    pq.offer(new double[] {i, dijkstra[i]});
                }
            }
        }
        long answer = (long) (dijkstra[N-1] * 1000);;
        if (dijkstra[N-1] == INF) {
            answer = -1;
        }
        System.out.println(answer);
    }

    public static double getDistance(int a, int b) {
        Integer[] aLocation = locations.get(a);
        Integer[] bLocation = locations.get(b);
        return Math.sqrt(Math.pow(aLocation[0] - bLocation[0], 2) + Math.pow(aLocation[1] - bLocation[1], 2));
    }

    public static void printGraph() {
        int N = graph.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%10.1f", graph[i][j]);
            }
            System.out.println();
        }
    }
}
