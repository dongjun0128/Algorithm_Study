package 삼성SW기출.P20058_마법사_상어_파이어스톰;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static int n;
    static int[][] map;
    static int iceSize = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P20058_마법사_상어_파이어스톰/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        n = 1;
        for (int i = 0; i < N; i++) {
            n *= 2;
        }

        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while (Q-- > 0) {

            int L = Integer.parseInt(st.nextToken());
            int l = 1;

            for (int i = 0; i < L; i++) {
                l *= 2;
            }

            rotation(l);
            melt();
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    int cnt = 0;
                    Queue<Node> queue = new LinkedList<>();

                    queue.add(new Node(i, j));

                    while (!queue.isEmpty()) {
                        Node node = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = node.x + dx[k];
                            int ny = node.y + dy[k];

                            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                                continue;
                            }

                            if(map[nx][ny] != 0){
                                cnt++;
                                map[nx][ny] = 0;
                                queue.add(new Node(nx,ny));
                            }
                        }
                    }

                    iceSize = Math.max(iceSize, cnt);

                }
            }
        }

        System.out.println(iceSize);
    }

    static void melt() {
        int[][] tempMap = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) continue;
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                        continue;
                    }

                    if (map[nx][ny] != 0) cnt++;
                }

                if (cnt < 3) tempMap[i][j]--;
            }
        }

        map = tempMap;
    }

    static void rotation(int l) {
        int[][] rotationMap = new int[n][n];

        for (int i = 0; i < n; i += l) {
            for (int j = 0; j < n; j += l) {
                rotationMap = rotationProcess(i, j, l, rotationMap);
            }
        }

        map = rotationMap;
    }

    static int[][] rotationProcess(int x, int y, int l, int[][] rotationMap) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                rotationMap[y + j][x + l - i - 1] = map[y + i][x + j];
            }
        }

        return rotationMap;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
