package 삼성SW기출.포탑부수기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Turret {
        int power;
        int x;
        int y;
        int attackTurn;
        boolean heal;

        public Turret(int power, int x, int y) {
            this.power = power;
            this.x = x;
            this.y = y;
            this.attackTurn = 0;
        }

        @Override
        public String toString() {
            return "Turret{" +
                    "power=" + power +
                    ", x=" + x +
                    ", y=" + y +
                    ", attackTurn=" + attackTurn +
                    '}';
        }
    }

    static class Node {
        int x;
        int y;

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

    static Turret[][] map;
    static int N, M, K;
    static ArrayList<Turret> turretList = new ArrayList<>();
    static ArrayList<Node> laserAttack;
    static boolean[][] visited;

    static Turret attacker;
    static Turret defender;

    // 우 하 좌 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/포탑부수기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Turret[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int power = Integer.parseInt(st.nextToken());
                map[i][j] = new Turret(power, i, j);
            }
        }

        // 각 포탑에는 공격력이 존재하며, 상황에 따라 공격력이 줄어들거나 늘어날 수 있다.
        // 공격력이 0 이하가 된다면, 해당 포탑은 부서지며 더 이상의 공격을 할 수 없다.

        // 하나의 턴은 다음의 4가지 액션을 순서대로 수행하며 총 K번 반복된다.
        // 만약 부서지지 않은 포탑이 1개가 된다면, 그 즉시 중지된다.

        for (int k = 1; k <= K; k++) {
            // 1. 공격자 선정
            // 부서지지 않은 포탑 중 가장 약한 포탑이 공격자로 선정
            // 공격자로 선정되면 가장 약한 포탑이므로, 핸디캡이 적용되어 N + M 만큼의 공격력이 증가
            // 공격력이 가장 낮은 포탑이 2개 이상이라면, 가장 최근에 공격한 포탑이 가장 약한 포탑
            // 그러한 포탑이 2개 이상이라면, 각 포탑의 위치의 행과 열의 합이 가장 큰 포탑이 가장 약한 포탑
            // 그러한 포탑이 2개 이상이라면, 열 값이 가장 큰 포탑이 가장 약한 포탑

            turretList = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j].power != 0) {
                        turretList.add(map[i][j]);
                    }
                    map[i][j].heal = true;
                }
            }

            // 만약 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지
            if(turretList.size() <= 1) break;

            Collections.sort(turretList, new Comparator<Turret>() {
                @Override
                public int compare(Turret o1, Turret o2) {
                    int cmp1 = o1.power - o2.power; // 공격력 오름차순

                    if (cmp1 == 0) {
                        int cmp2 = o2.attackTurn - o1.attackTurn; // 공격 턴 내림차순
                        if (cmp2 == 0) {
                            int cmp3 = (o2.x + o2.y) - (o1.x + o1.y); // 행과 열의 합 내림차순
                            if (cmp3 == 0) {
                                int cmp4 = o2.y - o1.y; // 열 값 내림차순
                                return cmp4;
                            } else {
                                return cmp3;
                            }
                        } else {
                            return cmp2;
                        }
                    } else {
                        return cmp1;
                    }
                }
            });

            attacker = turretList.get(0);
            turretList.remove(attacker);
            attacker.power += N;
            attacker.power += M;
            attacker.heal = false;
            attacker.attackTurn = k;

            // 2. 공격자의 공격
            // 자신을 제외한 가장 강한 포탑을 공격
            // 가장 강한 포탑은 약한 포탑 선정 기준의 반대이다.
            // 공격력이 가장 높다, 같으면 공격한지 가장 오래된 포탑이 가장 강하다, 같으면 행과 열의 합이 가장 작은 포탑, 같으면 열 값이 가장 작은 포탑

            Collections.sort(turretList, new Comparator<Turret>() {
                @Override
                public int compare (Turret o1, Turret o2) {
                    int cmp1 = o2.power - o1.power; // 공격 오름차순

                    if (cmp1 == 0) {
                        int cmp2 = o1.attackTurn - o2.attackTurn; // 공격 턴 내림차순
                        if (cmp2 == 0) {
                            int cmp3 = (o1.x + o1.y) - (o2.x + o2.y); // 행과 열의 합 오름차순
                            if (cmp3 == 0) {
                                int cmp4 = o1.y - o2.y; // 열 오름차순
                                return cmp4;
                            } else {
                                return cmp3;
                            }
                        } else {
                            return cmp2;
                        }
                    } else {
                        return cmp1;
                    }
                }
            });

            defender = turretList.get(0);
            defender.heal = false;

            // 레이저 공격을 먼저 시도하고, 만약 그게 안된다면 포탄 공격을 한다

            // 레이저 공격
            // 레이저는 상하좌우 4개의 방향으로 움직일 수 있다.
            // 부서진 포탑이 있는 위치는 지날 수 없다.
            // 가장자리에서 막힌 방향으로 진행하고자 한다면, 반대편으로 나온다.

            // 레이저 공격은 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 공격한다.
            // 만약 그런 경로가 없다면, 포탄 공격
            // 만약 공격 경로가 2개 이상이면, 우/하/좌/상 우선순위대로 먼저 움직인 경로가 선택된다.
            // 최단 경로가 정해졌으면, 공격 대상에는 공격자의 공격력 만큼의 피해를 입히며 피해를 입은 포탑은 해당 수치만큼 공격력이 줄어든다.
            // 공격 대상을 제외한 레이저 경로에 있는 포탑도 공격을 받게 되는데, 이 포탑은 절반 만큼 공격을 받는다.

            laserAttack = new ArrayList<>();
            visited = new boolean[N][M];
            laserAttack();

            if (laserAttack != null) {
                int half = attacker.power / 2;

                for (int i = 0; i < laserAttack.size() - 1; i++) {
                    Node node = laserAttack.get(i);

                    map[node.x][node.y].power -= half;
                    map[node.x][node.y].heal = false;

                    if (map[node.x][node.y].power < 0) map[node.x][node.y].power = 0;
                }

                Node node = laserAttack.get(laserAttack.size() - 1);

                map[node.x][node.y].power -= attacker.power;

                if (map[node.x][node.y].power < 0) map[node.x][node.y].power = 0;
            } else {
                // 포탄 공격
                // 공격 대상에 포탄을 던진다.
                // 공격 대상은 공격자 공격력 만큼의 피해를 받는다.
                // 추가적으로 주위 8개의 방향에 있는 포탑도 공격력의 절반 만큼 피해를 받는다.
                // 단, 공격자는 해당 공격에 영향을 받지 않는다.
                // 가장자리에 포탑이 떨어지면 추가 피해가 반대편 격자에 미치게 된다.
                cannon();
            }
            // 3. 포탑 부서짐
            // 공격을 받아 공격력이 0 이하가 된 포탑은 부서진다.

            // 4. 포탑 정비
            // 공격이 끝났으면, 부서서지지 않은 포탑 중 공격과 무관했던 포탑은 공격력이 1씩 올라간다.
            // 공격과 무관하다는 뜻은 공격자도 아니고, 공격에 피해를 입은 포탑도 아니라는 뜻

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j].power != 0 && map[i][j].heal == true) {
                        map[i][j].power++;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer = Math.max(answer, map[i][j].power);
            }
        }

        System.out.println(answer);

    }

    // 포탄 공격
    // 공격 대상에 포탄을 던진다.
    // 공격 대상은 공격자 공격력 만큼의 피해를 받는다.
    // 추가적으로 주위 8개의 방향에 있는 포탑도 공격력의 절반 만큼 피해를 받는다.
    // 단, 공격자는 해당 공격에 영향을 받지 않는다.
    // 가장자리에 포탑이 떨어지면 추가 피해가 반대편 격자에 미치게 된다.
    static void cannon() {
        defender.power -= attacker.power;
        if (defender.power < 0) {
            defender.power = 0;
        }

        int half = attacker.power / 2;

        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] dy = {1, 1, 0, -1, -1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nx = defender.x + dx[i];
            int ny = defender.y + dy[i];

            if (nx < 0) {
                nx += N;
            }

            if (nx >= N) {
                nx -= N;
            }

            if (ny < 0) {
                ny += M;
            }

            if (ny >= M) {
                ny -= M;
            }

            if(nx == attacker.x && ny == attacker.y) continue;

            map[nx][ny].power -= half;
            map[nx][ny].heal = false;
            if(map[nx][ny].power < 0) map[nx][ny].power = 0;
        }
    }


    // 레이저 공격
    // 레이저는 상하좌우 4개의 방향으로 움직일 수 있다.
    // 부서진 포탑이 있는 위치는 지날 수 없다.
    // 가장자리에서 막힌 방향으로 진행하고자 한다면, 반대편으로 나온다.

    // 레이저 공격은 공격자의 위치에서 공격 대상 포탑까지의 최단 경로로 공격한다.
    // 만약 그런 경로가 없다면, 포탄 공격
    // 만약 공격 경로가 2개 이상이면, 우/하/좌/상 우선순위대로 먼저 움직인 경로가 선택된다.
    // 최단 경로가 정해졌으면, 공격 대상에는 공격자의 공격력 만큼의 피해를 입히며 피해를 입은 포탑은 해당 수치만큼 공격력이 줄어든다.
    // 공격 대상을 제외한 레이저 경로에 있는 포탑도 공격을 받게 되는데, 이 포탑은 절반 만큼 공격을 받는다.
    static public void laserAttack() {
        ArrayList<Node>[][] route = new ArrayList[N][M];
        boolean[][] visited = new boolean[N][M];
        visited[attacker.x][attacker.y] = true;
        route[attacker.x][attacker.y] = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(attacker.x, attacker.y));

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0) {
                    nx += N;
                }

                if (nx >= N) {
                    nx -= N;
                }

                if (ny < 0) {
                    ny += M;
                }

                if (ny >= M) {
                    ny -= M;
                }

                if (map[nx][ny].power != 0 && visited[nx][ny] == false) {
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                    route[nx][ny] = new ArrayList<>();

                    for (Node n : route[node.x][node.y]) {
                        route[nx][ny].add(new Node(n.x, n.y));
                    }

                    route[nx][ny].add(new Node(nx, ny));

                    if(nx == defender.x && ny == defender.y) break;
                }
            }
        }

        laserAttack = route[defender.x][defender.y];

    }
}
