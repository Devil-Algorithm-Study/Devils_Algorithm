package week02_bruteforce;

import java.io.*;

public class BOJ16463_joshcho
{

    // 윤년 확인 로직
    static boolean isLeap(int y) {
        return (y % 4 == 0 && y % 100 != 0) || (y % 400 == 0);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int yearEnd = Integer.parseInt(br.readLine());

        // 요일: 0~6 순서대로 일~토,  2019-01-01은 화요일이므로 첫 선언은 2로 함.
        int firstDayOfWeek = 2;

        // 배열 크기가 13칸인 이유: 가독성을 높이기 위해 1번~12번 인덱스가 1월~12월과 매칭.
        // 이 정도로는 메모리 누수 차이 거의 없음
        int[] DaysOfMonth = {0,31,28,31,30,31,30,31,31,30,31,30,31};

        // sum, count 등 반복문을 돌리면서 누적하는 변수는 for문 밖에서 선언하고 사용.
        int fridayThirteenCount = 0;

        // 2019년부터 입력한 연도까지 탐색
        for (int startingYear = 2019; startingYear <= yearEnd; startingYear++) {
            // 일 년 열두 달 탐색
            for (int month = 1; month <= 12; month++) {
                // dayOfWeek13 : 이번 달의 13일은 무슨 요일인가?
                int dayOfWeek13 = (firstDayOfWeek + 12) % 7;
                if (dayOfWeek13 == 5) fridayThirteenCount++;

                // 윤년이면 2월 29일로 하고, 아니면 28일 그대로
                int days = (month == 2 && isLeap(startingYear)) ? 29 : DaysOfMonth[month];
                firstDayOfWeek = (firstDayOfWeek + days) % 7; // 다음 달 1일의 요일
            }
        }

        System.out.println(fridayThirteenCount);
    }
}
