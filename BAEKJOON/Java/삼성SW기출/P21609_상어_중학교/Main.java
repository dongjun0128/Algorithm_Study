package 삼성SW기출.P21609_상어_중학교;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    static int N, M;
    static int[][] map;
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};
    static int maxSize = 0;
    static int maxSizeRainbowCnt = 0;
    static ArrayList<Node> maxSizeBlockList;
    static int result = 0;
    static int standardBlockX = 0;
    static int standardBlockY = 0;
    static final int BLANK = -9;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21609_상어_중학교/input.txt"));
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

        while (true) {
            standardBlockX = 0;
            standardBlockY = 0;
            maxSize = 0;
            maxSizeRainbowCnt = 0;
            maxSizeBlockList = new ArrayList<>();

            findMaxBlock();
            if(maxSize == 0) break;

            removeBlock();
            gravity();
            rotation();
            gravity();

        }

        System.out.println(result);

    }

    static void rotation() {
        int[][] tempMap = new int[N][N];

        for (int i = N - 1; i >= 0 ; i--) {
            for (int j = 0; j < N; j++) {
                tempMap[(N - 1) - i][j] = map[j][i];
            }
        }

        map = tempMap;
    }

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

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] != BLANK) {
                            break;
                        }

                        map[nx][ny] = map[x][y];
                        map[x][y] = BLANK;

                        x = nx;
                        y = ny;
                    }

                }
            }
        }
    }

    static void removeBlock() {
        result += maxSize * maxSize;

        for (Node node : maxSizeBlockList) {
            map[node.x][node.y] = BLANK;
        }
    }

    static void findMaxBlock() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] != 0 && map[i][j] != -1 && map[i][j] != BLANK) {
                    block(i, j);
                }
            }
        }
    }

    static void block(int x, int y) {
        int rainbowCnt = 0;
        int blockNum = map[x][y];
        boolean[][] visited = new boolean[N][N];

        ArrayList<Node> blockList = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        blockList.add(new Node(x, y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && visited[nx][ny] == false) {
                    if (map[nx][ny] == blockNum) {
                        queue.add(new Node(nx, ny));
                        blockList.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] == 0) {
                        queue.add(new Node(nx, ny));
                        blockList.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                        rainbowCnt++;
                    }
                }
            }
        }

        if (blockList.size() >= 2) {
            if (blockList.size() > maxSize) {
                maxSizeBlockList = blockList;
                maxSize = blockList.size();
                maxSizeRainbowCnt = rainbowCnt;
                standardBlockX = x;
                standardBlockY = y;
            } else if (blockList.size() == maxSize) {
                if (rainbowCnt > maxSizeRainbowCnt) {
                    maxSizeBlockList = blockList;
                    maxSize = blockList.size();
                    maxSizeRainbowCnt = rainbowCnt;
                    standardBlockX = x;
                    standardBlockY = y;
                } else if (rainbowCnt == maxSizeRainbowCnt) {
                    if(standardBlockX < x) {
                        maxSizeBlockList = blockList;
                        maxSize = blockList.size();
                        maxSizeRainbowCnt = rainbowCnt;
                        standardBlockX = x;
                        standardBlockY = y;
                    } else if (standardBlockX == x) {
                        if(standardBlockY < y) {
                            maxSizeBlockList = blockList;
                            maxSize = blockList.size();
                            maxSizeRainbowCnt = rainbowCnt;
                            standardBlockX = x;
                            standardBlockY = y;
                        }
                    }
                }
            }
        }

    }
}
