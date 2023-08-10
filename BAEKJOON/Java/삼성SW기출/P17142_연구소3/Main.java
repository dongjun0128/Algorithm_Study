package 삼성SW기출.P17142_연구소3;

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
    static int[][] map;
    static ArrayList<Node> viruses = new ArrayList<>();
    static boolean[] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int result = Integer.MAX_VALUE;
    static int originEmptySpace = 0;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P17142_연구소3/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 0) {
                    originEmptySpace++;
                }
                if (map[i][j] == 2) {
                    viruses.add(new Node(i, j, 0));
                }
            }
        }

        if (originEmptySpace == 0) {
            System.out.println(0);
            return;
        }

        visited = new boolean[viruses.size()];

        for (int i = 0; i < viruses.size(); i++) {
            combination(i, 1, new ArrayList<>());
        }

        if(result == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(result);
        }
    }

    static void combination(int index, int depth, ArrayList<Integer> combi) {
        // 1. 체크인
        visited[index] = true;
        combi.add(index);

        // 2. 목적지인가?
        if (depth == M) {
            System.out.println(combi);
            spreadVirus(originEmptySpace,combi);
        } else {
            // 3. 연결된 곳 순회
            for (int i = index; i < viruses.size(); i++) {
                if (visited[i] == false) { // 4. 갈 수 있는가?
                    // 5. 간다.
                    combination(i, depth + 1, combi);
                }
            }
        }

        // 6. 체크아웃
        visited[index] = false;
        combi.remove(depth - 1);
    }

    static void spreadVirus(int emptySpace, ArrayList<Integer> combi) {

        Queue<Node> q = new LinkedList<>();
        boolean[][] infected = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            Node node = viruses.get(combi.get(i));
            infected[node.x][node.y] = true;
            q.add(node);
        }

        while (!q.isEmpty()) {

            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if (infected[nx][ny] || map[nx][ny] == 1) continue;

                if (map[nx][ny] == 0) {
                    emptySpace--;
                }

                if (emptySpace == 0) {
                    result = Math.min(result, node.time + 1);
                    return;
                }

                infected[nx][ny] = true;
                q.add(new Node(nx, ny, node.time + 1));
            }
        }
    }
}

    class Node {
        int x, y, time;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }