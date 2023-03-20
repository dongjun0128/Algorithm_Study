package 삼성SW기출.P14503_로봇_청소기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int r, c, d;
    // d 0 - 북 1 - 동 2 - 남 3 - 서
    static int[][] map;
    static int time;
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P14503_로봇_청소기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        time = 0;

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        loop1:
        while (true) {
            if(map[r][c] == 0) {
                map[r][c] = -1;
                time++;
            }

            for (int i = 0; i < 4; i++) {
                int nd = (d + 3 - i) % 4;
                int nr = r + dx[nd];
                int nc = c + dy[nd];

                if (map[nr][nc] == 0) {
                    d = nd;
                    r = nr;
                    c = nc;
                    continue loop1;
                }
            }

            int back = (d + 2) % 4 ;
            int nr = r + dx[back];
            int nc = c + dy[back];

            if(map[nr][nc] == 1){
                break;
            }

            r = nr;
            c = nc;
        }

        System.out.println(time);
    }

    static int direction(int dx, int dy) {
        if (dx == -1 && dy == 0) return 0;
        else if (dx == 0 && dy == 1) {
            return 1;
        } else if (dx == 1 && dy == 0) {
            return 2;
        } else {
            return 3;
        }
    }
}
