package 삼성SW기출.P21609_상어_중학교.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static PriorityQueue<BlockGroup> blockGroupPriorityQueue;
    static final int BLANK = -9;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P21609_상어_중학교/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 게임
        // 크기가 N * N 격자에서 진행
        // 초기에 격자의 모든 칸에는 블록이 하나씩 들어있다.
        // 블록은 검은색, 무지개, 일반 블록이 있다.
        // 일반 블록은 M가지 색상이 있다.
        // 색은 M이하의 자연수로 표현한다.
        // 검은색 블록은 -1, 무지개 블록은 0
        // 상하좌우에 인접하면 인접한 칸

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;


        while (true) {
            findBlockGroup();

            if (blockGroupPriorityQueue.isEmpty()) {
                break;
            } else {
                // 2. 1에서 찾은 블록 그룹의 모든 블록을 제거, 블록수를 B 라고 했을 때 B^2 점 획득
                answer += getScore();
                // 3. 격자에 중력이 작용
                gravity();
                // 4. 격자가 90도 반시계 방향 회전
                rotation();
                // 5. 다시 중력이 작용
                gravity();

            }
        }

        System.out.println(answer);


        // 오토플레이 기능을 만드려 한다.

        // 블록 그룹이 존재하는 동안 계속해서 반복


        // 그러한 블록 그룹이 여러개라면, 포함된 무지개 블록의 수가 가장 많은 그룹, 기준 블록의 행, 열이 가장 큰 것


        // 격자에 중력이 작용하면 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다.
        // 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.

    }

    static void rotation() {
        int[][] tempMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[(N - 1) - j][i] = map[i][j];
            }
        }

        map = tempMap;
    }

    static void gravity() {
        for (int i = N - 1; i >= 0; i--) {
            Loop1:
            for (int j = 0; j < N; j++) {
                if (map[i][j] == BLANK) {
                    for (int nextI = i - 1; nextI >= 0; nextI--) {
                        if (map[nextI][j] != BLANK) {
                            if (map[nextI][j] == -1) {
                                continue Loop1;
                            } else {
                                int temp = map[nextI][j];
                                map[nextI][j] = map[i][j];
                                map[i][j] = temp;
                                continue Loop1;
                            }
                        }
                    }
                }
            }
        }

    }

    static int getScore() {
        BlockGroup blockGroup = blockGroupPriorityQueue.poll();

        for (Node node : blockGroup.blockList) {
            map[node.x][node.y] = BLANK;
        }

        return blockGroup.blockCnt * blockGroup.blockCnt;
    }

    // 1. 크기가 가장 큰 블록 그룹을 찾는다.

    // 블록 그룹은 연결된 블록의 집합
    // 그룹에는 일반 블록이 적어도 하나 이썽야 하며, 일반 블록의 색은 모두 같아야 한다.
    // 검은색 블록은 포함하면 안되고, 무지개 블록은 얼마나 들어있든 상관없다.
    // 그룹에 속한 블록의 개수는 2보다 크거나 같아야 하며, 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 한다.
    // 블록 그룹의 기준은 블록은 무지개 블록이 아닌 블록 중에서 행, 열이 가장 작은 블록
    static void findBlockGroup() {
        blockGroupPriorityQueue = new PriorityQueue<>();
        visited = new boolean[N][N];
        Queue<Node> visitedCheck = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false && map[i][j] >= 1 && map[i][j] <= M) {
                    boolean[][] tempVisited = new boolean[N][N];
                    int blockCnt = 0;
                    int rainbowCnt = 0;
                    int id = map[i][j];

                    ArrayList<Node> blockList = new ArrayList<>();
                    Queue<Node> queue = new LinkedList<>();

                    queue.add(new Node(i, j));

                    while (!queue.isEmpty()) {
                        Node node = queue.poll();

                        for (int k = 0; k < 4; k++) {
                            int nx = node.x + dx[k];
                            int ny = node.y + dy[k];

                            if (nx >= 0 && nx < N && ny >= 0 && ny < N && tempVisited[nx][ny] == false) {
                                if (map[nx][ny] == 0) {
                                    blockCnt++;
                                    rainbowCnt++;

                                    tempVisited[nx][ny] = true;
                                    queue.add(new Node(nx, ny));
                                    visitedCheck.add(new Node(nx, ny, 0));
                                    blockList.add(new Node(nx, ny));
                                } else if (map[nx][ny] == id) {
                                    blockCnt++;

                                    tempVisited[nx][ny] = true;
                                    queue.add(new Node(nx, ny));
                                    visitedCheck.add(new Node(nx, ny, id));
                                    blockList.add(new Node(nx, ny));
                                }
                            }
                        }
                    }

                    if (blockCnt >= 2) {
                        while (!visitedCheck.isEmpty()) {
                            Node node = visitedCheck.poll();

                            if (node.id != 0) {
                                visited[node.x][node.y] = true;
                            }

                        }

                        blockGroupPriorityQueue.add(new BlockGroup(i, j, blockCnt, rainbowCnt, blockList));
                    }
                }
            }
        }
    }

    static class Node {
        int x;
        int y;
        int id;

        public Node(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

    }

    static class BlockGroup implements Comparable<BlockGroup> {
        int x;
        int y;
        int blockCnt;
        int rainbowCnt;
        ArrayList<Node> blockList;

        public BlockGroup(int x, int y, int blockCnt, int rainbowCnt, ArrayList<Node> blockList) {
            this.x = x;
            this.y = y;
            this.blockCnt = blockCnt;
            this.rainbowCnt = rainbowCnt;
            this.blockList = blockList;
        }

        @Override
        public int compareTo(BlockGroup o) {
            int cmp1 = o.blockCnt - this.blockCnt; // 블록 개수에 대해 내림차순

            if (cmp1 == 0) {
                int cmp2 = o.rainbowCnt - this.rainbowCnt; // 무지개 블록 개수에 대해 내림차순
                if (cmp2 == 0) {
                    int cmp3 = o.x - this.x; // 기준 블록의 행에 대해 내림차순
                    if (cmp3 == 0) {
                        int cmp4 = o.y - this.y; // 기준 블록의 열에 대해 내림차순
                        return cmp4;
                    }

                    return cmp3;
                }

                return cmp2;
            }

            return cmp1;
        }
    }

}
