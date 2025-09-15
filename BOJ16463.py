N = int(input())

month = [31,28,31,30,31,30,31,31,30,31,30,31]


first_day = 1 #화요일 설정 # 0,1,2,3,4,5,6
count = 0


for year in range(2019, N+1):
    for m in range(12):

        #2월 윤년 계산!
        if m == 1:

            #400의 배수, 100의 배수가 아니면서 4의 배수
            if (year % 400 == 0) or (year % 100 != 0 and year % 4 == 0):
                month_days = 29
            else:
                month_days = 28
        else:
            month_days = month[m]

        #금요일 수 세기
        if (first_day + 12) % 7 == 4:
            count += 1

        #다음 월 시작 요일 계산
        first_day = (first_day + month_days) % 7

print(count)
