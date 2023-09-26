package Softeer.GarageGame;

import java.util.*;
import java.io.*;


public class Main
{
    static int N;
    static int answer = 0;
    static int[][] map;
    static int[][] subMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static PriorityQueue<Car> priorityQueue;

    public static void main(String args[]) throws IOException
    {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/Softeer/GarageGame/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 가로 세로 N칸의 차고가 있고, 각 차고에는 색깔이 있는 자동차가 하나씩 있다.
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        subMap = new int[2 * N][N];

        for (int i = 0; i < 2 * N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                subMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int gameCnt = 0;

        while (gameCnt++ < 3) {
            // 한 턴에 한 칸을 선택하며, 선택한 칸과 상하좌우 칸에 들어있는 자동차의 색이 같다면 모두 사라진다.
            // 사라진 칸들과 연결된 칸들의 상하좌우칸에 들어있는 자동차의 색이 같다면 함께 사라진다.
            visited = new boolean[N][N];
            priorityQueue = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (visited[i][j] == false) {
                        bfs(i, j);
                    }
                }
            }

            Car car = priorityQueue.poll();
            remove(car.x, car.y);
            answer += car.point;

            gravity();

        }


        // 이때, 획득할 수 있는 점수는 사라진 자동차의 개수와 사라지는 차고 칸을 모두 포함하는 가장 작은 직사각형의 넓이의 합이다.
        // 자동차들이 사라지고 나면 위에 있는 자동차들이 아래로 떨어져 빈 칸을 채운다.
        // 위쪽에는 충분한 자동차들이 더 있어서 축적으로 떨어지며 모든 칸을 채운다.

        System.out.println(answer);
    }

    static void gravity() {
        for (int i = N - 1; i >= 0 ; i--) {
            for (int j = N - 1; j >= 0 ; j--) {
                if (map[i][j] == 0) {
                    int subX = N + i;
                    int subY = j;

                    while(true) {
                        if (subMap[subX][subY] != 0) {
                            map[i][j] = subMap[subX][subY];
                            subMap[subX][subY] = 0;
                            break;
                        } else {
                            subX--;
                        }
                    }
                }
            }
        }

    }

    static void remove(int x, int y) {
        int carId = map[x][y];

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(x, y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == carId) {
                    queue.add(new Node(nx, ny));
                    map[nx][ny] = 0;
                }
            }
        }
    }

    static void bfs(int x, int y) {
        int carId = map[x][y];
        int minWidth = x;
        int maxWidth = 0;
        int minLength = y;
        int maxLength = 0;
        int carCnt = 0;

        Queue<Node> queue = new LinkedList<>();

        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            carCnt++;


            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == carId && visited[nx][ny] == false) {
                    queue.add(new Node(nx, ny));
                    visited[nx][ny] = true;
                    maxWidth = Math.max(maxWidth, nx);
                    maxLength = Math.max(maxLength, ny);
                }
            }
        }
        int area = (maxWidth - minWidth + 1) * (maxLength - minLength + 1);

        priorityQueue.add(new Car(x, y, carCnt, area));

    }


    static class Car implements Comparable<Car> {
        int x;
        int y;
        int cnt;
        int area;
        int point;

        public Car(int x, int y, int cnt, int area) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.area = area;
            this.point = cnt + area;
        }

        @Override
        public int compareTo(Car o) {
            return o.point - this.point; // point에 대해 내림차순
        }

        @Override
        public String toString() {
            return "Car{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cnt=" + cnt +
                    ", area=" + area +
                    ", point=" + point +
                    '}';
        }
    }


    static class Node {
        int x;
        int y;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}