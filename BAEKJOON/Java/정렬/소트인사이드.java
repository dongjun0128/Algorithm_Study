package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 소트인사이드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int[] nums = new int[10];

        for(char num : input.toCharArray()){
            nums[num - '0'] += 1;
        }

        for(int i = 9 ; i >= 0 ; i--){
            while(nums[i] > 0){
                System.out.print(i);
                nums[i]--;
            }
        }
    }
}
