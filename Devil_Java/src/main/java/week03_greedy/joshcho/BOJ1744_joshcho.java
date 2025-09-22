package week03_greedy;

import java.util.*;
import java.io.*;

public class BOJ1744_joshcho {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> positives = new ArrayList<>(); // 1보다 큰 양수
        List<Integer> negatives = new ArrayList<>(); // 음수
        int zeroes = 0; // 0의 개수만큼 count -> 나중에 음수를 지워주는 역할
        int ones = 0; // 1의 개수만큼 count -> 쓸모 없으니 그냥 더하기

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(br.readLine());

            if(number > 1) positives.add(number);
            if(number == 1) ones++;
            if(number == 0) zeroes++;
            if(number < 0) negatives.add(number);

        }

        Collections.sort(positives,  Collections.reverseOrder());
        Collections.sort(negatives);

        // 2개씩 묶어서 곱할 때 전체 입력받은 개수가 홀/짝인지 분기처리
        for(int i = 0; i < positives.size() - 1; i += 2) {
            sum += positives.get(i) * positives.get(i+1);
        } 
        if(positives.get(i) % 2 == 1) sum += positives.get(positives.size()-1);

        if(negatives.size() % 2 == 0) {
            for(int i = 0; i < negatives.size() - 1; i += 2) {
                sum += negatives.get(i) * negatives.get(i+1);
            }
        }
        else
        {
            for(int i = 0; i < negatives.size() - 1; i += 2) {
                sum += negatives.get(i) * negatives.get(i+1);
            }
            if(zeroes == 0) sum += negatives.get(negatives.size()-1);
        }
        System.out.println(sum + ones);
    }
}
