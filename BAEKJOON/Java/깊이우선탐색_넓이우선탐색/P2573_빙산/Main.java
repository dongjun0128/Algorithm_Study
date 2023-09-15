package 깊이우선탐색_넓이우선탐색.P2573_빙산;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P2573_빙산/input.txt"));
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

        int answer = 0;

        while (true) {
            answer++;
            int[][] tempMap = mapClone();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0) {
                        int cnt = 0;

                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempMap[nx][ny] == 0) {
                                cnt++;
                            }
                        }

                        map[i][j] -= cnt;

                        if(map[i][j] <= 0) map[i][j] = 0;
                    }
                }
            }

            if (count() >= 2) {
                break;
            }

            if (count() == 0) {
                answer = 0;
                break;
            }
        }


        System.out.println(answer);


    }

    public static int[][] mapClone() {
        int[][] tempMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        return tempMap;
    }

    public static int count() {
        int cnt = 0;

        int[][] tempMap = mapClone();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tempMap[i][j] != 0) {
                    cnt++;
                    dfs(tempMap, i, j);
                }
            }
        }

        return cnt;
    }

    public static void dfs(int[][] tempMap, int x ,int y) {
        tempMap[x][y] = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempMap[nx][ny] != 0) {
                dfs(tempMap, nx, ny);
            }
        }
    }
}
