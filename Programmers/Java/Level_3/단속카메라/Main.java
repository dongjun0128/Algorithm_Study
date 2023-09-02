package Level_3.단속카메라;

import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static int solution(int[][] routes) {
        int answer = 0;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] route1, int[] route2) {
                return route1[1] - route2[1];
            }
        });

        int cam = Integer.MIN_VALUE;

        for (int i = 0; i < routes.length; i++) {
            if(cam < routes[i][0]) {
                // 현재 카메라의 위치가 route의 시작 지점보다 작으면
                // 새로운 cam을 route의 종료 지점에 설치한다
                cam = routes[i][1];
                answer++;
            }
        }

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int [][] {{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}}));
    }
}
