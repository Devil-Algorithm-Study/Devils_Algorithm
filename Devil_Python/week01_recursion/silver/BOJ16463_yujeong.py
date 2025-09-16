# 13일의 금요일 수 세기
# 2019~사용자 입력 년도 까지

def calendar(year, month, day):
    # 1월, 2월은 전년도 13월, 14월로 계산
    # 윤년 계산 단순화 위해.. 젤러씨가 그럼
    if month == 1:
        month = 13
        year -= 1
    elif month == 2:
        month = 14
        year -= 1
        
    # Zeller's Congruence
    # 0=토, 1=일, 2=월, 3=화, 4=수, 5=목, 6=금
    return (day + (13 * (month + 1)) // 5 + year + (year // 4) - (year // 100) + (year // 400)) % 7

def friday13(end):
    start = 2019 # 시작년도
    total = end - start + 1 # 총 년도 수
    
    cycle_count = 0 # 400년 동안의 13일의 금요일 수
    
    # 400년 주기 동안의 13일의 금요일 수 세기
    for year in range(start, start + 400):
        for month in range(1, 13):
            if calendar(year, month, 13) == 6: # 현재 연도/월/13일이 금요일(6)이면
                cycle_count += 1
    
    cycle = total // 400 # 400년 주기 수
    remainder_year = total % 400 # 나머지 년도 수
    count = cycle_count * cycle # 전체 400년 주기에서의 13일의 금요일 수
    
    # 나머지 년도에서의 13일의 금요일 수
    for year in range(start, start + remainder_year): # 지정된연도 범위 순회
        for month in range(1, 13): # 월 범위 순회
            if calendar(year, month, 13) == 6: # 현재 연도/월/13일이 금요일(6)이면
                count += 1        
    return count

n = int(input())
print(friday13(n))