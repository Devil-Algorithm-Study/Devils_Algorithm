package week03_greedy.jiyun;

import java.io.*;
import java.util.*;

public class BOJ1744 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] num = new int[n];

        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(num);

        int result = 0;

        int posIdx = 0;
        for (int i=0; i<n; i++) {
            if (i < n-1 && num[i]<0 && num[i+1]<0) {
                result += num[i]*num[++i];
            }
            else if (i < n-1 && num[i]<0 && num[i+1]==0) {
                posIdx = i+2;
                break;
            }
            else if (num[i]<0) {
                result += num[i];
                posIdx = i+1;
                break;
            }
            else {
                posIdx = i;
                break;
            }
        }

        for (int i=posIdx; i<n; i++) {
            if (num[i]==0 || num[i]==1) {
                result += num[i];
            }
            else if ((n-i)%2!=0) {
                result += num[i];
            }
            else {
                result += num[i] * num[++i];
            }
        }

        System.out.println(result);
    }
}
