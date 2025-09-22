import sys
input = sys.stdin.readline

n = int(input())
ropes = [int(input()) for _ in range(n)]

# 로프 최대 하중 오름차순 정렬
ropes.sort()

max_weight = 0
for i in range(n):
    # i번째 로프부터 끝까지 사용했을 때
    weight = ropes[i] * (n - i)
    max_weight = max(max_weight, weight)

print(max_weight)