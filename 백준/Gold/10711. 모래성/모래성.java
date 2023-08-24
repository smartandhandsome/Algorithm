import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int h, w;
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Point> list = new ArrayList<>(); // 모래성 좌표
	static int[][] move = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static Queue<Point> q = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = br.readLine().split(" ");
		h = Integer.parseInt(temp[0]);
		w = Integer.parseInt(temp[1]);
		map = new char[h][w];
		visited = new boolean[h][w];
		for (int i = 0; i < h; i++) {
			String line = br.readLine();
			for (int j = 0; j < w; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				if (map[i][j] == '.' || map[i][j] == '9') {
					visited[i][j] = true;
					continue;
				}
				int num = map[i][j] - '0';
				if (check(new Point(i, j)) >= num) {
					q.add(new Point(i, j));
					visited[i][j] = true;
				}
			}
		}
		bfs();
	}

	private static void bfs() {
		// TODO Auto-generated method stub
		char[][] tempMap = new char[h][w];
		copy(tempMap);
		int time;
		for (time = 0; !q.isEmpty(); time++) {
			int q_size = q.size();
			for (int i = 0; i < q_size; i++) {
				Point p = q.poll(); // 무너뜨린 모래성
				map[p.x][p.y] = '.';

				for (int j = 0; j < move.length; j++) {
					int x = p.x + move[j][0];
					int y = p.y + move[j][1];
					int num = map[x][y] - '0';
					if (isIn(x, y) && tempMap[x][y] != '.' && !visited[x][y] && check(new Point(x, y)) >= num) {
						q.add(new Point(x, y));
						visited[x][y] = true;
					}
				}
			}
		}
		System.out.println(time);
	}

	private static int check(Point p) {
		// TODO Auto-generated method stub
		int cnt = 0;
		for (int j = 0; j < move.length; j++) {
			int x = p.x + move[j][0];
			int y = p.y + move[j][1];
			if (isIn(x, y) && map[x][y] == '.')
				cnt++;
		}
		return cnt;
	}

	private static boolean isIn(int x, int y) {
		// TODO Auto-generated method stub
		return x >= 0 && y >= 0 && x < h && y < w;
	}

	private static void copy(char[][] tempMap) {
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				tempMap[i][j] = map[i][j];
			}
		}
	}

	private static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}