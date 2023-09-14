package 깊이우선탐색_넓이우선탐색.P2667_단지번호붙이기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int num;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P2667_단지번호붙이기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String input = st.nextToken();

            for (int j = 0; j < N; j++) {
                map[i][j] = input.charAt(j) - '0';
            }
        }

        int cnt = 0;
        List<Integer> cntList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    cnt++;
                    num = 0;
                    dfs(i, j);
                    cntList.add(num);
                }
            }
        }

        Collections.sort(cntList);
        System.out.println(cnt);
        for (int a : cntList) {
            System.out.println(a);
        }
    }

    public static void dfs(int x, int y) {
        if (x >= 0 && x < N && y >= 0 && y < N && map[x][y] == 1) {
            num++;
            map[x][y] = 0;

            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);
        }
    }
}
