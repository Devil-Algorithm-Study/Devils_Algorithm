package week02_bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16463_sohee {
    static final int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(br.readLine());
        int count = 0;
        int date = 13; // 첨 시작을 13일로 시작해

        for (int i = 2019; i <= year; i++) {
            for(int j=0; j<months.length; j++){
                if(date%7 == 4){ // 다음달의 13일이 금요일이면
                    count++;
                }

                date+=months[j];
                if(j==1 && ((i%4 ==0 && i%100!=0) || i%400 == 0))
                    date++;
            }
        }

        System.out.println(count);


    }
}
/*
 *
 * 13일의 금요일이라는말인데....
 *
 * 2019 ~ 2021 2019 2020 2021
 *
 * 월화수목 금 토일 <<7일 date+7 나머지 이용
 * 윤년은 +1일
 *
 * 1월1일 화요일
 *
 * 걍 1년동안 금요일
 * date%7 ==0 화
 * date%7 ==4 금
 * 반복반복
 * 일단 윤년 찾아
 * 1월 4일 금요일
 * 1월 11 금요일
 *
 * 400
 * !400 and !100
 * !100 and 4
 * */
