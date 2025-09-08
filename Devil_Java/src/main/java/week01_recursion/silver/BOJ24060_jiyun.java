package week01_recursion.silver;

import java.io.*;
import java.util.*;

public class BOJ24060_jiyun {
    static int N;
    static int K;
    static int save_cnt=0;
    static int[] tmp;
    static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        tmp = new int[N];
        merge_sort(A,0,N-1);
        if (!found) {
            System.out.println(-1);
        }
    }

    static void merge_sort(int[] A, int p, int r) {
        if (found) return;
        if (p<r) {
            int q = (p+r)/2;
            merge_sort(A,p,q);
            merge_sort(A,q+1,r);
            merge(A,p,q,r);
        }
    }

    static void merge(int[] A, int p, int q, int r) {
        if (found) return;

        int i = p;
        int j = q+1;
        int t = 0;

        while (i<=q && j <=r) {
            if (A[i]<=A[j]) {
                tmp[t++] =  A[i++];
            }
            else {
                tmp[t++] = A[j++];
            }

        }
        while (i<=q) {
            tmp[t++] = A[i++];
        }
        while (j<=r) {
            tmp[t++] = A[j++];
        }
        i = p;
        t = 0;

        while (i<=r) {
            A[i++] = tmp[t++];
            save_cnt++;
            if (save_cnt==K) {
                System.out.println(A[i-1]);
                found=true;
                return;
            }
        }
    }
}
