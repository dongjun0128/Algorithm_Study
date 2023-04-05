package 삼성SW기출.P19236_청소년_상어;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int result = 0;
    static int dx[] = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int dy[] = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P19236_청소년_상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Fish[][] map;
        map = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = new Fish(num, direction);
            }
        }

        dfs(0, 0, new Fish(-1, 0), map, 0);

        System.out.println(result);
    }

    static void dfs(int x, int y, Fish shark, Fish[][] map, int sumNum) {
        Fish[][] tempMap = new Fish[4][4];
        Fish tempShark = new Fish(shark.num, shark.direction);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        // 1. 체크 인
        Fish eatenFish = tempMap[x][y];
        sumNum += eatenFish.num;
        tempShark.direction = eatenFish.direction;
        tempMap[x][y] = tempShark;
        tempMap = fishMove(tempMap);

        for (int i = 1; i < 10; i++) {
            int nx = x + dx[tempShark.direction] * i;
            int ny = y + dy[tempShark.direction] * i;

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) break;
            if (tempMap[nx][ny] != null) {
                tempMap[x][y] = null;
                dfs(nx, ny, tempShark, tempMap, sumNum);
                tempMap[x][y] = tempShark;
            }
        }

        result = Math.max(result, sumNum);

        // 2. 목적지인가?


        // 3. 연결된 곳 순회

        // 4. 갈 수 있는가?

        // 5. 간다.

        // 6. 체크 아웃

    }


    static Fish[][] fishMove(Fish[][] map) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j] != null && map[i][j].num != -1) {
                    queue.add(map[i][j].num);
                }
            }
        }

        while (!queue.isEmpty()) {
            int num = queue.poll();

            loop1:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j] != null && map[i][j].num == num) {
                        Fish fish = map[i][j];
                        int nx;
                        int ny;

                        for (int k = 0; k < 8; k++) {
                            int nextDirection = (fish.direction + k) % 8;
                            nx = i + dx[nextDirection];
                            ny = j + dy[nextDirection];

                            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                                fish.direction = nextDirection;
                                if (map[nx][ny] == null) {
                                    map[nx][ny] = fish;
                                    map[i][j] = null;
                                    break loop1;
                                } else {
                                    if (map[nx][ny].num != -1) {
                                        Fish temp = map[nx][ny];
                                        map[nx][ny] = fish;
                                        map[i][j] = temp;
                                        break loop1;
                                    }
                                }
                            }
                        }

                        break loop1;
                    }
                }
            }

        }

        return map;
    }

    static class Fish {
        int num;
        int direction;

        public Fish(int num, int direction) {
            this.num = num;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "num=" + num +
                    ", direction=" + direction +
                    '}';
        }
    }
}
