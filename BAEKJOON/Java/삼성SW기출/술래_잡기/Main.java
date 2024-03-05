package 삼성SW기출.술래_잡기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Tagger {
        int x;
        int y;
        int d;
        int moveCnt;
        int cnt;
        boolean goOut;

        public Tagger(int x, int y) {
            this.x = x;
            this.y = y;
            this.d = 0;
            this.cnt = 1;
            moveCnt = 0;
            goOut = true;
        }

        public void move() {
            x = x + dx[d];
            y = y + dy[d];

            if (goOut == true && x == 0 && y == 0) {
                d = 2;
                moveCnt = 1;
                goOut = false;
                return;
            } else if (goOut == false && x == N / 2 && y == N / 2) {
                d = 0;
                moveCnt = 0;
                goOut = true;
                return;
            }

            moveCnt++;

            if (goOut == true) {
                if (moveCnt == cnt) {
                    moveCnt = 0;
                    d = (d + 1) % 4;

                    if (d % 2 == 0) {
                        cnt++;
                    }
                }
            } else {
                if (moveCnt == cnt) {
                    moveCnt = 0;

                    d--;
                    if (d < 0) {
                        d += 4;
                    }

                    if (d % 2 == 1) {
                        cnt--;
                    }
                }
            }

        }
    }

    static class Runner {
        int x;
        int y;
        int d;

        public Runner(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        // 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직인다.
        // |x1 - x2| + |y1 - y2|

        // 술래와의 거리가 3 이하인 도망자들은 1턴 동안 움직인다.
        // 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
        // 움직이려는 칸에 술래가 있는 경우라면 움직이지 않는다.
        // 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다. 나무가 있어도 상관없다.
        // 격자를 벗어나면 방향을 반대로 틀어준다.
        // 이후 바라보고 있는 방향으로 술래가 없다면 1칸 앞으로 움직인다.

        public void move() {
            int distance = Math.abs(tagger.x - this.x) + Math.abs(tagger.y - this.y);

            if (distance <= 3) {
                int nx = this.x + dx[d];
                int ny = this.y + dy[d];
                // 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    // 움직이려는 칸에 술래가 있는 경우라면 움직이지 않는다.
                    if (nx == tagger.x && ny == tagger.y) {

                    } else {
                        this.x = nx;
                        this.y = ny;
                    }
                    // 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다. 나무가 있어도 상관없다.

                } else { // 격자를 벗어나면 방향을 반대로 틀어준다.
                    // 이후 바라보고 있는 방향으로 술래가 없다면 1칸 앞으로 움직인다.
                    d = (d + 2) % 4;

                    nx = this.x + dx[d];
                    ny = this.y + dy[d];

                    if (nx == tagger.x && ny == tagger.y) {

                    } else {
                        this.x = nx;
                        this.y = ny;
                    }
                }
            }
        }
    }


    static int N, M;
    static int H, K;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] map;
    static ArrayList<Runner> runners = new ArrayList<>();
    static Tagger tagger;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/술래_잡기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // M 명의 도망자가 있다.
        // 도망자는 처음 지정된 곳에 서 있다.
        // 도망자의 종류는 좌우로만 움직이는 유형과 상하로만 움직이는 유형 이렇게 2가지가 있다.
        // 이 떄 좌오로 움직이는 사람은 항상 오른쪽을 보고 시작,
        // 상하로 움직이는 사람은 항상 아래쪽을 보고 시작

        // 이 게임에는 h개의 나무가 있다.
        // m명의 도망자가 먼저 동시에 움직이고,
        // 술래가 움직인다.
        // 이를 총 K번 반복
        // 도망자가 움직일 때 현재 술래와의 거리가 3 이하인 도망자만 움직인다.
        // |x1 - x2| + |y1 - y2|

        // 술래와의 거리가 3 이하인 도망자들은 1턴 동안 움직인다.
        // 현재 바라보고 있는 방향으로 1칸 움직인다 했을 때 격자를 벗어나지 않는 경우
        // 움직이려는 칸에 술래가 있는 경우라면 움직이지 않는다.
        // 움직이려는 칸에 술래가 있지 않다면 해당 칸으로 이동한다. 나무가 있어도 상관없다.
        // 격자를 벗어나면 방향을 반대로 틀어준다.
        // 이후 바라보고 있는 방향으로 술래가 없다면 1칸 앞으로 움직인다.

        // 술래가 움직이는 경우
        // 위 방향으로 시작하여 달팽이 모양으로 움직인다.
        // 만약 끝에 도달하게 되면 다시 거꾸로 중심으로 이동한다.

        // 술래는 1번의 턴 동안 정확히 한 칸 해당하는 방향으로 이동하게 된다.
        // 만약 이동방향이 틀어지는 지점이라면, 방향을 바로 틀어준다.
        // 만약 이동을 통해 양끝에 해당하는 위치 (0,0) 혹은 정 중앙에 도달하게 된다면 이 경우 방향을 바로 틀어준다.

        // 이동 직후 술래는 턴을 넘기기 전에 시야 내에 있는 도망자를 잡게 된다.
        // 술래의 시야는 현재 바라보고 있는 방향을 기준으로 현재 칸을 포함하여 총 3칸이다.

        // 만약 나무가 놓여 있는 칸이라면, 해당 칸에 있는 도망자는 나무에 가려져 보이지 않게 된다.

        // 술래는 t번째 턴이라고 했을 떄 t * 잡은 도망자의 수 만큼 점수를 얻는다.

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tagger = new Tagger(N / 2, N / 2);

        map = new int[N][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            runners.add(new Runner(x, y, d));
        }

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;

            map[x][y] = 1;
        }

        int answer = 0;

        for (int k = 1; k <= K; k++) {
            for (Runner runner : runners) {
                runner.move();
            }

            tagger.move();

            ArrayList<Runner> tempRunners = new ArrayList<>();

            for (int i = 0; i < runners.size(); i++) {
                Runner runner = runners.get(i);

                if (check(runner)) { // 술래의 시야에 걸림
                    if (map[runner.x][runner.y] == 1) { // 나무 칸에 있으면 산다.
                        tempRunners.add(runners.get(i));
                    } else {
                        answer += k;
                    }
                } else {
                    tempRunners.add(runners.get(i));
                }
            }

            runners = tempRunners;
        }

        System.out.println(answer);
    }

    static boolean check(Runner runner) {
        int nx1 = tagger.x + (dx[tagger.d]);
        int ny1 = tagger.y + (dy[tagger.d]);

        int nx2 = tagger.x + (dx[tagger.d] * 2);
        int ny2 = tagger.y + (dy[tagger.d] * 2);

        if(runner.x == tagger.x && runner.y == tagger.y) return true;
        if(runner.x == nx1 && runner.y == ny1) return true;
        if(runner.x == nx2 && runner.y == ny2) return true;

        return false;
    }
}
