package 문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 다이얼 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int time = 0;
        Map<Character, Integer> dial = new HashMap<>();

        dial.put('A',2);
        dial.put('B',2);
        dial.put('C',2);
        dial.put('D',3);
        dial.put('E',3);
        dial.put('F',3);
        dial.put('G',4);
        dial.put('H',4);
        dial.put('I',4);
        dial.put('J',5);
        dial.put('K',5);
        dial.put('L',5);
        dial.put('M',6);
        dial.put('N',6);
        dial.put('O',6);
        dial.put('P',7);
        dial.put('Q',7);
        dial.put('R',7);
        dial.put('S',7);
        dial.put('T',8);
        dial.put('U',8);
        dial.put('V',8);
        dial.put('W',9);
        dial.put('X',9);
        dial.put('Y',9);
        dial.put('Z',9);

        for(char c : input.toCharArray()){
            time += dial.get(c) + 1;
        }

        System.out.println(time);
    }
}
