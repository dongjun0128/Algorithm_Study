package 백트레킹.P15649_N과M_3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/백트레킹/P15649_N과M_2/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            dfs(i, new ArrayList<>(), 1);
        }

        System.out.println(sb);
    }

    static void dfs(int now, ArrayList<Integer> numList, int depth) {
        // 1. 체크인
        numList.add(now);

        // 2. 목적지인가?
        if (depth == M) {
            for (int i = 0; i < numList.size(); i++) {
                sb.append(numList.get(i)).append(' ');
            }
            sb.append('\n');
        } else {
            // 3. 연결된 곳 순회

            for (int i = 1; i <= N; i++) {
                // 4. 갈 수 있는가?
                    // 5. 간다.
                dfs(i, numList, depth + 1);

            }
        }

        // 6. 체크아웃
        numList.remove(numList.size() - 1);
    }
}
