package 최소신장트리.P1922_네트워크_연결;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/최소신장트리/P1922_네트워크_연결/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        int cost = 0;

        parents = new int[N + 1];

        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int a, b, c;

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            queue.add(new Node(a, b, c));
        }

        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (find(node.start) != find(node.end)) {
                cost += node.cost;
                union(node.start, node.end);
            }
        }

        System.out.println(cost);
    }

    public static void union(int a, int b) {
        int minVale = Math.min(a, b);
        int maxValue = Math.max(a, b);

        int rootMin = find(minVale);
        int rootMax = find(maxValue);

        parents[rootMax] = rootMin;
    }

    public static int find(int a) {
        if (parents[a] == a) return a;
        else return parents[a] = find(parents[a]);
    }
}

class Node implements Comparable<Node> {
    int start;
    int end;
    int cost;

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; // cost에 대한 오름차순
    }

    public Node(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

}