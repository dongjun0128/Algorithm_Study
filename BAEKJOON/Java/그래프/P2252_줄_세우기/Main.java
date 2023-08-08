package 그래프.P2252_줄_세우기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] DAG;
    static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P2252_줄_세우기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DAG = new int[N + 1];

        graph = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            DAG[b]++;
        }


        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < N + 1; i++) {
            if (DAG[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int index = queue.poll();

            System.out.print(index + " ");

            for (int i = 0; i < graph.get(index).size(); i++) {
                DAG[graph.get(index).get(i)]--;
                if (DAG[graph.get(index).get(i)] == 0) queue.add(graph.get(index).get(i));
            }
        }

    }
}
