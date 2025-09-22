import sys
input = sys.stdin.readline

n = int(input())
pos = []
neg = []
ones = 0
zero = 0

# 입력 분류
for _ in range(n):
    num = int(input())
    if num > 1:
        pos.append(num)
    elif num == 1:
        ones += 1
    elif num == 0:
        zero += 1
    else:
        neg.append(num)

# 양수 내림차순, 음수 오름차순 정렬
pos.sort(reverse=True)
neg.sort()

result = 0

# 양수 처리 (큰 수끼리 곱)
for i in range(0, len(pos)-1, 2):
    result += pos[i] * pos[i+1]
if len(pos) % 2 == 1:  # 남는 수 더하기
    result += pos[-1]

# 음수 처리 (작은 수끼리 곱)
for i in range(0, len(neg)-1, 2):
    result += neg[i] * neg[i+1]
if len(neg) % 2 == 1:  # 남는 음수
    if zero == 0:  # 0 없으면 그냥 더함
        result += neg[-1]

# 1 처리 (무조건 더하기)
result += ones

print(result)