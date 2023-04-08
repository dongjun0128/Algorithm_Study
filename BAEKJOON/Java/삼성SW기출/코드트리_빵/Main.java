package 삼성SW기출.코드트리_빵;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class BaseCamp implements Comparable<BaseCamp> {
        int x;
        int y;
        int cost;

        public BaseCamp(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(BaseCamp o) {
            int cmp1 = cost - o.cost;

            if (cmp1 == 0) {
                int cmp2 = x - o.x;
                if (cmp2 == 0) {
                    return y - o.y;
                } else return cmp2;
            } else return cmp1;
        }
    }

    static class Node implements Comparable<Node> {
        int x;
        int y;
        boolean visit;

        public Node(int x, int y, boolean visit) {
            this.x = x;
            this.y = y;
            this.visit = visit;
        }

        @Override
        public int compareTo(Node o) {
            int cmp1 = x - o.x;

            if (cmp1 == 0) {
                return y - o.y;
            } else return cmp1;
        }
    }

    static int N, M;
    static int[][] map;
    static ArrayList<Node> stores;
    static ArrayList<Node> people;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/코드트리_빵/input.txt"));
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

        people = new ArrayList<>();

        stores = new ArrayList<>();
        stores.add(new Node(-1, -1, false));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            stores.add(new Node(x, y, false));
        }

        int time = 0;
        int cnt = M;

        while (true) {
            time++;
            // 1. 격자에 있는 사람들이 가고싶은 편의점을 향해서 움직임
            for (int i = 0; i < people.size(); i++) {
                Node person = people.get(i);

                if (person.visit == false) {
                    Node store = stores.get(i + 1);
                    int[][] tempMap = bfs(store);
                    int goX = 0;
                    int goY = 0;
                    int minCost = Integer.MAX_VALUE;

                    for (int j = 0; j < 4; j++) {
                        int nx = person.x + dx[j];
                        int ny = person.y + dy[j];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] != -1 && tempMap[nx][ny] != 0) {

                            int cost = tempMap[nx][ny];

                            if(minCost > cost) {
                                minCost = cost;
                                goX = nx;
                                goY = ny;
                            }
                        }
                    }

                    person.x = goX;
                    person.y = goY;
                }
            }

            // 2. 편의점 도착하면 멈춤

            for (int i = 0; i < people.size(); i++) {
                Node person = people.get(i);
                Node store = stores.get(i + 1);

                if (person.visit == false) {
                    // 3. 도착한 편의점 못움직이게 못박음
                    if (person.x == store.x && person.y == store.y) {
                        person.visit = true;
                        map[store.x][store.y] = -1;
                        cnt--;
                    }
                }
            }

            // 4. t번째 사람 베이스 캠프 두둥등장
            if (time <= M) {
                BaseCamp baseCamp = findNearBaseCamp(time);
                map[baseCamp.x][baseCamp.y] = -1;
                people.add(new Node(baseCamp.x, baseCamp.y, false));
            }

            if (cnt == 0) break;
        }

        System.out.println(time);
    }

    public static int[][] bfs(Node store) {
        int[][] tempMap = new int[N][N];

        Queue<Node> queue = new LinkedList<>();
        queue.add(store);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && tempMap[nx][ny] == 0 && map[nx][ny] != -1) {
                    tempMap[nx][ny] = tempMap[node.x][node.y] + 1;
                    queue.add(new Node(nx, ny, false));
                }
            }
        }

        tempMap[store.x][store.y] = -1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == -1) tempMap[i][j] = 9999;
            }
        }

        return tempMap;
    }

    public static BaseCamp findNearBaseCamp(int index) {
        Node store = stores.get(index);

        Queue<Node> queue = new LinkedList<>();

        queue.add(store);

        int[][] tempMap = new int[N][N];

        ArrayList<BaseCamp> baseCampArrayList = new ArrayList<>();

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && tempMap[nx][ny] == 0 && map[nx][ny] != -1) {
                    tempMap[nx][ny] = tempMap[node.x][node.y] + 1;
                    if (map[nx][ny] == 1) {
                        baseCampArrayList.add(new BaseCamp(nx, ny, tempMap[nx][ny]));
                    }
                    queue.add(new Node(nx, ny, false));
                }
            }
        }

        Collections.sort(baseCampArrayList);

        return baseCampArrayList.get(0);
    }

}