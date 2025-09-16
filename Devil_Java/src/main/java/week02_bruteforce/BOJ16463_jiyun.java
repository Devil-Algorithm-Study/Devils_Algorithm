package week02_bruteforce;

import java.io.*;

public class BOJ16463_jiyun {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int year = 2019;
        int last_month = 1;
        int cnt = 0;

        // 2019.01.13 = 일요일
        // 일월화수목금토 0123456
        int dayOfWeek = 0;

        while (year <= n) {

            // java 14 이상 지원
            int last_month_days = switch(last_month) {
                case 1,3,5,7,8,10,12 -> 31;
                case 4,6,9,11 -> 30;
                default -> check_leaf(year);
            };

            /* java 11 지원
            int last_month_days;
            switch (last_month) {
                case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                    last_month_days = 31;
                    break;
                case 4: case 6: case 9: case 11:
                    last_month_days = 30;
                    break;
                default:
                    last_month_days = check_leaf(year);
                    break;
            }
            */

            if (dayOfWeek == 5) {
                cnt++;
            }

            dayOfWeek = (dayOfWeek + last_month_days) % 7;

            if (last_month==12) {
                last_month = 0;
                year++;
            }

            last_month++;
        }

        System.out.println(cnt);
    }

    static int check_leaf(int year) {
        if (year%400==0 || (year%100!=0) && (year%4==0)) {
            return 29;
        }
        return 28;
    }
}
