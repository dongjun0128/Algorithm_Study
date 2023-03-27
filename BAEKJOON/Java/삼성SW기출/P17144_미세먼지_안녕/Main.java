package 삼성SW기출.P17144_미세먼지_안녕;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int[][] tempMap;
    static int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P17144_미세먼지_안녕/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        ArrayList<Cleaner> cleanerIndex = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) {
                    cleanerIndex.add(new Cleaner(i, j));
                }
            }
        }

        while (T-- > 0) {
            tempMap = new int[R][C];

            for (int i = 0; i < cleanerIndex.size(); i++) {
                tempMap[cleanerIndex.get(i).x][cleanerIndex.get(i).y] = -1;
            }

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (map[i][j] > 0) {
                        move(i, j);
                    }
                }
            }

            map = tempMap;

            cleanUp(cleanerIndex.get(0).x, cleanerIndex.get(0).y);
            cleanDown(cleanerIndex.get(1).x, cleanerIndex.get(1).y);
        }

        int result = 0;

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(map[i][j] != 0 && map[i][j] != -1) result += map[i][j];
            }
        }

        System.out.println(result);
    }

    static void move(int x, int y) {
        int cnt = 0;
        int diff = map[x][y] / 5;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < R && ny >= 0 && ny < C && map[nx][ny] != -1) {
                cnt++;
                tempMap[nx][ny] += diff;
            }
        }

        tempMap[x][y] += map[x][y] - diff * cnt;
    }

    static void cleanUp(int x, int y) {
        int temp1 = 0;
        int temp2 = 0;
        int nx = x;
        int ny = y + 1;

        while (true) {
            nx += 0;
            ny += 1;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx][ny - 1];
                map[nx][ny-1] = temp1;
                temp1 = temp2;
                ny--;
                break;
            }

            temp2 = map[nx][ny - 1];
            map[nx][ny-1] = temp1;
            temp1 = temp2;

        }

        nx -= 1;
        ny += 0;

        while (true) {
            nx -= 1;
            ny += 0;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx + 1][ny];
                map[nx + 1][ny] = temp1;
                temp1 = temp2;
                nx++;
                break;
            }

            temp2 = map[nx + 1][ny];
            map[nx + 1][ny] = temp1;
            temp1 = temp2;
        }

        nx += 0;
        ny -= 1;

        while (true) {
            nx += 0;
            ny -= 1;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx][ny + 1];
                map[nx][ny + 1] = temp1;
                temp1 = temp2;
                ny++;
                break;
            }

            temp2 = map[nx][ny + 1];
            map[nx][ny + 1] = temp1;
            temp1 = temp2;

        }

        nx += 1;
        ny += 0;

        while (true) {
            nx += 1;
            ny += 0;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || tempMap[nx][ny] == -1) {
                temp2 = map[nx - 1][ny];
                map[nx - 1][ny] = temp1;
                temp1 = temp2;
                break;
            }

            temp2 = map[nx - 1][ny];
            map[nx - 1][ny] = temp1;
            temp1 = temp2;
        }

    }

    static void cleanDown(int x, int y) {
        int temp1 = 0;
        int temp2 = 0;
        int nx = x;
        int ny = y + 1;

        while (true) {
            nx += 0;
            ny += 1;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx][ny - 1];
                map[nx][ny-1] = temp1;
                temp1 = temp2;
                ny--;
                break;
            }

            temp2 = map[nx][ny - 1];
            map[nx][ny-1] = temp1;
            temp1 = temp2;

        }

        nx += 1;
        ny += 0;

        while (true) {
            nx += 1;
            ny += 0;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx - 1][ny];
                map[nx - 1][ny] = temp1;
                temp1 = temp2;
                nx--;
                break;
            }

            temp2 = map[nx - 1][ny];
            map[nx - 1][ny] = temp1;
            temp1 = temp2;
        }

        nx += 0;
        ny -= 1;

        while (true) {
            nx += 0;
            ny -= 1;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C) {
                temp2 = map[nx][ny + 1];
                map[nx][ny + 1] = temp1;
                temp1 = temp2;
                ny++;
                break;
            }

            temp2 = map[nx][ny + 1];
            map[nx][ny + 1] = temp1;
            temp1 = temp2;

        }

        nx -= 1;
        ny += 0;

        while (true) {
            nx -= 1;
            ny += 0;

            if (nx < 0 || nx >= R || ny < 0 || ny >= C || tempMap[nx][ny] == -1) {
                temp2 = map[nx + 1][ny];
                map[nx + 1][ny] = temp1;
                temp1 = temp2;
                break;
            }

            temp2 = map[nx + 1][ny];
            map[nx + 1][ny] = temp1;
            temp1 = temp2;
        }
    }
}

class Cleaner {
    int x;
    int y;

    public Cleaner(int x, int y) {
        this.x = x;
        this.y = y;
    }
}