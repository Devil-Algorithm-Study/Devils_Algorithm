package Devil_Java.src.main.java.week01_recursion.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ksh24060 {
    private static int num;
    private static int total = 0;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");

        int capacity = Integer.parseInt(firstLine[0]);
        num = Integer.parseInt(firstLine[1]);
        int[] array = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        merge_sort(array, 0, capacity - 1);
        System.out.println(result);
    }

    private static void merge_sort(int[] A, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            merge_sort(A, p, q);
            merge_sort(A, q + 1, r);
            merge(A, p, q, r);
        }
    }

    private static void merge(int[] A, int p, int q, int r) {
        int[] tmp = new int[r - p + 1];
        int i = p;
        int j = q + 1;
        int t = 0;
        while (i <= q && j <= r) {
            if (A[i] <= A[j]) {
                tmp[t++] = A[i++];
            } else tmp[t++] = A[j++];

        }
        while (i <= q)
            tmp[t++] = A[i++];
        while (j <= r)
            tmp[t++] = A[j++];
        i = p;
        t = 0;
        while (i <= r) {
            A[i++] = tmp[t++];
            total++;
            if (total == num)
                result = A[i - 1];
        }

    }

}
