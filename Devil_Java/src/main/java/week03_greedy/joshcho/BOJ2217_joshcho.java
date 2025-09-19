package week03_greedy.joshcho;

import java.io.*;
import java.util.*;

public class BOJ2217_joshcho {

    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numberOfRopes = Integer.parseInt(br.readLine().trim());
        int[] rope = new int[numberOfRopes];

        for (int i = 0; i < numberOfRopes; i++) rope[i] = Integer.parseInt(br.readLine().trim());

        // 오름차순 정렬을 통해서 최솟값을 잡고, 최솟값보다 큰 밧줄만 쓰기
        // 밧줄을 다 쓸 필요가 없으니, 밧줄 개수는 약한 밧줄을 뺀만큼 곱해주면 됨
        Arrays.sort(rope);

        int maxWeight = 0;
        for (int i = 0; i < numberOfRopes; i++) {
            int weight = rope[i] * (numberOfRopes - i);
            if (weight > maxWeight) maxWeight = weight;
        }

        System.out.println(maxWeight);
    }
}