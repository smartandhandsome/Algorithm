import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int K;

    public static void main(String[] args) throws IOException {
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        Belt belt = new Belt(br.readLine());

        int answer = 0;
        do {

            belt.move();
            belt.moveRobot();
            belt.addRobot();

            answer++;
        } while ((belt.checkLife()));
        System.out.println(answer);
    }

    static class Belt {
        private Deque<Integer> lives = new ArrayDeque<>();
        private Deque<Integer> robots = new ArrayDeque<>();

        public Belt(String line) {
            String[] s = line.split(" ");
            for (String string : s) {
                lives.add(Integer.parseInt(string));
            }
        }

        public boolean checkLife() {
            long count = lives.stream().filter(integer -> integer <= 0).count();
            return count < K;
        }

        public void move() {
            lives.addFirst(lives.pollLast());

            robots = robots.stream().map(integer -> integer + 1).collect(Collectors.toCollection(ArrayDeque::new));
            if (!robots.isEmpty() && (robots.peekLast() == N - 1)) {
                robots.pollLast();
            }
        }

        public void addRobot() {
            Integer i = lives.pollFirst();
            if (i != 0) {
                robots.addFirst(0);
                i--;
            }
            lives.addFirst(i);
        }

        public void moveRobot() {
            ArrayList<Integer> l = new ArrayList<>(lives);
            Deque<Integer> r = new ArrayDeque<>();

            Iterator<Integer> iterator = robots.descendingIterator();
            while (iterator.hasNext()) {
                Integer now = iterator.next();
                int next = (now + 1) % (N * 2);
                if (l.get(next) < 1 || r.contains(next)) { // 다음 블록 체력이 0 일때 그냥 멈춤
                    r.addFirst(now);
                    continue;
                }
                l.set(next, l.get(next) - 1);
                if (next != N - 1) {
                    r.addFirst(now + 1);
                }

            }
            lives = new ArrayDeque<>(l);
            robots = r;
        }

    }

}

