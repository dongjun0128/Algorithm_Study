package 그래프.P1922_네크워크_연결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Node> graph;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1922_네크워크_연결/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        parents = new int[N + 1];
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int i = 0; i <= N; i++) {
            parents[i] = i;
        }

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.add(new Node(a, b, c));
            queue.add(new Node(a, b, c));
        }

        int totalCost = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            int start = node.start;
            int end = node.end;
            int cost = node.cost;

            if (find(start) != find(end)) { // 두 개가 이어져 있지 않다면
                union(start,end);
                totalCost+=cost;
            }
        }

        System.out.println(totalCost);

    }

    static void union(int a, int b) {
        int minValue = Math.min(a, b);
        int maxValue = Math.max(a, b);

        int rootMin = find(minValue);
        int rootMax = find(maxValue);

        parents[rootMax] = rootMin;
    }

    static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }

}

class Node implements Comparable<Node> {
    int start;
    int end;
    int cost;

    public Node(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; //cost에 대해서 오름차순
    }
}
