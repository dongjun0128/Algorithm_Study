package Level_3.스티커모으기2;

import java.util.Arrays;

public class Main {
    public static int solution(int sticker[]) {
        int answer = 0;

        if (sticker.length == 1) {
            return sticker[0];
        }

        int[] dp1 = new int[sticker.length];
        int[] dp2 = new int[sticker.length];

        dp1[0] = sticker[0];
        dp1[1] = sticker[0];

        for (int i = 2; i < dp1.length - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + sticker[i]);
        }

        dp2[0] = 0;
        dp2[1] = sticker[1];

        for (int i = 2; i < dp2.length; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + sticker[i]);
        }

//        System.out.println(Arrays.toString(dp1));
//        System.out.println(Arrays.toString(dp2));

        answer = Math.max(dp1[dp1.length - 2], dp2[dp2.length - 1]);

        return answer;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{14, 6, 5, 11, 3, 9, 2, 10}));
    }
}
