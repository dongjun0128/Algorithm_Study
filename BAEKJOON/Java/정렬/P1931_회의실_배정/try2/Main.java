package 정렬.P1931_회의실_배정.try2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static List<Node> classTimeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/정렬/P1931_회의실_배정/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        classTimeList = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            classTimeList.add(new Node(start, end));
        }

        //Collections.sort(classTimeList);
        classTimeList.sort((Node o1 , Node o2) -> {
            int cmp1 = o1.end - o2.end;
            if(cmp1 == 0) return o1.start - o2.start;
            return cmp1;
        });

        int start = 0;
        int end = 0;
        int answer = 0;

        for (Node classTime : classTimeList) {
            if(classTime.start >= end) {
                answer++;
                start = classTime.start;
                end = classTime.end;
            }
        }

        System.out.println(answer);
    }
}

class Node implements Comparable<Node> {
    int start;
    int end;

    public Node(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Node o) {
        int cmp1 = this.end - o.end; // end 에 대해 오름차순

        if(cmp1 == 0) {
            return this.start - o.start; // start 에 대해 오름차순
        } else {
            return cmp1;
        }
    }
}
