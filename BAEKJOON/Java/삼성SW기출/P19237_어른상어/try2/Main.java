package 삼성SW기출.P19237_어른상어.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static int N, M, K;

    static Smell[][] map;
    static ArrayList<Shark> sharkArrayList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P19237_어른상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new Smell[N][N];

        for (int i = 0; i <= M; i++) {
            sharkArrayList.add(new Shark(0, 0, i, 0, new ArrayList<>(), true));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());

                if (num == 0) {
                    map[i][j] = new Smell(0, 0);
                } else {
                    map[i][j] = new Smell(0, 0);
                    sharkArrayList.get(num).x = i;
                    sharkArrayList.get(num).y = j;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= M; i++) {
            int direction = Integer.parseInt(st.nextToken()) - 1;

            sharkArrayList.get(i).direction = direction;
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());

                ArrayList<Integer> directionList = new ArrayList<>();

                for (int k = 0; k < 4; k++) {
                    directionList.add(Integer.parseInt(st.nextToken()) - 1);
                }

                sharkArrayList.get(i).priorityDirection.add(directionList);
            }
        }


        // 1의 번호를 가진 상어가 가장 강력해서 나머지를 모두 쫓아낼 수 있다.

        // 맨 처음에는 모든 상어가 자신의 위치에 자신의 냄새를 뿌린다.
        // 그 후 1초마다 모든 상어가 동시에 상하좌우로 인접한 칸 중 하나로 이동하고 자신의 냄새를 그 칸에 뿌린다.
        // 냄새는 상어가 K번 이동하고 나면 사라진다.

        makeSmell();

        int T = 0;

        while (true) {
            T++;
            sharksMove();
            takeTimeSmell();
            makeSmell();

            if (check() == true) {
                break;
            }

            if(T >= 1000) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(T);
    }

    static boolean check() {

        for (int i = 2; i <= M; i++) {
            if (sharkArrayList.get(i).isAlive == true) {
                return false;
            }
        }

        return true;
    }

    // 각 상어가 이동 방향을 결정할 때는, 먼저 인접한 칸 중 아무 냄새가 없는 칸의 방향으로 잡는다.
    // 그런 칸이 없으면 자신의 냄새가 있는 칸의 방향으로 잡는다.
    // 이 때 가능한 칸이 여러개일 수 있는데, 그 경우에는 특정한 우선순위를 따른다.
    // 상어가 맨 처음에 보고 있는 방향은 입력으로 주어지고, 그 후에는 방금 이동한 방향이 보고 있는 방방향이 된다.

    // 모든 상어가 이동한 후 한 칸에 여러 마리의 상어가 남아 있으면, 가장 작은 번호를 가진 상어를 제외하고 모두 격자 밖으로 쫓겨난다.

    static void sharksMove() {
        Loop1 :
        for (int i = 1; i <= M; i++) {
            Shark shark = sharkArrayList.get(i);

            // 살아있는 상어만 움직인다.
            if(shark.isAlive == true) {
                int nowDirection = shark.direction; // 상어의 현재 방향
                boolean isSharkMove = false; // 상어가 움직였는지 확인하는 변수

                // 4방향 중 갈 수 있는 곳 검사
                for (int j = 0; j < 4; j++) {
                    int nextDirection = shark.priorityDirection.get(nowDirection).get(j);

                    int nx = shark.x + dx[nextDirection];
                    int ny = shark.y + dy[nextDirection];

                    // 향이 없는 곳 찾기
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny].id == 0) {
                        // 이미 나보다 높은 숫자의 상어가 와있으면 나가기
                        for (int k = 1; k <= i; k++) {
                            if (nx == sharkArrayList.get(k).x && ny == sharkArrayList.get(k).y) {
                                shark.x = -1;
                                shark.y = -1;
                                shark.isAlive = false;
                                continue Loop1;
                            }
                        }

                        // 없으면 움직이기
                        shark.x = nx;
                        shark.y = ny;
                        shark.direction = nextDirection;

                        isSharkMove = true;
                        break;
                    }
                }

                // 칸이 없으므로 자신의 냄새가 있는 칸의 방향으로 간다.
                if (isSharkMove == false) {
                    for (int j = 0; j < 4; j++) {
                        int nextDirection = shark.priorityDirection.get(nowDirection).get(j);
                        int nx = shark.x + dx[nextDirection];
                        int ny = shark.y + dy[nextDirection];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny].id == shark.id) {
                            shark.x = nx;
                            shark.y = ny;
                            shark.direction = nextDirection;
                            break;
                        }
                    }
                }
            }
        }
    }

    static void takeTimeSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].id != 0) {
                    map[i][j].time--;

                    if (map[i][j].time == 0) {
                        map[i][j].id = 0;
                    }
                }
            }
        }
    }

    static void makeSmell() {
        for (int i = 1; i <= M; i++) {
            Shark shark = sharkArrayList.get(i);

            if(shark.isAlive == true) {
                map[shark.x][shark.y].id = i;
                map[shark.x][shark.y].time = K;
            }
        }
    }


}

class Smell {
    int id;
    int time;

    public Smell(int id, int time) {
        this.id = id;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Smell{" +
                "id=" + id +
                ", time=" + time +
                '}';
    }
}

class Shark {
    int x;
    int y;
    int id;
    int direction;
    ArrayList<ArrayList<Integer>> priorityDirection;
    boolean isAlive;

    @Override
    public String toString() {
        return "Shark{" +
                "x=" + x +
                ", y=" + y +
                ", id=" + id +
                ", direction=" + direction +
                ", priorityDirection=" + priorityDirection +
                ", isAlive=" + isAlive +
                '}';
    }

    public Shark(int x, int y, int id, int direction, ArrayList<ArrayList<Integer>> priorityDirection, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.direction = direction;
        this.priorityDirection = priorityDirection;
        this.isAlive = isAlive;
    }
}
