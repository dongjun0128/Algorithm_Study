package 삼성SW기출.P21610_마법사_상어와_비바리기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] map;
    static int dx[] = {0, -1, -1, -1, 0, 1, 1, 1};
    static int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static ArrayList<ArrayList<Integer>> clouds;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21610_마법사_상어와_비바리기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clouds = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int direction = Integer.parseInt(st.nextToken()) - 1;
            int times = Integer.parseInt(st.nextToken());

            // 비바라기 시전
            if (i == 0) {
                visited = new boolean[N][N];
                makeClouds();
            }

            // 구름 이동
            for (int j = 0; j < times; j++) {
                for (int k = 0; k < clouds.size(); k++) {
                    int x = clouds.get(k).get(0);
                    int y = clouds.get(k).get(1);

                    x += dx[direction];

                    if (x < 0) {
                        x += N;
                    } else if (x >= N) {
                        x -= N;
                    }

                    clouds.get(k).set(0, x);

                    y += dy[direction];

                    if (y < 0) {
                        y += N;
                    } else if (y >= N) {
                        y -= N;
                    }

                    clouds.get(k).set(1, y);
                }
            }

            // 구름이 있는 곳에 물 내리기
            for (int j = 0; j < clouds.size(); j++) {
                int x = clouds.get(j).get(0);
                int y = clouds.get(j).get(1);

                map[x][y]++;
                visited[x][y] = true;
            }

            // 물복사 마법
            for (int j = 0; j < clouds.size(); j++) {
                int x = clouds.get(j).get(0);
                int y = clouds.get(j).get(1);

                for (int k = 1; k < 8; k += 2) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        if (map[nx][ny] != 0) {
                            map[x][y]++;
                        }
                    }
                }
            }

            ArrayList<ArrayList<Integer>> newClouds = new ArrayList<>();

            // 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k] >= 2) {
                        if (visited[j][k] == false) {
                            map[j][k] -= 2;
                            ArrayList<Integer> cloud = new ArrayList<>();
                            cloud.add(j);
                            cloud.add(k);
                            newClouds.add(cloud);
                        }
                    }
                }
            }

            for (int j = 0; j < clouds.size(); j++) {
                visited[clouds.get(j).get(0)][clouds.get(j).get(1)] = false;
            }
            clouds = newClouds;
        }

        int result = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += map[i][j];
            }
        }

        System.out.println(result);

    }

    static void makeClouds() {
        ArrayList<Integer> cloud1 = new ArrayList<>();
        ArrayList<Integer> cloud2 = new ArrayList<>();
        ArrayList<Integer> cloud3 = new ArrayList<>();
        ArrayList<Integer> cloud4 = new ArrayList<>();

        cloud1.add(N - 2);
        cloud1.add(0);

        cloud2.add(N - 2);
        cloud2.add(1);

        cloud3.add(N - 1);
        cloud3.add(0);

        cloud4.add(N - 1);
        cloud4.add(1);

        clouds.add(cloud1);
        clouds.add(cloud2);
        clouds.add(cloud3);
        clouds.add(cloud4);

    }
}
