package 깊이우선탐색_넓이우선탐색.P2644_촌수계산;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int A, B;
    static List<List<Integer>> graph;
    static int answer = 0;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P2644_촌수계산/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for (int i = 0; i < graph.get(A).size(); i++) {
            dfs(graph.get(A).get(i), 1);
        }

        System.out.println(answer == 0 ? -1 : answer);
    }

    public static void dfs(int index, int depth) {
        visited[index] = true;

        if (index == B) {
            answer = depth;
            return;
        } else {
            for (int i = 0; i < graph.get(index).size(); i++) {
                if(visited[graph.get(index).get(i)] == false)
                    dfs(graph.get(index).get(i), depth + 1);
            }
        }

        visited[index] = false;
    }
}
