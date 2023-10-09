package Softeer.사물인식_최소_면적_산출_프로그램;

import java.util.*;
import java.io.*;


public class Main
{
    static class Node {
        int x;
        int y;
        int num;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "x:" + this.x + " y:" + this.y + " num:" + this.num;
        }
    }

    static int N,K;
    static ArrayList<ArrayList<Node>> pointList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String args[]) throws IOException
    {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/Softeer/사물인식_최소_면적_산출_프로그램/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for(int i = 0 ; i <= K ; i++) {
            pointList.add(new ArrayList<>());
        }

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            pointList.get(num).add(new Node(x,y,num));
        }

        // System.out.println(pointList);

        dfs(1,1000,-1000,1000,-1000);

        System.out.println(answer);
    }


    public static void dfs(int index, int left, int right, int bottom, int top) {

        if(index == K + 1) {
            answer = Math.min(answer, (right - left) * (top - bottom));
            return;
        }

        for(Node point : pointList.get(index)) {
            int x = point.x;
            int y = point.y;

            int leftN = Math.min(left, x);
            int rightN = Math.max(right, x);
            int bottomN = Math.min(bottom, y);
            int topN = Math.max(top,y);

            int area = (rightN - leftN) * (topN - bottomN);

            if(answer > area) {
                dfs(index + 1, leftN, rightN, bottomN, topN);
            }

        }

    }
}