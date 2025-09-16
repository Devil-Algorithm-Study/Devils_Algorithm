package week01_recursion.silver;
import java.util.Scanner;
public class BOJ24060_joshcho
{
    static int N, K;
    static int[] array, temp;
    static long count = 0;
    static int answer = -1;

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        N = input.nextInt();
        K = input.nextInt();

        if( (N >= 5 && N <= 500_000) && (K >=1 && K <= 100_000_000) ) {
            array = new int[N];
            temp = new int[N];

            for (int i = 0; i < N; i++) {
                array[i] = input.nextInt();
            }
        }

        mergeSort(0, N-1);

        System.out.println(answer);
    }

    static void mergeSort(int startIdx, int endIdx){
        if(startIdx >= endIdx || answer != -1) return;

        int midIdx = (startIdx + endIdx)/2;
        mergeSort(startIdx, midIdx);
        mergeSort(midIdx + 1, endIdx);
        merge(startIdx, midIdx, endIdx);
    }

    static void merge(int startIdx, int midIdx, int endIdx) {
        if(answer !=-1) return;

        int i = startIdx, j = midIdx + 1, t = 0;

        while( i <= midIdx && j <= endIdx) {
            if(array[i] <= array[j]) temp[t++] = array[i++];
            else temp[t++] = array[j++];
        }
        while(i <= midIdx) {
            temp[t++] = array[i++];
        }
        while(j <= endIdx) {
            temp[t++] = array[j++];
        }

        i = startIdx;
        t = 0;

        while(i <= endIdx) {
            array[i] = temp[t++];
            count++;
            if(count == K) {
                answer = array[i];
                return;
            }
            i++;
        }
    }
}