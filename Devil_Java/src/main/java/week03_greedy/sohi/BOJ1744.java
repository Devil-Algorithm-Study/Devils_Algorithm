package week03_greedy.sohi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BOJ1744 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        // 음수, 양수 나눠서 담기
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        int zero = 0, one = 0, result = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) positive.add(num);
            else if (num == 1) one++;
            else if (num == 0) zero++;
            else negative.add(num);
        }

        result += one; // 1 걍 더하고

        positive.sort(Collections.reverseOrder());
        Collections.sort(negative);

        for (int i = 0; i < positive.size(); i = i + 2) {
            if (positive.size() > i + 1) {
                result += positive.get(i) * positive.get(i + 1);
            } else result += positive.get(i);

        }

        for (int i = 0; i < negative.size(); i += 2) {
            if (i + 1 < negative.size()) {
                result += negative.get(i) * negative.get(i + 1);
            } else if (zero == 0) result += negative.get(i);

        }

        System.out.println(result);

    }

}