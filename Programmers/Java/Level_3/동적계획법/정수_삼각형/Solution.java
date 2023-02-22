package Level_3.동적계획법.정수_삼각형;

public class Solution {
    public static void main(String[] args) {
        int[][] triangle = {{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};

        System.out.println(solution(triangle));
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[501][501];

        dp[1][1] = triangle[0][0];

        for (int i = 2; i <= triangle.length; i++) {
            for (int j = 1; j <= triangle[i - 1].length; j++) {
                dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i - 1][j - 1], dp[i - 1][j] + triangle[i - 1][j - 1]);
            }
        }

        int lastTriangleIndex = triangle.length;

        for (int i = 0; i < triangle[lastTriangleIndex - 1].length; i++) {
            answer = Math.max(answer, dp[lastTriangleIndex][i]);
        }

        return answer;
    }
}
