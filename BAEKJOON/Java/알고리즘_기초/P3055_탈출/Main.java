package 알고리즘_기초.P3055_탈출;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static int R;
    static int C;
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};
    static char[][] map;
    static int[][] dp;
    static Queue<Point> queue;


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/알고리즘_기초/P3055_탈출/input.txt"));
        Scanner scanner = new Scanner(System.in);

        R = scanner.nextInt();
        C = scanner.nextInt();

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();

        Point st = null;

        for (int r = 0; r < R; r++) {
            String line = scanner.next();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if(map[r][c] == '*'){
                    queue.add(new Point(r,c,'*'));
                } else if (map[r][c] == 'S') {
                    st = new Point(r,c,'S');
                }
            }
        }

        queue.add(st);

        boolean foundAnswer = false;

        // BFS 시작
        while (!queue.isEmpty()) {

            // 1. 큐에서 꺼내옴
            Point p = queue.poll();

            // 2. 목적지인가? -> 고슴도치만 D에 도착
            if (p.type == 'D') {
                System.out.println(dp[p.x][p.y]);
                foundAnswer = true;
                break;
            }

            // 3. 연결된 곳을 순회 -> 좌우위아래
            for (int i = 0; i < 4; i++) {
                int tx = MX[i] + p.x;
                int ty = MY[i] + p.y;

                // 4. 갈 수 있는가? (공통) -> 맵을 벗어나지 않고
                if (0 <= tx && tx < R && 0 <= ty && ty < C) {
                    // 4. 갈 수 있는가? (고슴도치) -> . , D, 방문체크
                    if (p.type == '.' || p.type == 'S') {
                        if ((map[tx][ty] == '.' || map[tx][ty] == 'D') && dp[tx][ty] == 0) {
                            // 5. 체크인(고슴도치) -> dp
                            dp[tx][ty] = dp[p.x][p.y] + 1;
                            // 6. 큐에 넣음
                            queue.add(new Point(tx, ty, map[tx][ty]));
                        }
                    } else if (p.type == '*') {
                        // 4. 갈 수 있는가? (물) -> . , S
                        if (map[tx][ty] == '.' || map[tx][ty] == 'S') {
                            // 5. 체크인 (물) -> map
                            map[tx][ty] = '*';
                            // 6. 큐에 넣음
                            queue.add(new Point(tx, ty, '*'));

                        }
                    }

                }

            }

            /*
            Point temp = queue.poll();

            // 물 일경우
            if(temp.getType() == '*'){
                for (int i = 0; i < 4; i++) {
                    int nx = MX[i] + temp.getX();
                    int ny = MY[i] + temp.getY();

                    // 범위를 벗어나는 경우 pass
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                    // .인 경우에만 물 번지기
                    if(map[nx][ny] == '.'){
                        map[nx][ny] = '*';
                    }

                }
            }

            // 고슴도치 일 경우
            else {
                for (int i = 0; i < 4; i++) {
                    int nx = MX[i] + temp.getX();
                    int ny = MY[i] + temp.getY();

                    // 범위를 벗어나는 경우 pass
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;

                    if

                }

            }
            */
        }

        if(foundAnswer == false)
            System.out.println("KAKTUS");

    }
}

class Point {
    int x;
    int y;
    char type;

    public Point(int x, int y, char type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", type=" + type +
                '}';
    }
}
