package week01_recursion.silver;
import java.util.*;

public class BOJ24060_dwoong {
    public static void main(String[] args) {
        new week01_1().run();
    }
}

class week01_1 {
    int n, k;
    int[] a, temp;
    int count = 0;
    Integer answer = null;   //답

    void merge_sort(int l, int r) { //0 ~ 4

        if (l >= r){ //쪼갤 필요 없음요
            return;
        }

        int m = (l + r) / 2; //걍 가운데 구하자

        merge_sort(l, m); //왼
        merge_sort(m + 1, r); //오
        merge(l, m, r); //merge!
    }

    void merge(int l, int m, int r) {

        int i = l; //왼
        int j = m + 1; //오
        int t = l; //temp에 넣자

        while (i <= m && j <= r) {
            if (a[i] <= a[j]){
                temp[t++] = a[i++];
            }
            else
                temp[t++] = a[j++];
        }

        while (i <= m){
            temp[t++] = a[i++];
        }
        while (j <= r){
            temp[t++] = a[j++];
        }

        //temp 저장하고 count 세기
        for (int x = l; x <= r; x++) {

            a[x] = temp[x];
            count++;

            if (count == k) { //k!
                answer = a[x];
            }
        }
    }

    void run() {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        k = sc.nextInt();

        a = new int[n];
        temp = new int[n];

        for (int i = 0; i < n; i++){
            a[i] = sc.nextInt();
        }

        merge_sort(0, n - 1); //mergesort 진행

        if (answer == null){
            System.out.println(-1);
        }
        else
            System.out.println(answer);
    }


}
