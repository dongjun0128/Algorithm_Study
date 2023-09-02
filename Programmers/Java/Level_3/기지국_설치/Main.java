package Level_3.기지국_설치;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static int solution(int n, int[] stations, int w) {
        int answer = 0;
        boolean[] visited = new boolean[n + 1];
        List<List<Integer>> list = new ArrayList<>();

        for (int i = 0; i < stations.length; i++) {
            visited[stations[i]] = true;

            for (int j = stations[i]; j <= stations[i] + w; j++) {
                if(j <= n)
                    visited[j] = true;
            }

            for (int j = stations[i]; j >= stations[i] - w; j--) {
                if(j >= 0)
                    visited[j] = true;
            }
        }

        //System.out.println(Arrays.toString(visited));

        List<Integer> temp = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (visited[i] == false) {
                temp.add(i);
            } else {
                if(temp.size() != 0) list.add(temp);
                temp = new ArrayList<>();
            }

            if(i == n) {
                if(temp.size() != 0) list.add(temp);
            }
        }

        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {
            int start = list.get(i).get(0);
            int end = list.get(i).get(list.get(i).size() - 1);

            for (int j = start; j <= end; j++) {
                if (visited[j] == false) {
                    if (visited[j + w] == false) {
                        answer++;
                        for (int k = j + w; k <= j + w + w; k++) {
                            if (k <= n)
                                visited[k] = true;
                        }

                        for (int k = j + w; k >= j - w; k--) {
                            if (k >= 1)
                                visited[k] = true;
                        }
                    } else {
                        answer++;
                        for (int k = j; k <= j + w; k++) {
                            if (k <= n)
                                visited[k] = true;
                        }

                        for (int k = j; k >= j - w ; k--) {
                            if (k >= 1)
                                visited[k] = true;
                        }
                    }
                }

            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(16, new int[] {9}, 2));
        System.out.println(solution(11, new int[] {4,11}, 1));
    }
}
