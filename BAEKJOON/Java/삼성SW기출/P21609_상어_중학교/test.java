package 삼성SW기출.P21609_상어_중학교;

import java.io.*;
import java.util.*;

public class test {

    static public class Block {
        Pos pos;
        int color;

        Block(int x, int y, int color) {
            this.pos = new Pos(x, y);
            this.color = color;
        }
    }

    static public class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static public class Group {
        int blockCnt;
        int rainbowCnt;
        Pos base;

        Group() {
            this.blockCnt = 0;
            this.rainbowCnt = 0;
            this.base = new Pos(-1, -1);
        }
    }

    static int N, M, map[][], score;
    static final int black = -1, rainbow = 0, empty = -2;
    static int[][] dir = { { -1, 0, 0, 1 }, { 0, -1, 1, 0 } };
    static Group bigGroupBase; // 가장 큰 블록 그룹의 기준 블록
    static Queue<Block> bigGroup = new LinkedList<>();// 가장 큰 블록 그룹

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken()); // 격자 한 변의 크기
        M = Integer.parseInt(st.nextToken()); // 일반 블록 색깔 수
        score = 0;
        map = new int[N][N];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            if (!findBlock())
                break;
            eraseBlock();
            gravity();
            rotate();
            gravity();
        }

        System.out.println(score);
    }

    // 가장 큰 블록을 찾는다.
    static boolean findBlock() {
        bigGroup = new LinkedList<>(); // 가장 큰 블록 그룹을 담는다.
        bigGroupBase = new Group(); // 가장 큰 그룹의 기준 블록을 저장한다.
        boolean[][] isVisited = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (M >= map[i][j] && map[i][j] > rainbow && !isVisited[i][j]) {
                    bfs(i, j, map[i][j], isVisited);
                }
            }
        }
        return bigGroup.size() > 1; // 그룹에 속한 블록의 개수는 2개 이상이어야함.
    }

    static void bfs(int x, int y, int color, boolean[][] isVisited) {
        Queue<Block> q = new LinkedList<>();
        Queue<Block> tmp = new LinkedList<>(); // 현재 블록의 개수가 몇개인지 비교하기 위함.
        q.add(new Block(x, y, color));
        tmp.add(new Block(x, y, color));
        isVisited[x][y] = true;
        // 기준 블록을 담는다.
        Group tmpBase = new Group();
        tmpBase.base.x = x;
        tmpBase.base.y = y;
        tmpBase.blockCnt++;
        // 탐색 시작
        while (!q.isEmpty()) {
            Block now = q.poll();
            for (int d = 0; d < 4; ++d) {
                int nx = now.pos.x + dir[0][d];
                int ny = now.pos.y + dir[1][d];
                if (isBoundary(nx, ny) && (map[nx][ny] == color || map[nx][ny] == rainbow) && !isVisited[nx][ny]) {
                    isVisited[nx][ny] = true;
                    q.add(new Block(nx, ny, map[nx][ny]));
                    tmp.add(new Block(nx, ny, map[nx][ny]));
                    if (map[nx][ny] != rainbow)
                        tmpBase.blockCnt++; // 기준 블록 카운트
                    else
                        tmpBase.rainbowCnt++; // 무지개 블록 카운트
                }
            }
        }
        boolean isChange = false;
        if (tmp.size() > 1 && tmpBase.blockCnt >= 1) { // 그룹에는 최소 1개이상의 일반 블록이 있어야한다.
            // 그룹이 더 크면
            if (bigGroup.size() < tmp.size()) {
                isChange = true;
            } else if (bigGroup.size() == tmp.size()) {
                // 그룹 개수가 가틍면
                // 무지개 블록 수가 큰 쪽
                if (bigGroupBase.rainbowCnt < tmpBase.rainbowCnt) {
                    isChange = true;
                } else if (bigGroupBase.rainbowCnt == tmpBase.rainbowCnt) {
                    if (bigGroupBase.base.x < tmpBase.base.x) {
                        isChange = true;
                    } else if (bigGroupBase.base.x == tmpBase.base.x && bigGroupBase.base.y < tmpBase.base.y) {
                        isChange = true;
                    }
                }
            }
        }

        if (isChange) {
            bigGroup.clear();
            while (!tmp.isEmpty()) {
                Block now = tmp.poll();
                if (now.color == rainbow) {
                    isVisited[now.pos.x][now.pos.y] = false;
                }
                bigGroup.add(now);
            }
            bigGroupBase.base.x = tmpBase.base.x;
            bigGroupBase.base.y = tmpBase.base.y;
            bigGroupBase.blockCnt = tmpBase.blockCnt;
            bigGroupBase.rainbowCnt = tmpBase.rainbowCnt;
        } else {
            while (!tmp.isEmpty()) {
                Block now = tmp.poll();
                if (now.color == rainbow) {
                    isVisited[now.pos.x][now.pos.y] = false;
                }
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static void eraseBlock() {
        score += bigGroup.size() * bigGroup.size(); // 점수 계산
        while (!bigGroup.isEmpty()) {
            Block now = bigGroup.poll();
            map[now.pos.x][now.pos.y] = empty;
        }
    }

    // 중력
    static void gravity() {

        int downX = 1;
        int downY = 0;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (map[i][j] >= 0 && map[i][j] <= M) {
                    int x = i;
                    int y = j;

                    while (true) {
                        int nx = x + downX;
                        int ny = y + downY;

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != empty) {
                            break;
                        }

                        map[nx][ny] = map[x][y];
                        map[x][y] = empty;

                        x = nx;
                        y = ny;
                    }

                }
            }
        }
    }

    // 90도 반시계 방향
    static void rotate() {
        int[][] tempMap = new int[N][N];

        for (int i = N - 1; i >= 0 ; i--) {
            for (int j = 0; j < N; j++) {
                tempMap[(N - 1) - i][j] = map[j][i];
            }
        }

        map = tempMap;
    }

}