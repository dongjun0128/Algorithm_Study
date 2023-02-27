package 구현.P16924_십자가_찾기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] tempMap;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/구현/P16924_십자가_찾기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        tempMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }

        ArrayList<Cross> crosses = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '*') {

                    int len = 1;

                    while (true) {

                        int nx1 = i + dx[0] * len;
                        int nx2 = i + dx[1] * len;
                        int nx3 = i + dx[2] * len;
                        int nx4 = i + dx[3] * len;

                        int ny1 = j + dy[0] * len;
                        int ny2 = j + dy[1] * len;
                        int ny3 = j + dy[2] * len;
                        int ny4 = j + dy[3] * len;

                        if (nx1 < 0 || nx1 >= N || ny1 < 0 || ny1 >= M) break;
                        if (nx2 < 0 || nx2 >= N || ny2 < 0 || ny2 >= M) break;
                        if (nx3 < 0 || nx3 >= N || ny3 < 0 || ny3 >= M) break;
                        if (nx4 < 0 || nx4 >= N || ny4 < 0 || ny4 >= M) break;

                        if (map[nx1][ny1] != '*') break;
                        if (map[nx2][ny2] != '*') break;
                        if (map[nx3][ny3] != '*') break;
                        if (map[nx4][ny4] != '*') break;

                        crosses.add(new Cross(i + 1, j + 1, len));

                        len++;
                    }

                }
            }
        }

        for (Cross cross : crosses) {
            int x = cross.x - 1;
            int y = cross.y - 1;
            int s = cross.s;

            map[x][y] = '.';

            for (int len = 1; len <= s; len++) {
                int nx1 = x + dx[0] * len;
                int nx2 = x + dx[1] * len;
                int nx3 = x + dx[2] * len;
                int nx4 = x + dx[3] * len;

                int ny1 = y + dy[0] * len;
                int ny2 = y + dy[1] * len;
                int ny3 = y + dy[2] * len;
                int ny4 = y + dy[3] * len;

                map[nx1][ny1] = '.';
                map[nx2][ny2] = '.';
                map[nx3][ny3] = '.';
                map[nx4][ny4] = '.';
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if(map[i][j] == '*') {
                    System.out.println("-1");
                    return;
                }
            }
        }

        System.out.println(crosses.size());
        for (int i = 0; i < crosses.size(); i++) {
            System.out.println(crosses.get(i).x + " " + crosses.get(i).y + " " + crosses.get(i).s);
        }
    }
}

class Cross {
    int x;
    int y;
    int s;

    public Cross(int x, int y, int s) {
        this.x = x;
        this.y = y;
        this.s = s;
    }

    @Override
    public String toString() {
        return "Cross{" +
                "x=" + x +
                ", y=" + y +
                ", s=" + s +
                '}';
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