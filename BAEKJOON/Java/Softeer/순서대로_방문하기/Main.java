package Softeer.순서대로_방문하기;

import java.util.*;
import java.io.*;


public class Main
{
    static int n, m;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int answer = 0;

    public static void main(String args[]) throws IOException
    {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/Softeer/순서대로_방문하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0 ; j < n ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        int x = 0;
        int y = 0;

        int num = 2;
        for(int a = 0 ; a < m ; a++) {
            st = new StringTokenizer(br.readLine());

            int q = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken()) - 1;

            if(a == 0) {
                x = q;
                y = w;
            }

            map[q][w] = num++;
        }

        dfs(x,y,cnt, map);

        System.out.println(answer);

    }

    static void dfs(int x, int y, int cnt, int[][] map) {

        // 1. 체크인
        if(map[x][y] == cnt + 2) {
            map[x][y] = -9;
            cnt++;
        } else {
            map[x][y] = -8;
        }

        // 2. 목적지인가?
        if(cnt == m) {
            answer++;
        } else {
            // 3. 연결된 곳 순회
            for(int i = 0 ; i < 4 ; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 4. 갈 수 있는가?
                if(nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if(map[nx][ny] == cnt + 2 || map[nx][ny] == 0) {
                        // 5. 간다.
                        int[][] tempMap = copyMap(map);
                        dfs(nx, ny, cnt, tempMap);
                    }
                }
            }
        }


    }



    static int[][] copyMap(int[][] map) {
        int[][] tempMap = new int[n][n];

        for(int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < n ; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        return tempMap;
    }
}
