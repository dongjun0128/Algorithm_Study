package Level_3.네트워크;

import java.util.Arrays;

public class Main {
    static int[] parents;

    public static int solution(int n, int[][] computers) {
        int answer = 0;
        parents = new int[computers.length];

        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < computers.length; i++) {
            for (int j = 0; j < computers[i].length; j++) {
                if(computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }

        //System.out.println(Arrays.toString(parents));

        for (int i = 0; i < parents.length; i++) {
            if(parents[i] == i) answer++;
        }

        return answer;
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parents[rootB] = rootA;
    }

    public static int find(int n) {
        if(parents[n] == n) return n;

        return parents[n] = find(parents[n]);
    }

    public static void main(String[] args) {
        solution(3,new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
    }
}
