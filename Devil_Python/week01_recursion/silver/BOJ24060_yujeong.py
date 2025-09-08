import sys # 시스템 모듈 불러오기
sys.setrecursionlimit(10**6) # 재귀 깊이 제한 설정 (늘림)

# global로 선언해줘야 함
save = -1 # K번째로 저장되는 수 (없으면 -1)
save_point = 0 # 지금까지 배열A에 저장된 횟수

# 병합 정렬
def merge_sort(arr, p, r): # arr[p ~ r]
    if p < r:
        q = (p + r) // 2 # 중간 지점 , 소수점 버림
        merge_sort(arr, p, q) # 앞부분 정렬
        merge_sort(arr, q + 1, r) # 뒷부분 정렬
        merge(arr, p, q, r) # 병합

# 병합하기
def merge(arr, p, q, r):
    global save_point, save, K
    
    i = p # 왼쪽 배열 시작 인덱스
    j = q + 1 # 오른쪽 배열 시작 인덱스
    t = 0 # 임시 배열 인덱스
    temp = [0] * (r - p + 1) # 임시 배열

    # 왼/오 배열 모두 남아 있을 때
    while i <= q and j <= r:
        if arr[i] <= arr[j]: # 왼쪽 배열의 원소가 더 작을 때
            temp[t] = arr[i] # 왼쪽 배열 원소를 임시 배열에 넣고
            i += 1 # 왼쪽 배열 한 칸 증가
        else: # 오른쪽 부분 배열의 원소가 더 작을 때
            temp[t] = arr[j] # 오른쪽 배열 원소를 임시 배열에 넣고
            j += 1 # 오른쪽 배열 한 칸 증가
        t += 1 # 임시 배열 한 칸 증가

    # 왼쪽 부분 배열에 원소가 남아 있을 때
    while i <= q:
        temp[t] = arr[i]
        i += 1
        t += 1

    # 오른쪽 부분 배열에 원소가 남아 있을 때
    while j <= r:
        temp[t] = arr[j]
        j += 1
        t += 1

    # 임시 배열 원소를 원래 배열로 복사
    for k in range(t):
        arr[p + k] = temp[k]
        save_point += 1
        if save_point == K:
            save = arr[p + k]
        
# 예외처리1 : 입력값이 조건에 맞지 않는 경우
try:
    N, K = map(int, input("배열의 크기 N, 저장 횟수 K를 입력하세요.: ").split())
except ValueError: # 입력이 정수가 아니거나, 개수가 맞지 않을 때
    print("error")
    sys.exit(0)

# 예외처리2 : N, K가 조건에 맞지 않는 경우
if (N >= 5 and N <= 500000) and (K >= 1 and K <= 10**8):
    arr = list(map(int, input("배열의 원소를 입력하세요.: ").split()))
    
    # 예외처리3 : 배열의 크기와 입력된 배열의 원소 개수가 일치하지 않는 경우
    if (len(arr) != N):
        print("error: 배열의 크기 N과 입력된 배열의 원소 개수가 일치하지 않습니다.")
        sys.exit(0)
        
    # 예외처리4 : 배열의 원소가 조건에 맞지 않는 경우
    for i in range(N):
        if (arr[i] >= 1 and arr[i] <= 10**9):
            continue
        else:
            print("error: 배열의 원소는 1 이상 10^9 이하의 자연수여야 합니다.")
            break
    
    merge_sort(arr, 0, N-1) # 병합 정렬 수행
    print(f"\n총 저장 횟수: {save_point}")
    
    # 예외처리5 : K번째로 저장되는 수가 없는 경우
    if save != -1:
        print(f"배열 A에 {K}번째로 저장되는 수는 {save}입니다.")
    else:
        print(f"error: {save}\n저장 횟수 {save_point}가 K인 {K}보다 작습니다.")
else:
    print("error: N은 5 이상 500,000 이하의 자연수, K는 1 이상 10^8 이하의 자연수여야 합니다.")