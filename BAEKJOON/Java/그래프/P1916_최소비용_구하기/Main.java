package 그래프.P1916_최소비용_구하기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V, E;
    static List<List<Node>> graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1916_최소비용_구하기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        graph = new ArrayList<>();
        distance = new int[V + 1];

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

        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        dijkstra(a);

        System.out.println(distance[b]);
    }

    public static void dijkstra(int start) {
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int now = node.index;
            int cost = node.cost;

            if(distance[now] < cost) continue;

            for (int i = 0; i < graph.get(now).size(); i++) {
                int dist = graph.get(now).get(i).cost + cost;

                if(distance[graph.get(now).get(i).index] > dist) {
                    distance[graph.get(now).get(i).index] = dist;
                    pq.add(new Node(graph.get(now).get(i).index, dist));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost; // cost에 대한 오름차순
    }
}