# 합이 최대가 되게 두 수 곱해서 더하기
# 음수, 0, 1, 양수
# 음수는 음수끼리 양수는 양수끼리 / 음수 0하고 곱하기 1은 걍 더하기

N = int(input().strip())
nums = [int(input().strip()) for _ in range(N)]


#nums.sort()

# 음수, 양수, 따로 정렬?

negative = []
zero = 0
one = 0
positive = []
result = 0

for n in nums:
    if  n > 1:
        positive.append(n)
    elif n < 0:
        negative.append(n)
    elif n == 1:
        one += 1
    elif n == 0:
        zero += 1

positive.sort(reverse=True)
negative.sort()

# 두 개씩 묶어서 곱하기 음수, 양수
for i in range(0, len(negative), 2):
    if i + 1 < len(negative):
        result += negative[i] * negative[i+1]
    elif zero == 0:
        result += negative[i]

for i in range(0, len(positive), 2):
    if i + 1 < len(positive):
        result += positive[i] * positive[i+1]
    else:
        result += positive[i]

result += one

print(result)




