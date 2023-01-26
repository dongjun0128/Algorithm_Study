package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class 통계학 {
    static final int FREQUENCY_STANDARD = 4000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int num = 0;
        ArrayList<Integer> arrayList = new ArrayList<>();
        ArrayList<Integer> mostNums = new ArrayList<>();
        int [] frequencyCheck = new int[8001];

        for (int i = 0; i < N; i++) {
            arrayList.add(Integer.parseInt(br.readLine()));
            num += arrayList.get(i);
            frequencyCheck[FREQUENCY_STANDARD + arrayList.get((i))] += 1;
        }

        int maxCount = 0;

        for(int i = 0 ; i<frequencyCheck.length; i++){
            if(maxCount < frequencyCheck[i]) {
                maxCount = frequencyCheck[i];
                mostNums = new ArrayList<>();
                mostNums.add(i - FREQUENCY_STANDARD);
            } else if (maxCount == frequencyCheck[i]) {
                mostNums.add(i-FREQUENCY_STANDARD);
            }
        }

        Collections.sort(arrayList);
        Collections.sort(mostNums);

        System.out.println((int)Math.round((double)num / N));
        System.out.println(arrayList.get(N / 2));
        if(mostNums.size() > 1)
            System.out.println(mostNums.get(1));
        else
            System.out.println(mostNums.get(0));
        System.out.println(arrayList.get(arrayList.size() - 1) - arrayList.get(0));

    }
}
