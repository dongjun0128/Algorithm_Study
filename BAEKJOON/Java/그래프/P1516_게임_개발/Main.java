package 그래프.P1516_게임_개발;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] parents;
    static int N;
    static ArrayList<ArrayList<Integer>> graph;
    static int[] DAG;
    static int[] costs;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1516_게임_개발/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        parents = new int[N + 1];

        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        graph = new ArrayList<>();
        DAG = new int[N + 1];
        costs = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int index = 1; index <= N; index++) {
            st = new StringTokenizer(br.readLine());

            int cost = Integer.parseInt(st.nextToken());

            costs[index] = cost;

            while (true) {
                int next = Integer.parseInt(st.nextToken());

                if (next == -1) break;

                graph.get(next).add(index);
                DAG[index]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            if (DAG[i] == 0) {
                queue.add(i);
                result[i] = costs[i];
            }
        }


        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i);
                result[next] = Math.max(result[next], result[current] + costs[next]);
                DAG[next]--;
                if(DAG[next] == 0) queue.add(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            System.out.println(result[i]);
        }
    }
}
