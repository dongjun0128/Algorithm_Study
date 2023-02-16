package 인덱스_트리.P10868_최솟값;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] maxTree;
    static long[] minTree;
    static long[] nums;
    static int S;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P2357_최솟값과_최댓값/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new long[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            nums[i] = Long.parseLong(st.nextToken());
        }

        S = 1;

        while (S < N) {
            S *= 2;
        }

        maxTree = new int[S * 2];
        minTree = new long[S * 2];

        for (int i = 0; i < S; i++) {
            if (i < N) {
                minTree[i + S] = nums[i];
            } else {
                minTree[i + S] = Long.MAX_VALUE;
            }
        }

        for (int i = S - 1; i > 0; i--) {
            minTree[i] = Math.min(minTree[i * 2], minTree[i * 2 + 1]);
        }

        //System.out.println(Arrays.toString(maxTree));
        //System.out.println(Arrays.toString(minTree));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(minQuery(a,b));
        }
    }

    static long minQuery(int leftQuery, int rightQuery){
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;

        long minLeft = Long.MAX_VALUE;
        long minRight = Long.MAX_VALUE;

        while(left <= right){
            if(left % 2 == 1){
                minLeft = Math.min(minLeft,minTree[left]);
                left++;
            }

            if(right % 2 == 0){
                minRight = Math.min(minRight,minTree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return Math.min(minLeft,minRight);
    }

    static int maxQuery(int leftQuery, int rightQuery){
        int left = leftQuery + S - 1;
        int right = rightQuery + S - 1;

        int maxLeft = 0;
        int maxRight = 0;

        while(left <= right){
            if(left % 2 == 1){
                maxLeft = Math.max(maxLeft,maxTree[left]);
                left++;
            }

            if(right % 2 == 0){
                maxRight = Math.max(maxRight,maxTree[right]);
                right--;
            }

            left /= 2;
            right /= 2;
        }

        return Math.max(maxLeft,maxRight);
    }
}
