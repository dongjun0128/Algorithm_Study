package 이진탐색;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class 떡볶이_떡_만들기 {
    static int N;
    static int M;
    static int[] heights;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        N = scan.nextInt();
        M = scan.nextInt();

        heights = new int[N];

        for(int index = 0 ; index < N ; index++){
            heights[index] = scan.nextInt();
        }

        Arrays.sort(heights);

        System.out.println(binarySearch(0,heights[heights.length - 1]));
    }

    public static int binarySearch(int start,int end){
        if(start > end) return -1;
        int mid = (start + end) / 2;

        if(cut(mid) == M) return mid;
        else if (cut(mid) > M) return binarySearch(mid+1,end);
        else return binarySearch(start,mid-1);
    }

    public static int cut(int mid){
        int cutHeight = 0;

        for(int index = 0; index < N; index++){
            if(heights[index] - mid > 0)
                cutHeight += heights[index] - mid;
        }

        return cutHeight;
    }

}

/*
input
4 6
19 15 10 17

output
15
 */