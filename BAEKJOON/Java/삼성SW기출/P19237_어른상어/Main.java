package 삼성SW기출.P19237_어른상어;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static class Smell {
        int time;
        int id;

        public Smell(int time, int id) {
            this.time = time;
            this.id = id;
        }
    }

    static class Shark {
        int x;
        int y;
        int direction;
        int id;
        boolean isAlive;

        public Shark(int x, int y, int direction, int id, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.id = id;
            this.isAlive = isAlive;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }
    }

    static int[][] map;
    static Smell[][] smells;
    static int N, M, K;
    static ArrayList<Shark> sharks = new ArrayList<>();
    static ArrayList<ArrayList<ArrayList<Integer>>> priorityDirection;
    // 위 아래 왼 오
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P19237_어른상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        smells = new Smell[N][N];
        sharks = new ArrayList<>();

        for (int i = 0; i <= M; i++) {
            sharks.add(new Shark(-1, -1, -1, i, false));
        }


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    sharks.get(map[i][j]).x = i;
                    sharks.get(map[i][j]).y = j;
                    sharks.get(map[i][j]).isAlive = true;
                }
            }
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= M; i++) {
            sharks.get(i).direction = Integer.parseInt(st.nextToken()) - 1;
        }

        priorityDirection = new ArrayList<>();

        for (int i = 0; i <= M; i++) {
            priorityDirection.add(new ArrayList<>());
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                ArrayList<Integer> directions = new ArrayList<>();
                for (int k = 0; k < 4; k++) {
                    directions.add(Integer.parseInt(st.nextToken()) - 1);
                }
                priorityDirection.get(i).add(directions);
            }
        }

        makeSmell();

        for (int t = 1; t <= 1000; t++) {
            move();
            removeSmell();
            makeSmell();
            if(check() == true) {
                System.out.println(t);
                return;
            }
        }

        System.out.println(-1);
    }

    static void move() {
        int[][] tempMap = new int[N][N];

        for (Shark shark : sharks) {
            if (shark.isAlive) {
                ArrayList<Integer> directionList = priorityDirection.get(shark.id).get(shark.direction);
                boolean isMove = false;

                // 냄새가 없는 칸 발견하면 움직이기
                for (int nextDirection : directionList) {
                    int nx = shark.x + dx[nextDirection];
                    int ny = shark.y + dy[nextDirection];

                    if (nx >= 0 && nx < N && ny >= 0 && ny < N && smells[nx][ny] == null) {
                        if (tempMap[nx][ny] == 0) {
                            shark.x = nx;
                            shark.y = ny;
                            shark.direction = nextDirection;
                            tempMap[nx][ny] = shark.id;
                        } else {
                            shark.isAlive = false;
                        }
                        isMove = true;
                        break;
                    }
                }

                // 주변에 냄새가 없는 칸이 없어서 자기 냄새가 난 칸으로 이동
                if(isMove == false) {
                    for (int nextDirection : directionList) {
                        int nx = shark.x + dx[nextDirection];
                        int ny = shark.y + dy[nextDirection];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N && smells[nx][ny] != null && smells[nx][ny].id == shark.id) {
                            tempMap[nx][ny] = shark.id;
                            shark.x = nx;
                            shark.y = ny;
                            shark.direction = nextDirection;
                            break;
                        }
                    }
                }


            }
        }

        map = tempMap;
    }

    static void removeSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(smells[i][j] != null) {
                    smells[i][j].time--;
                    if(smells[i][j].time == 0) {
                        smells[i][j] = null;
                    }
                }
            }
        }
    }

    static void makeSmell() {
        for (Shark shark : sharks) {
            if (shark.isAlive == true) {
                smells[shark.x][shark.y] = new Smell(K, shark.id);
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == 0) continue;
                if(map[i][j] == 1) continue;
                return false;
            }
        }

        return true;
    }
}
