package 삼성SW기출.코드트리_빵;

import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

class Pair {
    int x, y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Pair p) {
        return this.x == p.x && this.y == p.y;
    }
}

public class solution {
    public static final int INT_MAX = Integer.MAX_VALUE;
    public static final Pair EMPTY = new Pair(-1, -1);
    public static final int DIR_NUM = 4;
    public static final int MAX_M = 30;
    public static final int MAX_N = 15;

    // 변수 선언
    public static int n, m;

    // 0이면 빈 칸, 1이면 베이스 캠프, 2라면 아무도 갈 수 없는 곳을 뜻합니다.
    public static int[][] grid = new int[MAX_N][MAX_N];

    // 편의점 목록을 관리합니다.
    public static Pair[] cvsList = new Pair[MAX_M];

    // 현재 사람들의 위치를 관리합니다.
    public static Pair[] people = new Pair[MAX_M];

    // 현재 시간을 기록합니다.
    public static int currT;

    // dx, dy값을
    // 문제에서의 우선순위인 상좌우하 순으로 적어줍니다.
    public static int[] dx = new int[]{-1,  0, 0, 1};
    public static int[] dy = new int[]{ 0, -1, 1, 0};

    // bfs에 사용되는 변수들입니다.
    public static int[][] step = new int[MAX_N][MAX_N];      // 최단거리 결과 기록
    public static boolean[][] visited = new boolean[MAX_N][MAX_N];  // 방문 여부 표시

    // (x, y)가 격자 내에 있는 좌표인지를 판단합니다.
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    // (x, y)로 이동이 가능한지 판단합니다.
    public static boolean canGo(int x, int y) {
        return inRange(x, y) &&       // 범위를 벗어나지 않으면서
                !visited[x][y] &&      // 방문했던 적이 없으면서
                grid[x][y] != 2;       // 이동 가능한 곳이어야 합니다.
    }

    // startPos를 시작으로 하는 BFS를 진행합니다.
    // 시작점으로부터의 최단거리 결과는 step배열에 기록됩니다.
    public static void bfs(Pair startPos) {
        // visited, step 값을 전부 초기화합니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                visited[i][j] = false;
                step[i][j] = 0;
            }

        // 초기 위치를 넣어줍니다.
        Queue<Pair> q = new LinkedList<>();
        int sx = startPos.x, sy = startPos.y;
        q.add(startPos);
        visited[sx][sy] = true;
        step[sx][sy] = 0;

        // BFS를 진행합니다.
        while(!q.isEmpty()) {
            // 가장 앞에 원소를 골라줍니다.
            Pair currPos = q.poll();

            // 인접한 칸을 보며 아직 방문하지 않은 칸을 큐에 넣어줍니다.
            int x = currPos.x, y = currPos.y;
            for(int i = 0; i < DIR_NUM; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                // 갈 수 있는 경우에만 진행합니다.
                if(canGo(nx, ny)) {
                    visited[nx][ny] = true;
                    step[nx][ny] = step[x][y] + 1;
                    q.add(new Pair(nx, ny));
                }
            }
        }
    }

    // 시뮬레이션을 진행합니다.
    public static void simulate() {
        // Step 1. 격자에 있는 사람들에 한하여 편의점 방향을 향해 1칸 움직입니다.
        for(int i = 0; i < m; i++) {
            // 아직 격자 밖에 있는 사람이거나 이미 편의점에 도착한 사람이라면 패스합니다.
            if(people[i] == EMPTY || people[i].isSame(cvsList[i]))
                continue;

            // 원래는 현재 위치에서 편의점 위치까지의 최단거리를 구해줘야 합니다.
            // 다만 최단거리가 되기 위한 그 다음 위치를 구하기 위해서는
            // 거꾸로 편의점 위치를 시작으로 현재 위치까지 오는 최단거리를 구해주는 것이 필요합니다.
            // 따라서 편의점 위치를 시작으로 하는 BFS를 진행합니다.
            bfs(cvsList[i]);

            int px = people[i].x, py = people[i].y;
            // 현재 위치에서 상좌우하 중 최단거리 값이 가장 작은 곳을 고르면
            // 그 곳으로 이동하는 것이 최단거리 대로 이동하는 것이 됩니다.
            // 그러한 위치 중 상좌우하 우선순위대로 가장 적절한 곳을 골라줍니다.
            int minDist = INT_MAX;
            int minX = -1, minY = -1;
            for(int j = 0; j < DIR_NUM; j++) {
                int nx = px + dx[j], ny = py + dy[j];
                if(inRange(nx, ny) && visited[nx][ny] && minDist > step[nx][ny]) {
                    minDist = step[nx][ny];
                    minX = nx; minY = ny;
                }
            }

            // 우선순위가 가장 높은 위치로 한 칸 움직여줍니다.
            people[i] = new Pair(minX, minY);
        }

        // Step 2. 편의점에 도착한 사람에 한하여
        //         앞으로 이동 불가능하다는 표시로
        //         grid값을 2로 바꿔줍니다.
        for(int i = 0; i < m; i++) {
            if(people[i].isSame(cvsList[i])) {
                int px = people[i].x, py = people[i].y;
                grid[px][py] = 2;
            }
        }

        // Step 3. 현재 시간 currT에 대해 currT ≤ m를 만족한다면
        //         t번 사람이 베이스 캠프로 이동합니다.

        // currT가 m보다 크다면 패스합니다.
        if(currT > m)
            return;

        // Step 3-1. 편의점으로부터 가장 가까운 베이스 캠프를 고르기 위해
        //           편의점을 시작으로 하는 BFS를 진행합니다.
        bfs(cvsList[currT - 1]);

        // Step 3-2. 편의점에서 가장 가까운 베이스 캠프를 선택합니다.
        //           i, j가 증가하는 순으로 돌리기 때문에
        //           가장 가까운 베이스 캠프가 여러 가지여도
        //           알아서 (행, 열) 우선순위대로 골라집니다.
        int minDist = INT_MAX;
        int minX = -1, minY = -1;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // 방문 가능한 베이스 캠프 중
                // 거리가 가장 가까운 위치를 찾아줍니다.
                if(visited[i][j] && grid[i][j] == 1 && minDist > step[i][j]) {
                    minDist = step[i][j];
                    minX = i; minY = j;
                }
            }
        }

        // 우선순위가 가장 높은 베이스 캠프로 이동합니다.
        people[currT - 1] = new Pair(minX, minY);
        // 해당 베이스 캠프는 앞으로 이동이 불가능한 칸임을 표시합니다.
        grid[minX][minY] = 2;
    }

    // 전부 편의점에 도착헀는지를 확인합니다.
    public static boolean end() {
        // 단 한 사람이라도
        // 편의점에 도착하지 못했다면
        // 아직 끝나지 않은 것입니다.
        for(int i = 0; i < m; i++) {
            if(!people[i].isSame(cvsList[i]))
                return false;
        }

        // 전부 편의점에 도착했다면 끝입니다.
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 입력:
        n = sc.nextInt();
        m = sc.nextInt();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                grid[i][j] = sc.nextInt();

        for(int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            cvsList[i] = new Pair(x - 1, y - 1);
        }

        // 초기 사람들은 격자 밖에 있으므로
        // 위치를 EMPTY 상태로 놓습니다.
        for(int i = 0; i < m; i++)
            people[i] = EMPTY;

        // 1분에 한번씩 시뮬레이션을 진행합니다.
        while(true) {
            currT++;
            simulate();
            // 전부 이동이 끝났다면 종료합니다.
            if(end()) break;
        }

        System.out.print(currT);
    }
}
