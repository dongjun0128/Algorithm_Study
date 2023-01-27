package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class tuple implements Comparable<tuple> {

    private int a;
    private int b;

    public tuple(int a, int b){
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return this.a;
    }

    public int getB(){
        return this.b;
    }

    @Override
    public int compareTo(tuple other) {
        if(this.a > other.a){
            return 1;
        } else if (this.a < other.a) {
            return -1;
        } else{
            if(this.b > other.b){
                return 1;
            } else if (this.b < other.b) {
                return -1;
            } else{
                return 0;
            }
        }

    }
}

public class 좌표정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        ArrayList<tuple> arrayList = new ArrayList<>();

        while(N > 0){
            String[] input = br.readLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);

            arrayList.add(new tuple(a,b));

            N--;
        }

        Collections.sort(arrayList);

        for(int i = 0 ; i <arrayList.size(); i++){
            System.out.println(arrayList.get(i).getA()+" " +arrayList.get(i).getB());
        }

    }
}
