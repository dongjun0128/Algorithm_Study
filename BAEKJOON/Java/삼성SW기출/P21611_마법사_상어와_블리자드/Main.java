package 삼성SW기출.P21611_마법사_상어와_블리자드;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] map;
    static int sharkX;
    static int sharkY;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21611_마법사_상어와_블리자드/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        sharkX = (N - 1) / 2;
        sharkY = (N - 1) / 2;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            blizzard(d, s);
            move();

            while (true) {
                boolean flag = boom();
                if(flag == false) break;
            }

            divided();
        }

        System.out.println(answer);
        // 격자의 가장 윗 칸은 (1,1), 가장 오른쪽 아랫 칸은(N,N), 마법사 상어는 ((N + 1) / 2 , (N + 1) / 2))에 있따.
        // 일부 칸과 칸 사이에는 벽이 세워져 있다

        // 가장 처음에 상어가 있는 칸을 제외한 나머지 칸에는 구슬이 하나 들어갈 수 있다.
        // 1번 구슬, 2번 구슬, 3번 구슬이 있다.
        // 같은 번호를 가진 구슬이 번호가 연속하는 칸에 있으면, 그 구슬을 연속하는 구슬이라고 한다.


        // 빈칸을 구슬로 모두 채운다.

        // 구슬은 4개 이상 연속하는 구슬이 있을 때 폭발한다.
        // 폭발 후 구슬이 이동한다.


        // 더 이상 폭발할 구슬이 없을 때 구슬이 변화한다.

        // 연속하는 구슬을 하나의 그룹이라고 한다.
        // 하나의 그룹은 두 개의 구슬 A와 B로 변한다.

        // 구슬 A의 번호는 그룹에 들어있는 구슬의 개수이고, B는 그룹을 이루고 있는 구슬의 번호이다.
        // 구슬은 다시 그룹의 수서대로 1번 칸 부터 차례대로 A,B,순서로 칸에 들어간다.
        // 만약, 구슬이 칸의 수보다 많아 칸에 들어가지 못하는 경우 그러한 구슬은 사라진다.


    }

    static void divided() {
        ArrayList<Integer> numList = new ArrayList<>();

        int nowX = sharkX;
        int nowY = sharkY;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int direction = 0;
        int length = 0;

        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if(map[nowX][nowY] != 0)
                        numList.add(map[nowX][nowY]);
                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if(map[nowX][nowY] != 0)
                        numList.add(map[nowX][nowY]);
                }

            }

            direction = (direction + 1) % 4;
        }

        ArrayList<Integer> tempNumList = new ArrayList<>();

        for (int i = 0; i < numList.size(); i++) {

            int num = numList.get(i);
            int numCnt = 1;

            int nextI = i + 1;

            while (true) {
                if (nextI >= numList.size()) break;

                if (numList.get(nextI) == numList.get(i)) {
                    numCnt++;
                } else break;

                nextI++;
            }

            tempNumList.add(numCnt);
            tempNumList.add(num);

            i = nextI - 1;
        }

        int index = 0;
        int[][] tempMap = new int[N][N];
        nowX = sharkX;
        nowY = sharkY;
        direction = 0;
        length = 0;

        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= tempNumList.size()) break Loop1;

                    tempMap[nowX][nowY] = tempNumList.get(index++);
                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= tempNumList.size()) break Loop1;

                    tempMap[nowX][nowY] = tempNumList.get(index++);
                }

            }

            direction = (direction + 1) % 4;
        }

        map = tempMap;
    }

    static boolean boom() {
        boolean flag = false;
        ArrayList<Integer> numList = new ArrayList<>();

        int nowX = sharkX;
        int nowY = sharkY;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int direction = 0;
        int length = 0;

        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if(map[nowX][nowY] != 0)
                        numList.add(map[nowX][nowY]);
                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if(map[nowX][nowY] != 0)
                        numList.add(map[nowX][nowY]);
                }

            }

            direction = (direction + 1) % 4;
        }

        ArrayList<Integer> tempNumList = new ArrayList<>();

        for (int i = 0; i < numList.size(); i++) {

            int num = 0;
            int numCnt = 0;

            int nextI = i + 1;

            while (true) {
                if (nextI >= numList.size()) break;

                if (numList.get(nextI) == numList.get(i)) {
                    if (num == 0 && numCnt == 0) {
                        num = numList.get(i);
                        numCnt = 2;
                    } else {
                        numCnt++;
                    }
                } else break;

                nextI++;
            }

            if (numCnt >= 4) {
                i = nextI - 1;
                answer += num * numCnt;
                flag = true;

            } else {
                tempNumList.add(numList.get(i));
            }
        }

        int index = 0;
        int[][] tempMap = new int[N][N];
        nowX = sharkX;
        nowY = sharkY;
        direction = 0;
        length = 0;


        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= tempNumList.size()) break Loop1;

                    tempMap[nowX][nowY] = tempNumList.get(index++);
                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= tempNumList.size()) break Loop1;

                    tempMap[nowX][nowY] = tempNumList.get(index++);
                }

            }

            direction = (direction + 1) % 4;
        }

        map = tempMap;

        return flag;
    }

    static void move() {
        int[][] tempMap = new int[N][N];
        ArrayList<Integer> numList = new ArrayList<>();

        int nowX = sharkX;
        int nowY = sharkY;

        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int direction = 0;
        int length = 0;

        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if (map[nowX][nowY] != 0) {
                        numList.add(map[nowX][nowY]);
                    }

                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N) break Loop1;

                    if (map[nowX][nowY] != 0) {
                        numList.add(map[nowX][nowY]);
                    }
                }

            }

            direction = (direction + 1) % 4;
        }

        int index = 0;
        nowX = sharkX;
        nowY = sharkY;
        direction = 0;
        length = 0;

        Loop1:
        while (true) {

            if (direction % 2 == 0) {
                length++;

                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= numList.size()) break Loop1;

                    tempMap[nowX][nowY] = numList.get(index++);
                }

            } else {
                for (int i = 0; i < length; i++) {
                    nowX = nowX + dx[direction];
                    nowY = nowY + dy[direction];

                    if(nowX < 0 || nowX >= N || nowY < 0 || nowY >= N || index >= numList.size()) break Loop1;

                    tempMap[nowX][nowY] = numList.get(index++);
                }

            }

            direction = (direction + 1) % 4;
        }

        map = tempMap;
    }

    // 상어는 d 방향으로 거리가 s 이하인 모든 칸에 얼음 파편을 던져 그 칸에 있는 구슬을 모두 파괴한다.
    // 구슬이 파괴되면 그 칸은 구슬이 들어있지 않은 빈 칸이 되고, 벽은 파괴되지 않는다.
    static void blizzard(int d, int s) {
        for (int i = 1; i <= s; i++) {
            int nx = sharkX + (dx[d] * i);
            int ny = sharkY + (dy[d] * i);

            map[nx][ny] = 0;
        }

    }
}
