package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 수_정렬하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N =Integer.parseInt(br.readLine());
        ArrayList<Integer> arr = new ArrayList<>();

        for(int i = 0 ; i < N ; i++){
            arr.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(arr);

        for(int i = 0 ; i < N ; i++){
            System.out.println(arr.get(i));
        }
    }
}
