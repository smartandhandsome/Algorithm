import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    int[][] plants;
    Double[][] dist;

    void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        plants = new int[n][2];
        dist = new Double[n][n];
        double m = Double.parseDouble(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            plants[i][0] = Integer.parseInt(st.nextToken());
            plants[i][1] = Integer.parseInt(st.nextToken());
            for (int j = 0; j < i; j++) {
                double d = dSquare(plants[i], plants[j]);
                if (d <= m * m) dist[i][j] = dist[j][i] = Math.sqrt(d);
            }
        }
        while (w-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            dist[start][end] = dist[end][start] = 0.0;
        }
    }

    double dSquare(int[] p1, int[] p2) {
        double d = (double) (p1[0] - p2[0]) * (p1[0] - p2[0]);
        d += (double) (p1[1] - p2[1]) * (p1[1] - p2[1]);
        return d;
    }

    double Dijkstra() {
        double[] minDist = new double[plants.length];
        for (int i = 0; i < plants.length; i++) {
            minDist[i] = Double.MAX_VALUE;
        }
        class Plant implements Comparable<Plant> {
            final int plantNo;
            final double dist;

            Plant(int plantNo, double dist) {
                this.plantNo = plantNo;
                this.dist = dist;
            }

            @Override
            public int compareTo(Plant p) {
                return Double.compare(this.dist, p.dist);
            }
        }
        PriorityQueue<Plant> pq = new PriorityQueue<>();
        pq.add(new Plant(0, 0));
        while (!pq.isEmpty()) {
            Plant curr = pq.poll();
            int currPlantNo = curr.plantNo;
            double currDist = curr.dist;
            if (minDist[currPlantNo] < currDist) continue;
            for (int i = 0; i < plants.length; i++) {
                if (dist[currPlantNo][i] == null) continue;
                if (currDist + dist[currPlantNo][i] < minDist[i]) {
                    minDist[i] = currDist + dist[currPlantNo][i];
                    pq.add(new Plant(i, minDist[i]));
                }
            }
        }
        return minDist[plants.length - 1];
    }

    void solution() throws IOException {
        input();
        System.out.println((int) (Dijkstra() * 1000));
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}