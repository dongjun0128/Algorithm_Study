package 깊이우선탐색_넓이우선탐색.P1260_DFS_BFS;

import java.io.*;
import java.util.*;

public class Main {
    static int N,M, V;
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/깊이우선탐색_넓이우선탐색/P1260_DFS_BFS/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 0; i <= N; i++) {
            Collections.sort(graph.get(i));
        }

        visited = new boolean[N + 1];

        dfs(V);
        System.out.println();

        Queue<Integer> queue = new LinkedList<>();
        queue.add(V);
        visited = new boolean[N + 1];
        visited[V] = true;


        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");

            for (int i = 0; i < graph.get(now).size(); i++) {
                if(visited[graph.get(now).get(i)] == false) {
                    visited[graph.get(now).get(i)] = true;
                    queue.add(graph.get(now).get(i));
                }
            }
        }

    }

    public static void dfs(int index) {
        if(visited[index] == false) {
            visited[index] = true;
            System.out.print(index + " ");

            for (int i = 0; i < graph.get(index).size(); i++) {
                dfs(graph.get(index).get(i));
            }

        }
    }
}
