package 삼성SW기출.P20057_마법사_상어와_토네이도;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int result = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P20057_마법사_상어와_토네이도/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 토네이도
        int x = N / 2;
        int y = N / 2;
        int direction = 0;
        int length = 1;

        Loop1:
        while (true) {
            for (int i = 0; i < length; i++) {
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    break Loop1;
                }

                if (map[nx][ny] != 0)
                    spread(nx, ny, direction);

                x = nx;
                y = ny;
            }
            direction = (direction + 1) % 4;
            if (direction % 2 == 0) length++;
        }

        System.out.println(result);
    }

    static void spread(int x, int y, int direction) {
        int send = map[x][y];
        int alpha = send;
        int alphaIndexX = x + dx[direction];
        int alphaIndexY = y + dy[direction];

        map[x][y] = 0;

        // 첫 칸

        int nx = x + dx[(direction + 3) % 4];
        int ny = y + dy[(direction + 3) % 4];

        int spreadSend = (int) (send * 0.07);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;


        nx = x + dx[(direction + 3) % 4] * 2;
        ny = y + dy[(direction + 3) % 4] * 2;
        spreadSend = (int) (send * 0.02);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;


        nx = x + dx[(direction + 1) % 4];
        ny = y + dy[(direction + 1) % 4];
        spreadSend = (int) (send * 0.07);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        nx = x + dx[(direction + 1) % 4] * 2;
        ny = y + dy[(direction + 1) % 4] * 2;
        spreadSend = (int) (send * 0.02);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        // 뒤쪽

        x = x + dx[(direction + 2) % 4];
        y = y + dy[(direction + 2) % 4];

        nx = x + dx[(direction + 1) % 4];
        ny = y + dy[(direction + 1) % 4];
        spreadSend = (int) (send * 0.01);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        nx = x + dx[(direction + 3) % 4];
        ny = y + dy[(direction + 3) % 4];
        spreadSend = (int) (send * 0.01);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        //앞 2 칸

        x = alphaIndexX;
        y = alphaIndexY;

        nx = x + dx[(direction + 1) % 4];
        ny = y + dy[(direction + 1) % 4];
        spreadSend = (int) (send * 0.1);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        nx = x + dx[(direction + 3) % 4];
        ny = y + dy[(direction + 3) % 4];
        spreadSend = (int) (send * 0.1);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;

        nx = x + dx[(direction) % 4];
        ny = y + dy[(direction) % 4];
        spreadSend = (int) (send * 0.05);

        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            result += spreadSend;
        } else{
            map[nx][ny] += spreadSend;
        }
        alpha -= spreadSend;


        // 알파 자리 삽입
        if (alphaIndexX < 0 || alphaIndexX >= N || alphaIndexY < 0 || alphaIndexY >= N) {
            result += alpha;
        } else{
            map[alphaIndexX][alphaIndexY] += alpha;
        }
    }

}
