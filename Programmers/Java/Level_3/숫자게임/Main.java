package Level_3.숫자게임;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;

public class Main {
    public static int solution(int[] A, int[] B) {
        int answer = 0;

        Arrays.sort(A);
        Arrays.sort(B);

        Deque<Integer> AA = new ArrayDeque<>();
        Deque<Integer> BB = new ArrayDeque<>();

        for (int i = A.length - 1; i >= 0 ; i--) {
            AA.add(A[i]);
            BB.add(B[i]);
        }

        for (int i = 0; i < A.length; i++) {
            int numA = AA.pop();

            if(numA >= BB.peek()) {
                BB.removeLast();
            } else {
                BB.removeFirst();
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 3, 7}, new int[]{2, 2, 6, 8}));
    }
}
