package Level_3.섬_연결하기;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static int[] parents;
    static List<List<Node>> graph = new ArrayList<>();
    static PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

    public static int solution(int n, int[][] costs) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        parents = new int[n];

        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < costs.length; i++) {
            int start = costs[i][0];
            int end = costs[i][1];
            int cost = costs[i][2];

            graph.get(start).add(new Node(start, end, cost));
            priorityQueue.add(new Node(start, end, cost));
        }

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            int start = node.start;
            int end = node.end;
            int cost = node.cost;

            if (find(start) != find(end)) {
                union(start, end);
                answer += cost;
            }


        }

        return answer;
    }

    public static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);

        parents[rootB] = rootA;
    }

    public static int find(int a) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static class Node implements Comparable<Node> {
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
            return this.cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "start=" + start +
                    ", end=" + end +
                    ", cost=" + cost +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(4, new int[][]{{0, 1, 1}, {0, 2, 2}, {1, 2, 5}, {1, 3, 1}, {2, 3, 8}}));
    }
}


