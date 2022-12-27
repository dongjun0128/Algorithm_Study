package Level_2.DFSBFS;

import org.testng.Assert;
import org.testng.annotations.Test;

public class 네트워크 {

    boolean[] check;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        check = new boolean[n]; // n 갯수만큼 boolean 배열을 만들고 모든 요소를 false로 초기화

        for (int i = 0; i < n; i++) {
            if (check[i] == false) { // 방문하지 않은 네트워크이면 dfs 수행
                dfs(computers, i);
                answer++;
            }
        }

        return answer;
    }

    public void dfs(int[][] computers, int i) {
        check[i] = true;

        for (int j = 0; j < computers.length; j++) {
            if (i != j && computers[i][j] == 1 && check[j] == false) {
                dfs(computers, j);
            }
        }
    }

    @Test
    public void 정답() {
        Assert.assertEquals(2, solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        Assert.assertEquals(1, solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }
}
