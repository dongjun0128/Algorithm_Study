package 삼성SW기출.P14502_연구소;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static int[][] map;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14502_연구소/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    dfs(i, j, 1);
                }
            }
        }

        System.out.println(result);
    }

    static void dfs(int x, int y, int wallNum) {
        // 1. 체크인
        map[x][y] = 1;

        // 2. 목적지인가?
        if (wallNum == 3) {
            int[][] tempMap = new int[N][M];
            Queue<Node> queue = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tempMap[i][j] = map[i][j];
                    if(map[i][j] == 2) queue.add(new Node(i,j));
                }
            }

            // bfs
            while (!queue.isEmpty()) {
                Node node = queue.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = node.x + dx[i];
                    int ny = node.y + dy[i];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                        if (tempMap[nx][ny] == 0) {
                            tempMap[nx][ny] = 2;
                            queue.add(new Node(nx, ny));
                        }
                    }
                }
            }

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if(tempMap[i][j] == 0) cnt++;
                }
            }

            result = Math.max(cnt,result);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    // 4. 갈 수 있는가?
                    if (map[i][j] == 0) {
                        // 5. 간다.
                        dfs(i, j, wallNum + 1);
                    }
                }
            }
        }

        // 6. 체크아웃
        map[x][y] = 0;
    }
}

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
