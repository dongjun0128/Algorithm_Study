// 다음과 같이 import를 사용할 수 있습니다.
import java.util.*;

public class test {

    public static void main(String[] args) {
        int[] array = { 230, 10, 60, 550, 40, 220, 20 };

        mergeSort(array, 0, array.length - 1);

        for (int v : array) {
            System.out.println(v);
        }

        Queue<Integer> q = new LinkedList<>();
    }

    public static void mergeSort(int[] array, int left, int right) {

        if(left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid+1, right);
            merge(array, left, mid, right);
        }

    }

    public static void merge(int[] array, int left, int mid, int right) {
        int[] L = Arrays.copyOfRange(array, left, mid + 1);
        int[] R = Arrays.copyOfRange(array, mid + 1, right + 1);

        int i = 0, j = 0, k = left;
        int ll = L.length, rl = R.length;

        while(i < ll && j < rl) {
            if(L[i] <= R[j]) {
                array[k] = L[i++];
            }
            else {
                array[k] = R[j++];
            }
            k++;
        }

        // remain
        while(i < ll) {
            array[k++] = L[i++];
        }
        while(j < rl) {
            array[k++] = R[j++];
        }
    }

    public static void q(Integer a) {
        a = a + 1;
        System.out.println(a);
    }

    public static void ss(String s) {
        s = "qqqq";

        System.out.println(s);
    }

    public static  void aa(int num) {
        num++;
        System.out.println(num);
    }
}