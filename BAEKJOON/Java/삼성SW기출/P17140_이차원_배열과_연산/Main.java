package 삼성SW기출.P17140_이차원_배열과_연산;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int r, c, k;
    static int[][] A = new int[3][3];
    static int xLength = 3;
    static int yLength = 3;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/삼성SW기출/P17140_이차원_배열과_연산/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());


        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            if (time > 100) {
                System.out.println(-1);
                return;
            }
            try {
                if (A[r][c] == k) break;
            } catch(Exception e){

            }


            if (xLength >= yLength) {
                rOperation();
            } else {
                cOperation();
            }

            time++;
        }

        System.out.println(time);
    }

    static void rOperation() {
        int[][] tempMap = new int[100][100];
        int maxYLength = 0;

        for (int i = 0; i < A.length; i++) {
            int[] cntNum = new int[101];
            ArrayList<Node> sortList = new ArrayList<>();

            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] == 0) continue;
                else {
                    cntNum[A[i][j]]++;
                }
            }

            for (int j = 1; j < cntNum.length; j++) {
                if (cntNum[j] != 0) {
                    sortList.add(new Node(j, cntNum[j]));
                }
            }

            Collections.sort(sortList);

            maxYLength = Math.max(maxYLength,sortList.size() * 2);

            if(maxYLength > 100) maxYLength = 100;

            int index = 0;

            for (int j = 0; j < sortList.size(); j++) {
                int num = sortList.get(j).num;
                int cnt = sortList.get(j).cnt;

                if(index >= 100) break;
                tempMap[i][index++] = num;
                if(index >= 100) break;
                tempMap[i][index++] = cnt;
            }
        }

        A = new int[A.length][maxYLength];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < maxYLength; j++) {
                A[i][j] = tempMap[i][j];
            }
        }

        yLength = maxYLength;
    }

    static void cOperation(){
        int[][] tempMap = new int[100][100];
        int maxXLength = 0;

        for (int i = 0; i < A[0].length; i++) {
            int[] cntNum = new int[101];
            ArrayList<Node> sortList = new ArrayList<>();

            for (int j = 0; j < A.length; j++) {
                if (A[j][i] == 0) continue;
                else {
                    cntNum[A[j][i]]++;
                }
            }

            for (int j = 1; j < cntNum.length; j++) {
                if (cntNum[j] != 0) {
                    sortList.add(new Node(j, cntNum[j]));
                }
            }

            Collections.sort(sortList);

            maxXLength = Math.max(maxXLength,sortList.size() * 2);

            if(maxXLength > 100) maxXLength = 100;

            int index = 0;

            for (int j = 0; j < sortList.size(); j++) {
                int num = sortList.get(j).num;
                int cnt = sortList.get(j).cnt;

                if(index >= 100) break;
                tempMap[index++][i] = num;
                if(index >= 100) break;
                tempMap[index++][i] = cnt;
            }
        }

        A = new int[maxXLength][A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {
                A[i][j] = tempMap[i][j];
            }
        }

        xLength = maxXLength;
    }
}

class Node implements Comparable<Node> {
    int num;
    int cnt;

    public Node(int num, int cnt) {
        this.num = num;
        this.cnt = cnt;
    }

    @Override
    public int compareTo(Node o) {
        int cmp1 = cnt - o.cnt;

        if (cmp1 == 0) {
            return num - o.num;
        } else {
            return cmp1;
        }
    }
}