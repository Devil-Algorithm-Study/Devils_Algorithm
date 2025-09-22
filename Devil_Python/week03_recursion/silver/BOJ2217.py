N = int(input().strip())
rope = [int(input().strip()) for _ in range(N)]

Max = 0

# 로프 여러개니까 내림차순 정렬 후 가능한 무게 찾기 (30 ,20, 10)

rope.sort(reverse=True)

for i in range(N):
    weight = rope[i] * (i + 1)
    Max = max(Max, weight)


print(Max)


# [40, 30, 20, 10]

# rope[0] = 40 x 1 로프 1개
# rope[1] = 30 x 2 60kg 가능