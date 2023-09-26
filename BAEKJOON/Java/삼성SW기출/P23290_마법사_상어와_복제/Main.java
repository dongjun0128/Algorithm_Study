package 삼성SW기출.P23290_마법사_상어와_복제;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    static int M, S;
    static ArrayList<Fish>[][] map;
    static ArrayList<Fish>[][] tempMap;
    static Fish shark;
    static int[][] smells = new int[4][4];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P23290_마법사_상어와_복제/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 물고기 M 마리, 마법사 상어
        // 둘 이상의 물고기가 같은 칸에 있을 수도 있으며, 마법사 상어와 물고기가 같은 칸에 있을 수도 있다.

        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        map = new ArrayList[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) - 1;

            map[x][y].add(new Fish(x, y, d));
        }

        st = new StringTokenizer(br.readLine());
        shark = new Fish(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, -1);

        int cntS = 0;

        while (cntS++ < S) {
            // 1. 상어가 모든 물고기에게 복제 마법을 시전한다.
            // 복제 마법은 시간이 조금 걸리기 때문에, 아래 5번에서 물고기가 복제되어 칸에 나타난다.

            // 2. 모든 물고기가 한 칸 이동한다.
            fishMove();

            // 3. 상어가 연속해서 3칸 이동한다.
            sharkMove();

            // 4. 두 번 전 연습에서 생긱 물고기의 냄새가 격자에서 사라진다.
            removeSmells();

            // 5. 1번에서 사용한 복제 마법이 완료된다. 모든 복제된 물고기는 1에서의 위치와 방향을 그대로 갖게 된다.
            copyMagic();

        }

        int answer = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                answer += map[i][j].size();
            }
        }

        System.out.println(answer);

    }

    static void copyMagic() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].size() != 0) {
                    for (Fish fish : map[i][j]) {
                        tempMap[i][j].add(new Fish(fish.x, fish.y, fish.d));
                    }
                }
            }
        }

        map = tempMap;
    }

    static void removeSmells() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (smells[i][j] != 0) {
                    smells[i][j]--;
                }
            }
        }
    }

    // 3. 상어가 연속해서 3칸 이동한다. 상어는 현재 칸에서 상하좌우로 인접한 칸으로 이동할 수 있다. 연속해서 이동하는 칸 중에 격자의 범위를 벗어나는 칸이 있으면, 그 방법은 불가능한 이동 방법이다.
    // 연속해서 이동하는 칸 중에 상어가 물고기가 있는 칸으로 이동하게 된다면, 그 칸에 있는 모든 물고기는 격자에서 제외되면, 제외되는 모든 물고기는 물고기 냄새를 남긴다.
    // 가능한 이동 방법 중에서 제외되는 물고기의 수가 가장 많은 방법으로 이동하며, 그러한 방법이 여러가지인 경우 사전 순으로 가장 앞서는 방법
    static void sharkMove() {
        int[] dx = {0, -1, 0, 1, 0};
        int[] dy = {0, 0, -1, 0, 1};

        ArrayList<Integer> directions = new ArrayList<>();
        int maxFishEat = -1;


        Loop1:
        for (int i = 1; i <= 4; i++) {
            Loop2:
            for (int j = 1; j <= 4; j++) {
                Loop3:
                for (int k = 1; k <= 4; k++) {
                    int fishEat = 0;
                    int nx = shark.x + dx[i];
                    int ny = shark.y + dy[i];

                    boolean[][] visited = new boolean[4][4];

                    if(nx < 0 || nx >= 4 || ny <0 || ny >=4) {
                        continue Loop1;
                    }

                    if(visited[nx][ny] == false) {
                        fishEat += tempMap[nx][ny].size();
                        visited[nx][ny] = true;
                    }

                    nx = nx + dx[j];
                    ny = ny + dy[j];

                    if(nx < 0 || nx >= 4 || ny <0 || ny >=4) {
                        continue Loop2;
                    }

                    if(visited[nx][ny] == false) {
                        fishEat += tempMap[nx][ny].size();
                        visited[nx][ny] = true;
                    }

                    nx = nx + dx[k];
                    ny = ny + dy[k];

                    if(nx < 0 || nx >= 4 || ny <0 || ny >=4) {
                        continue Loop3;
                    }

                    if(visited[nx][ny] == false) {
                        fishEat += tempMap[nx][ny].size();
                        visited[nx][ny] = true;
                    }

                    if (maxFishEat < fishEat) {
                        maxFishEat = fishEat;
                        directions = new ArrayList<>();
                        directions.add(i);
                        directions.add(j);
                        directions.add(k);
                    }
                }
            }
        }

        int nx = shark.x;
        int ny = shark.y;

        for (int direction : directions) {
            nx += dx[direction];
            ny += dy[direction];

            if (tempMap[nx][ny].size() != 0) {
                tempMap[nx][ny] = new ArrayList<>();
                smells[nx][ny] = 3;
            }
        }

        shark.x = nx;
        shark.y = ny;
    }

    // 2. 모든 물고기가 한 칸 이동한다. 상어가 있는 칸, 물고기의 냄새가 있는 칸, 격자를 벗어나는 칸으로는 이동할 수 없다.
    // 각 물고기는 자신이 가지고 있는 이동 방향이 이동할 수 있는 칸을 향할 때까지 방향을 45도 반시계 회전시킨다.
    // 만약 이동할 수 있는 칸이 없으면 이동 하지 않는다.
    static void fishMove() {
        tempMap = new ArrayList[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (Fish fish : map[i][j]) {
                    boolean isMove = false;

                    int nd = fish.d + 1;

                    for (int k = 0; k < 8; k++) {
                        nd--;
                        if(nd == -1) nd = 7;

                        int nx = fish.x + dx[nd];
                        int ny = fish.y + dy[nd];

                        if(nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && smells[nx][ny] == 0) {
                            if (nx == shark.x && ny == shark.y) {
                                continue;
                            }

                            tempMap[nx][ny].add(new Fish(nx, ny, nd));
                            isMove = true;
                            break;
                        }
                    }

                    if (isMove == false) {
                        tempMap[fish.x][fish.y].add(new Fish(fish.x, fish.y, fish.d));
                    }
                }
            }
        }
    }

    static class Fish {
        int x;
        int y;
        int d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", d=" + d +
                    '}';
        }
    }

}
