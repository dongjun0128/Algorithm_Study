package Softeer.업무_처리;

import java.util.*;
import java.io.*;


public class Main
{

    static class Node {
        int num;
        boolean finish;

        public Node(int num) {
            this.num = num;
            this.finish = false;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "num=" + num +
                    ", finish=" + finish +
                    '}';
        }
    }

    static int H,K,R;
    static ArrayList<ArrayList<Node>> tree;
    static int answer = 0;
    static int S;

    public static void main(String args[]) throws IOException
    {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/Softeer/업무_처리/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 업무는 R일 동안 진행된다.
        // 처음에 말단 직원들만 각각 K개의 순서가 정해진 업무를 가지고 있다.
        // 각 업무는 업무 번호가 있다.
        // 각 날짜에 남은 업무가 있는 경우, 말단 직원은 하나의 업무를 처리해서 상사에게 올린다.
        // 다른 직원들도, 대기하는 업무가 있는 경우 업무를 올라온 순서대로 하나 처리해서 상사에게 올린다.
        // 단, 홀수 번째 날짜에는 왼쪽 부하직원이 올린 업무를, 짝수 번째 날짜에는 오른쪽 부하 직원이 올인 업무를 처리한다.

        // 부서장이 처리한 일은 완료된 것이다.
        // 업무를 올리는 것은 모두 동시에 진행된다.
        // 따라서 그날 올린 업무를 상사가 처리하는 것은 그 다음날에 가능하다.
        // 부서의 조직과 대기하는 업무들을 입력 받아 처리가 완료된 업무들의 번호의 합을 계산하는 프로그램을 작성하여라

        H = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        S = 1;
        int employeeNum = 1;

        for(int i = 0 ; i < H ; i++) {
            employeeNum *= 2;
        }

        while(S < employeeNum) {
            S *= 2;
        }

        tree= new ArrayList<>();

        for(int i = 0 ; i < S * 2 ; i++) {
            tree.add(new ArrayList<>());
        }

        // System.out.println(employeeNum);
        // System.out.println(S);

        for(int i = 0 ; i < employeeNum ; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j = 0 ; j < K ; j++) {
                int num = Integer.parseInt(st.nextToken());
                tree.get(i + S).add(new Node(num));
            }
        }

        //System.out.println(tree);

        for(int day = 1 ; day <= R ; day++) {
            dfs(1,day);
        }

        System.out.println(answer);


    }

    static void dfs(int index, int day) {
        if(tree.get(index).size() != 0) {
            int num = 0;

            if(day % 2 == 1) { // 홀수이면 왼쪽 부하직원

                for(int workIndex = 0; workIndex < tree.get(index).size() ; workIndex+= 2) {
                    if(tree.get(index).get(workIndex).finish == false) {
                        tree.get(index).get(workIndex).finish = true;
                        num = tree.get(index).get(workIndex).num;
                        break;
                    }
                }

            } else { // 짝수이면 오른쪽 부하직원
                for(int workIndex = 1; workIndex < tree.get(index).size() ; workIndex+= 2) {
                    if(tree.get(index).get(workIndex).finish == false) {
                        tree.get(index).get(workIndex).finish = true;
                        num = tree.get(index).get(workIndex).num;
                        break;
                    }
                }
            }

            if(num != 0) {
                if (index == 1) { // 부서장 님이면 answer에 저장
                    answer += num;
                } else { // 부하직원이면 위로 전달
                    tree.get(index / 2).add(new Node(num));
                }
            }
        }

        if(index * 2 < tree.size()) {
            dfs(index*2, day);
            dfs(index*2 + 1 , day);
        }
    }
}