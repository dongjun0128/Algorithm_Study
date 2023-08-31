package Level_3.야근지수;

import java.util.*;

public class Main {

    public static long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int work : works) {
            pq.offer(work);
        }

        while (n > 0) {
            int maxWork = pq.poll();

            if (maxWork <= 0) {
                break; // 남은 작업이 없으면 종료
            }

            pq.offer(maxWork - 1);
            n--;
        }

        long answer = 0;
        while (!pq.isEmpty()) {
            int work = pq.poll();
            answer += (long) work * work;
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(50, new int[]{98, 50, 2}));
        System.out.println(solution(4, new int[]{4, 3, 3}));
    }
}
