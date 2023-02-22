package Level_3.동적계획법.등굣길;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    static final int MOD = 1000000007;

    public static void main(String[] args) {
        System.out.println(solution(4, 3, new int[][]{{2, 2}}));
        System.out.println(solution(4, 4, new int[][]{{3, 2}, {2, 4}}));
        System.out.println(solution(5, 3, new int[][]{{4, 2}}));
        System.out.println(solution(2, 2, new int[][]{{2, 1}, {1, 2}}));
        System.out.println(solution(3, 1, new int[][]{{2, 1}}));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        // 웅덩이는 -1
        for (int[] puddle : puddles)
            map[puddle[1] - 1][puddle[0] - 1] = -1;

        map[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == -1) {
                    map[i][j] = 0;
                } else {
                    if (i != 0) map[i][j] += map[i - 1][j] % MOD;
                    if (j != 0) map[i][j] += map[i][j - 1] % MOD;

                }
            }
        }

        return map[n - 1][m - 1] % 1000000007;
    }
}
