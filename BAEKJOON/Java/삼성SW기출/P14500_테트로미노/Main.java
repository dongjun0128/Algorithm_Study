package 삼성SW기출.P14500_테트로미노;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static ArrayList<ArrayList<Node>> figures;
    static boolean[][] visited;
    static int result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14500_테트로미노/input.txt"));
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

        visited = new boolean[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 1, map[i][j]);
                check(i,j);
            }
        }

        System.out.println(result);
    }

    static void dfs(int x, int y, int depth, int maxValue) {
        // 1. 체크인
        visited[x][y] = true;

        // 2. 목적지인가?
        if (depth == 4) {
            result = Math.max(result, maxValue);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 4. 갈 수 있는가?
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (visited[nx][ny] == false) {
                        dfs(nx, ny, depth + 1, maxValue + map[nx][ny]); // 5, 간다.
                    }
                }
            }
        }

        // 6. 체크아웃
        visited[x][y] = false;
    }

    static void check(int y, int x) {
        if (y < N - 2 && x < M - 1)
            result = Math.max(result, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x + 1]);

        if (y < N - 2 && x > 0)
            result = Math.max(result, map[y][x] + map[y + 1][x] + map[y + 2][x] + map[y + 1][x - 1]);

        if (y < N - 1 && x < M - 2)
            result = Math.max(result, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y + 1][x + 1]);

        if (y > 0 && x < M - 2)
            result = Math.max(result, map[y][x] + map[y][x + 1] + map[y][x + 2] + map[y - 1][x + 1]);
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