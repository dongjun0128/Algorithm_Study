package 인덱스_트리.P2243_사탕상자;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int S;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/인덱스_트리/P2243_사탕상자/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        init();

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());

            if (A == 1) { // 사탕 꺼내기
                int B = Integer.parseInt(st.nextToken());

                int index = pickup(1,S,1,B);
                update(index, -1);
                System.out.println(index);
            } else { // A == 2
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                update(B,C);
            }

        }


    }

    public static void init() {
        S = 1;

        while (S < 1000000) {
            S *= 2;
        }

        tree = new int[S * 2];
    }

    //left, right는 범위(1~S), node: 현재 위치한 노드의 인덱스, target: 목표 노드
    public static int pickup(int left, int right, int node, int target) {
        if(left==right) return left;

        int mid= (left+right)/2;
        if(tree[node*2]>=target) { //왼쪽 노드이면 (target그대로)
            return pickup(left, mid, node*2, target);
        }else { //오른쪽 노드이면 (target-왼쪽 노드)
            return pickup(mid+1, right, node*2+1, target-tree[node*2]);
        }
    }

    public static void update(int index, int diff){
        index = index + S - 1;

        tree[index] += diff;

        while(index > 0){
            index /= 2;
            tree[index] += diff;
        }
    }


}
