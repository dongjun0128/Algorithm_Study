package 인덱스_트리.P2517_달리기;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Runner> runnerList;
    static int S;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P2517_달리기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        runnerList = new ArrayList<>(N);

        for (int n = 0; n < N; n++) {
            runnerList.add(new Runner(n + 1, Integer.parseInt(new StringTokenizer(br.readLine()).nextToken())));
        }

        // 압축
        Collections.sort(runnerList, (a, b) -> a.skill - b.skill); //skill에 대해 오름차순
        for (int i = 0; i < N; i++) {
            runnerList.get(i).skill = i + 1;
        }
        Collections.sort(runnerList, (a, b) -> a.index - b.index);

        //System.out.println(runnerList);

        init();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            Runner runner = runnerList.get(i);
            sb.append(runner.index - query(1, runner.skill) + "\n");
            update(runner.skill);
        }
        System.out.println(sb.toString());
    }

    public static void update(int index) {
        index = S + index - 1;

        tree[index]++;
        index /= 2;

        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index /= 2;
        }
    }

    public static int query(int leftQuery, int rightQuery) {
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;

        int rank = 0;

        while (left <= right) {
            if (left % 2 == 1) {
                rank += tree[left];
                left++;
            }

            if (right % 2 == 0) {
                rank += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return rank;
    }

    public static void init() {
        S = 1;

        while (S < N) {
            S *= 2;
        }

        tree = new int[S * 2];
    }
}

class Runner {
    int index;
    int skill;

    public Runner(int index, int skill) {
        this.index = index;
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Runner{" +
                "index=" + index +
                ", skill=" + skill +
                '}';
    }
}