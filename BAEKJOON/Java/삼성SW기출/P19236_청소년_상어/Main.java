package 삼성SW기출.P19236_청소년_상어;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Fish implements Comparable<Fish> {
        int x, y, id, direction;
        boolean isAlive;

        public Fish(int x, int y, int id, int direction, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.direction = direction;
            this.isAlive = isAlive;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "x=" + x +
                    ", y=" + y +
                    ", id=" + id +
                    ", direction=" + direction +
                    ", isAlive=" + isAlive +
                    '}';
        }

        @Override
        public int compareTo(Fish o) {
            return id - o.id; // id순으로 오름차순
        }
    }

    static class Shark {
        int x, y, direction, eatSum;

        public Shark(int x, int y, int direction, int eatSum) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.eatSum = eatSum;
        }

        @Override
        public String toString() {
            return "Shark{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    ", eatSum=" + eatSum +
                    '}';
        }
    }

    static int result = 0;
    static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P19236_청소년_상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[][] map;
        ArrayList<Fish> fishes = new ArrayList<>();
        map = new int[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = num;
                fishes.add(new Fish(i, j, num, direction, true));
            }
        }

        Collections.sort(fishes);

        Fish eatenFish = fishes.get(map[0][0] - 1);
        map[0][0] = -1;
        eatenFish.isAlive = false;
        Shark shark = new Shark(0, 0, eatenFish.direction, eatenFish.id);

        dfs(map, fishes, shark);

        System.out.println(result);
    }

    static void dfs(int[][] map, ArrayList<Fish> fishes, Shark shark) {
        result = Math.max(result, shark.eatSum);

        fishesMove(map, fishes);

        for (int i = 1; i < 4; i++) {
            int nx = shark.x + dx[shark.direction] * i;
            int ny = shark.y + dy[shark.direction] * i;

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny] != 0) {
                int[][] tempMap = new int[4][4];

                for (int j = 0; j < 4; j++) {
                    for (int k = 0; k < 4; k++) {
                        tempMap[j][k] = map[j][k];
                    }
                }

                ArrayList<Fish> tempFishes = new ArrayList<>();

                for (Fish fish : fishes) {
                    tempFishes.add(new Fish(fish.x, fish.y, fish.id, fish.direction, fish.isAlive));
                }

                Fish eatenFish = tempFishes.get(tempMap[nx][ny] - 1);

                tempMap[shark.x][shark.y] = 0;
                tempMap[nx][ny] = -1;
                eatenFish.isAlive = false;

                Shark tempShark = new Shark(nx, ny, eatenFish.direction, shark.eatSum + eatenFish.id);

                dfs(tempMap,tempFishes,tempShark);
            }
        }


    }

    static void fishesMove(int[][] map, ArrayList<Fish> fishes) {
        for (Fish fish : fishes) {
            if (fish.isAlive == true) {

                for (int i = 0; i < 8; i++) {
                    int nextDirection = (fish.direction + i) % 8;

                    int nx = fish.x + dx[nextDirection];
                    int ny = fish.y + dy[nextDirection];

                    if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny] != -1) {
                        map[fish.x][fish.y] = 0;

                        if (map[nx][ny] == 0) {
                            fish.x = nx;
                            fish.y = ny;
                        } else {
                            Fish temp = fishes.get(map[nx][ny] - 1);
                            temp.x = fish.x;
                            temp.y = fish.y;
                            map[fish.x][fish.y] = temp.id;

                            fish.x = nx;
                            fish.y = ny;
                        }

                        map[nx][ny] = fish.id;
                        fish.direction = nextDirection;

                        break;
                    }
                }
            }
        }
    }
}
