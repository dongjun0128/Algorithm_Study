package 이분탐색.P2805_나무_자르기;

import java.io.*;
import java.util.*;

public class Main {
    static int N,M;
    static int[] trees;
    static long temp;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/이분탐색/P2805_나무_자르기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        trees = new int[N];
        int maxTree = 0;

        for (int n = 0; n < N; n++) {
            trees[n] = Integer.parseInt(st.nextToken());
            maxTree = Math.max(maxTree, trees[n]);
        }

        System.out.println(binarySearch(0,maxTree));

    }

    public static long binarySearch(int start, int end) {
        if(start > end) return temp;

        int mid = (start + end) / 2;

        if(treeCut(mid) == M) {
            return mid;
        } else if (treeCut(mid) > M) {
            temp =Math.max(temp,mid);
            return binarySearch(mid + 1, end);
        } else {
            return binarySearch(start, mid - 1);
        }
    }

    public static long treeCut(int num) {
        long sum = 0;

        for(int tree : trees) {
            if(num < tree) {
                sum += tree - num;
            }
        }

        return sum;
    }
}
