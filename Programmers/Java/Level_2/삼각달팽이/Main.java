package Level_2.삼각달팽이;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int[] dx = {1, 0, -1};
    static int[] dy = {0, 1, -1};

    public static int[] solution(int n) {
        int[] answer = {};
        int[][] map = new int[n][n];
        int num = 1;
        int direction = 0;
        int x = 0;
        int y = 0;

        if(n == 1) return new int[] {1};
        if(n == 2) return new int[] {1,2,3};
        if(n == 3) return new int[]{1, 2, 6, 3, 4, 5};

        while (true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                direction = (direction + 1) % 3;
                continue;
            }

            if (map[nx][ny] != 0) {

                if(map[x + dx[(direction + 1)% 3]][y + dy[(direction + 1) % 3]] != 0 && map[x + dx[(direction + 2)% 3]][y + dy[(direction + 2) % 3]] != 0) {
                    map[x][y] = num++;
                    break;
                }

                direction = (direction + 1) % 3;
                continue;
            }

            map[x][y] = num++;
            x = nx;
            y = ny;
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(map[i]));
        }

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(map[i][j] == 0) break;
                list.add(map[i][j]);
            }
        }

        answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(3));
    }
}
