package Level_3.최고의_집합;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static int[] solution(int n, int s) {
        if(n > s) return new int[] {-1};
        int[] answer = new int[n];
        int idx = 0;
        while(n > 0){
            int value = s/n;
            answer[idx++] = value;
            s -= value;
            n--;
        }
        return answer;
    }

    public static void main(String[] args) {
        solution(2,1);
    }
}
