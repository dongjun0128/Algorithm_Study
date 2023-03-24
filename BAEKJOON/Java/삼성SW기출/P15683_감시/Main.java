package 삼성SW기출.P15683_감시;

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
    static ArrayList<Integer> cctvs;
    // 동 서 남 북
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static int[] directions;
    static boolean[] visited;
    static ArrayList<Node> cctvNodes;
    static int result = 0;
    static int test=0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P15683_감시/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        result = N * M;

        map = new int[N][M];
        cctvs = new ArrayList<>();
        cctvNodes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] >= 1 && map[i][j] <= 5) {
                    cctvs.add(map[i][j]);
                    cctvNodes.add(new Node(i, j));
                }
            }
        }

        directions = new int[cctvs.size()];
        visited = new boolean[cctvs.size()];

        if (cctvs.size() >= 1) {
            if (cctvs.get(0) == 1) {
                for (int j = 0; j < 4; j++) {
                    // 5. 간다.
                    dfs(0, j, 1);
                }
            } else if (cctvs.get(0) == 2) {
                for (int j = 0; j < 2; j++) {
                    dfs(0, j, 1);
                }
            } else if (cctvs.get(0) == 3) {
                for (int j = 0; j < 4; j++) {
                    // 5. 간다.
                    dfs(0, j, 1);
                }
            } else if (cctvs.get(0) == 4) {
                for (int j = 0; j < 4; j++) {
                    // 5. 간다.
                    dfs(0, j, 1);
                }
            } else if (cctvs.get(0) == 5) {
                dfs(0, 0, 1);
            }
        } else{
            result = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) result++;
                }
            }
        }

        System.out.println(result);
    }

    static void dfs(int cctvIndex, int direction, int depth) {
        // 1. 체크인
        visited[cctvIndex] = true;
        directions[cctvIndex] = direction;

        // 2. 목적지인가?
        if (cctvIndex == cctvs.size() - 1) {

            int[][] tempMap = new int[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    tempMap[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < cctvs.size(); i++) {
                int cctv = cctvs.get(i);
                int dict = directions[i];

                int x = cctvNodes.get(i).x;
                int y = cctvNodes.get(i).y;

                if (cctv == 1) {
                    tempMap = move(tempMap, dict, x, y);
                } else if (cctv == 2) {
                    tempMap = move(tempMap, dict, x, y);
                    tempMap = move(tempMap, (dict + 2) % 4, x, y);
                } else if (cctv == 3) {
                    tempMap = move(tempMap, dict, x, y);
                    tempMap = move(tempMap, (dict + 3) % 4, x, y);
                } else if (cctv == 4) {
                    tempMap = move(tempMap, dict, x, y);
                    tempMap = move(tempMap, (dict + 2) % 4, x, y);
                    tempMap = move(tempMap, (dict + 3) % 4, x, y);
                } else if (cctv == 5) {
                    tempMap = move(tempMap, dict, x, y);
                    tempMap = move(tempMap, (dict + 1) % 4, x, y);
                    tempMap = move(tempMap, (dict + 2) % 4, x, y);
                    tempMap = move(tempMap, (dict + 3) % 4, x, y);
                }
            }

            int cnt = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (tempMap[i][j] == 0) cnt++;
                }
            }

            result = Math.min(result, cnt);
        } else {
            // 3. 연결된 곳 순회
            for (int i = 0; i < depth + 1; i++) {
                // 4. 갈 수 있는가?
                if (visited[i] == false) {
                    if (cctvs.get(i) == 1) {
                        for (int j = 0; j < 4; j++) {
                            // 5. 간다.
                            dfs(i, j, depth + 1);
                        }
                    } else if (cctvs.get(i) == 2) {
                        for (int j = 0; j < 2; j++) {
                            dfs(i, j, depth + 1);
                        }
                    } else if (cctvs.get(i) == 3) {
                        for (int j = 0; j < 4; j++) {
                            // 5. 간다.
                            dfs(i, j, depth + 1);
                        }
                    } else if (cctvs.get(i) == 4) {
                        for (int j = 0; j < 4; j++) {
                            // 5. 간다.
                            dfs(i, j, depth + 1);
                        }
                    } else if (cctvs.get(i) == 5) {
                        dfs(i, 0, depth + 1);
                    }
                }
            }
        }

        // 6. 체크아웃
        visited[cctvIndex] = false;
        directions[cctvIndex] = -1;
    }

    static int[][] move(int[][] tempMap, int dict, int x, int y) {
        int nx = x;
        int ny = y;

        while (true) {
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) break;
            if (tempMap[nx][ny] == 6) break;

            if (tempMap[nx][ny] == 0) {
                tempMap[nx][ny] = 8;
            }

            nx += dx[dict];
            ny += dy[dict];
        }

        return tempMap;
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