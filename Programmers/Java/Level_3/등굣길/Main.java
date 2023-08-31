package Level_3.등굣길;

public class Main {

    public static int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n][m];

        for (int i = 0; i < puddles.length; i++) {
            int x = puddles[i][0];
            int y = puddles[i][1];

            map[x - 1][y - 1] = -1;
        }

        map[0][0] = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(map[i][j] == -1) {
                    map[i][j] = 0;
                } else {
                    if(i != 0) {
                        map[i][j] += map[i - 1][j] % 1000000007;
                    }

                    if(j != 0) {
                        map[i][j] += map[i][j-1] % 1000000007;
                    }
                }
            }
        }

        return map[n - 1][m - 1] % 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(solution(4,3,new int[][] {{2,2}}));
    }
}
