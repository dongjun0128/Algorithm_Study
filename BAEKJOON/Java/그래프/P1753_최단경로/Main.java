package 그래프.P1753_최단경로;

import java.io.*;
import java.util.*;

public class Main {
    static int V, E;
    static List<List<Node>> graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1753_최단경로/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int s = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        distance = new int[V + 1];

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph.get(start).add(new Node(end, cost));
        }

        Arrays.fill(distance, Integer.MAX_VALUE);
        dijkstra(s);

        for (int i = 1; i <= V; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    public static void dijkstra(int start) {
        distance[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(distance[node.index] > node.distance) continue;

            for (int i = 0; i < graph.get(node.index).size(); i++) {
                int dist = node.distance + graph.get(node.index).get(i).distance;

                if(distance[graph.get(node.index).get(i).index] > dist) {
                    distance[graph.get(node.index).get(i).index] = dist;
                    pq.add(new Node(graph.get(node.index).get(i).index, dist));
                }
            }
        }

    }

}

class Node implements Comparable<Node> {
    int index;
    int distance;

    public Node(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return this.distance - o.distance; // cost에 대해 오름차순
    }

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", distance=" + distance +
                '}';
    }
}