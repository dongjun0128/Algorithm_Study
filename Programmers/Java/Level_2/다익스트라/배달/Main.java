package Level_2.다익스트라.배달;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        // 4
        System.out.println(solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        // 4
        System.out.println(solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }

    public static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
    // 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
    // 노드의 개수는 최대 100,000개라고 가정
    public static int n, m, start;
    // 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
    public static ArrayList<ArrayList<PriorityQueueNode>> graph = new ArrayList<ArrayList<PriorityQueueNode>>();
    // 최단 거리 테이블 만들기
    public static int[] d = new int[100001];

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        d = new int[N + 1];
        Arrays.fill(d, INF);

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            graph.get(a).add(new PriorityQueueNode(b, c));
            graph.get(b).add(new PriorityQueueNode(a, c));
        }

        dijkstra(1);


        for (int i = 0; i <= N; i++) {
            if(d[i] <= K) answer++;
        }

        return answer;
    }

    public static void dijkstra(int start) {
        PriorityQueue<PriorityQueueNode> pq = new PriorityQueue<>();
        // 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
        pq.offer(new PriorityQueueNode(start, 0));
        d[start] = 0;
        while(!pq.isEmpty()) { // 큐가 비어있지 않다면
            // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
            PriorityQueueNode node = pq.poll();
            int dist = node.getDistance(); // 현재 노드까지의 비용
            int now = node.getIndex(); // 현재 노드
            // 현재 노드가 이미 처리된 적이 있는 노드라면 무시
            if (d[now] < dist) continue;
            // 현재 노드와 연결된 다른 인접한 노드들을 확인
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = d[now] + graph.get(now).get(i).getDistance();
                // 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
                if (cost < d[graph.get(now).get(i).getIndex()]) {
                    d[graph.get(now).get(i).getIndex()] = cost;
                    pq.offer(new PriorityQueueNode(graph.get(now).get(i).getIndex(), cost));
                }
            }
        }
    }
}

class PriorityQueueNode implements Comparable<PriorityQueueNode> {

    private int index;
    private int distance;

    public PriorityQueueNode(int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    public int getIndex() {
        return this.index;
    }

    public int getDistance() {
        return this.distance;
    }

    // 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
    @Override
    public int compareTo(PriorityQueueNode other) {
        if (this.distance < other.distance) {
            return -1;
        }
        return 1;
    }
}