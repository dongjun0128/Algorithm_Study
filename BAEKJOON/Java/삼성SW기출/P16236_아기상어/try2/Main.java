package 삼성SW기출.P16236_아기상어.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static Shark babyShark;
    static int[][] map;
    static int N;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P16236_아기상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 아기상어는 같은 크기의 칸은 지나갈 수 있고, 먹지는 못한다.
        // 자기보다 작은 크기의 칸은 먹을 수 있다.

        // 먹을 수 있는 물고기가 공간에 없다면 엄마에게 도움을 요청
        // 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
        // 먹을 수 있는 물고기가 1마리 이상이라면, 거리가 가장 가까운 물고기를 먹으러 간다.
        // 거리가 같은 물고기가 많다면 가장 왼쪽 상위 물고기를 먹으러 간다.

        // 아기상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.

        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    map[i][j] = 0;
                    babyShark = new Shark(2, i, j, 0);
                }
            }
        }

        PriorityQueue<Node> priorityQueue;
        int answer = 0;

        while (true) {
            Node fish = findNearestFish();

            if (fish == null) break;

            answer += fish.distance;
            map[fish.x][fish.y] = 0;

            babyShark.x = fish.x;
            babyShark.y = fish.y;

            babyShark.eatCnt++;
            if (babyShark.eatCnt == babyShark.size) {
                babyShark.size++;
                babyShark.eatCnt = 0;
            }


        }

        System.out.println(answer);
    }

    static public Node findNearestFish() {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(0, babyShark.x, babyShark.y));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int x = node.x;
            int y = node.y;
            int distance = node.distance;

            if (map[x][y] != 0 && map[x][y] < babyShark.size) {
                return node;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny] && map[nx][ny] <= babyShark.size) {
                    visited[nx][ny] = true;
                    pq.add(new Node(distance + 1, nx, ny));
                }
            }
        }

        return null;
    }
}

class Shark {
    int size;
    int x;
    int y;
    int eatCnt;

    public Shark(int size, int x, int y, int eatCnt) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.eatCnt = eatCnt;
    }

    @Override
    public String toString() {
        return "Shark{" +
                "size=" + size +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

class Node implements Comparable<Node> {
    int distance;
    int x;
    int y;

    public Node(int distance, int x, int y) {
        this.distance = distance;
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Node o) {
        int cmp1 = this.distance - o.distance; // 거리에대해 오름차순

        if (cmp1 == 0) { // 거리가 같으면
            int cmp2 = this.x - o.x;// 행에대해 오름차순

            if (cmp2 == 0) {
                int cmp3 = this.y - o.y; // 행도 같으면 열에대해 오름차순
                return cmp3;
            }

            return cmp2;
        }

        return cmp1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "distance=" + distance +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}