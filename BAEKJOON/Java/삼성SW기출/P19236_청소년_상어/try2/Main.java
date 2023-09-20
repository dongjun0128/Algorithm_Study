package 삼성SW기출.P19236_청소년_상어.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int answer = 0;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P19236_청소년_상어/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Fish[][] map = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = new Fish(a, b);
            }
        }

        int cnt = map[0][0].id;
        map[0][0].id = -1;

        dfs(map, cnt);

        System.out.println(answer);
    }

    // 물고기의 이동이 모두 끝나면 상어가 이동한다.
    // 상어는 방향에 있는 칸으로 이동할 수 있는데, 한 번에 여러 개의 칸을 이동할 수 있다.
    // 상어가 물고기가 있는 칸으로 이동했다면, 그 칸에 있는 물고기를 먹고 그 물고기의 방향을 가지게 된다.
    // 이동하는 중에 지나가는 칸에 있는 물고기는 먹지 않는다.
    // 물고기가 없는 칸으로는 이동할 수 없다.
    // 상어가 이동한 후에는 다시 물고기가 이동한다.

    // 상어가 먹을 수 있는 물고기 번호의 합의 최댓값을 구해보자
    static void dfs(Fish[][] map, int cnt) {
        fishMove(map);

        Loop1:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[i][j].id == -1) {  // 상어찾기
                    boolean findFish = false;
                    int num = 1;

                    while (true) {
                        int nx = i + dx[map[i][j].direction] * num;
                        int ny = j + dy[map[i][j].direction] * num;

                        if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4) {
                            if (map[nx][ny].id != 0) {
                                Fish[][] tempMap = copyMap(map);

                                tempMap[i][j].id = 0;
                                tempMap[i][j].direction = 0;

                                int tempCnt = cnt + tempMap[nx][ny].id;

                                tempMap[nx][ny].id = -1;

                                dfs(tempMap, tempCnt);
                            }

                        } else {
                            break;
                        }

                        num++;
                    }

                    if(findFish == false) {
                        answer = Math.max(answer, cnt);
                    }

                    break Loop1;
                }
            }
        }

    }

    static Fish[][] copyMap(Fish[][] map) {
        Fish[][] tempMap = new Fish[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempMap[i][j] = new Fish(map[i][j].id, map[i][j].direction);
            }
        }

        return tempMap;
    }


    // 물고기는 번호가 작은 물고기부터 순서대로 이동
    // 물고기는 한 칸을 이동할 수 있고, 이동할 수 있는 칸은 빈 칸과 다른 물고기가 있는 칸
    // 이동할 수 없는 칸은 상어가 있거나, 공간의 경계를 넘는 칸이다.
    // 각 물고기는 방향이 이동할 수 있는 칸을 향할 때 까지 방향을 45도 반시계 회전시킨다.
    // 이동할 수 있는 칸이 없으면 이동하지 않는다.
    // 물고기가 다른 물고기가 있는 칸으로 이동할 때는 서로의 위치를 바꾸는 방식으로 이동한다.
    static Fish[][] fishMove(Fish[][] map) {
        Loop1:
        for (int id = 1; id <= 16; id++) {

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j].id == id) {
                        for (int k = 0; k < 8; k++) {


                            int nx = i + dx[map[i][j].direction];
                            int ny = j + dy[map[i][j].direction];

                            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && map[nx][ny].id != -1) {
                                int tempId = map[nx][ny].id;
                                int tempDirection = map[nx][ny].direction;

                                map[nx][ny].id = map[i][j].id;
                                map[nx][ny].direction = map[i][j].direction;

                                map[i][j].id = tempId;
                                map[i][j].direction = tempDirection;

                                continue Loop1;
                            } else {
                                map[i][j].direction = (map[i][j].direction + 1) % 8;
                            }
                        }

                    }
                }
            }

        }

        return map;
    }
}

class Fish {
    int id;
    int direction;

    public Fish(int id, int direction) {
        this.id = id;
        this.direction = direction;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", direction=" + direction
                ;
    }
}
