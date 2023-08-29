package 그래프.P1753_최단경로.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<List<Node>> graph;
    static int V, E;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/그래프/P1753_최단경로/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        int start = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dijkstra(start);

        //System.out.println(Arrays.toString(distance));

        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(distance[i]);
            }
        }
    }

    static void dijkstra(int start) {
        distance = new int[V + 1];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        priorityQueue.add(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();

            if(distance[node.index] < node.cost) continue;

            for (int i = 0; i < graph.get(node.index).size(); i++) {
                int dist = node.cost + graph.get(node.index).get(i).cost;

                if (distance[graph.get(node.index).get(i).index] > dist) {
                    distance[graph.get(node.index).get(i).index] = dist;
                    priorityQueue.add(new Node(graph.get(node.index).get(i).index, dist));
                }

            }
        }
    }
}

class Node implements Comparable<Node> {

    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }


    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}