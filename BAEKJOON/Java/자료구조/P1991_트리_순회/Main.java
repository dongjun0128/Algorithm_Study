package 자료구조.P1991_트리_순회;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Node root;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("/Users/shindongjun/Desktop/Algorithm_Study/BAEKJOON/Java/자료구조/P1991_트리_순회/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        for (int n = 0; n < N; n++) {
            st = new StringTokenizer(br.readLine());

            char name = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            createNode(name, left, right);
        }

        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }

    public static void postOrder(Node node) {
        if(node.left != null)
            postOrder(node.left);

        if(node.right != null)
            postOrder(node.right);

        System.out.print(node.data);
    }

    public static void inOrder(Node node) {
        if(node.left != null)
            inOrder(node.left);

        System.out.print(node.data);

        if(node.right != null)
            inOrder(node.right);
    }

    public static void preOrder(Node node) {
        System.out.print(node.data);

        if(node.left != null)
            preOrder(node.left);

        if(node.right != null)
            preOrder(node.right);
    }

    public static void createNode(char data, char leftData, char rightData) {
        if(root == null) {
            root = new Node(data);
            root.left = leftData != '.' ? new Node(leftData) : null;
            root.right = rightData != '.' ? new Node(rightData) : null;
        }else {
            searchNode(root, data, leftData, rightData);
        }
    }

    public static void searchNode(Node node, char data, char leftData, char rightData) {
        if(node == null) {
            return;
        }else if(node.data == data) {
            node.left = leftData != '.' ? new Node(leftData) : null;
            node.right = rightData != '.' ? new Node(rightData) : null;
        }else {
            searchNode(node.left, data, leftData, rightData);  // 오른쪽 재귀 탐색
            searchNode(node.right, data, leftData, rightData);  // 왼쪽 재귀 탐색
        }
    }
}

class Node{
    char data;
    Node left;
    Node right;

    Node(char data) {
        this.data = data;
    }
}
