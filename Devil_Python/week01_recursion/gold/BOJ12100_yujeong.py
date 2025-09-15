import sys
input = sys.stdin.readline # 빠르게 입력받기 (전처리를 거의 안해서 그냥 인풋보다 빠름)

n = int(input())
board = [list(map(int, input().split())) for _ in range(n)] # for 앞에 있는 입력 방식을 n번 입력 받겠다
answer = 0

# 한 줄을 왼쪽으로 합치는 함수
def compress(line):
    # line안의 원소를 하나씩 꺼내서 x!=0인 것만 new_line의 x로 저장
    new_line = [x for x in line if x != 0]  # 0 제거
    result = [] # 합친 결과 저장 리스트
    skip = False # 바로 직전 원소 합쳤는지 확인
    
    for i in range(len(new_line)):
        # 이미 합친 경우 패스
        if skip:
            skip = False
            continue
        
        # 현재 원소와 다음 원소가 같으면 합치기
        if i + 1 < len(new_line) and new_line[i] == new_line[i + 1]:
            result.append(new_line[i] * 2)  # 두 배 값 append
            skip = True # 다음 원소 합쳣으니까 건너뛰기
            
        else:
            result.append(new_line[i]) # 안 합치고 그대로 추가
            
    result += [0] * (n - len(result))  # 부족한 칸에 0 채우기 (길이는 유지하고)
    
    return result

# 보드를 네 방향으로 이동시키는 함수
def move(board, direction):
    new_board = [[0] * n for _ in range(n)] # 보드 초기화
    
    if direction == 0:  # 왼쪽
        for i in range(n):
            new_board[i] = compress(board[i]) # 각 행을 왼쪽으로 합치기
            
    elif direction == 1:  # 오른쪽
        for i in range(n):
            # ::-1 -> 리스트 전체를 뒤집음
            new_board[i] = compress(board[i][::-1])[::-1] # 각 행을 오른쪽으로 합치기
    
    elif direction == 2:  # 위쪽
        for j in range(n):
            col = [board[i][j] for i in range(n)] # 세로 열 추출
            new_col = compress(col) # 세로 열을 위쪽으로 합치기
            
            for i in range(n):
                new_board[i][j] = new_col[i] # 합친 열을 다시 보드에 넣기
    
    elif direction == 3:  # 아래쪽
        for j in range(n):
            col = [board[i][j] for i in range(n)][::-1] # 세로 열 추출 후 뒤집기
            new_col = compress(col)[::-1] # 세로 열을 아래쪽으로 합치기 후 다시 뒤집기
            
            for i in range(n): 
                new_board[i][j] = new_col[i] # 합친 열을 다시 보드에 넣기
    
    return new_board

# DFS로 최대 5번 이동 탐색
def dfs(board, depth):
    global answer
    
    if depth == 5: # 5번 이동했으면 종료
        # 각 행에서 가장 큰 값 -> 행별 최댓값 중 가장 큰 값 -> 지금까지의 최댓값-이번보드의 최댓값 중 큰 거 선택
        answer = max(answer, max(map(max, board))) # 현재 보드에서 가장 큰 값 갱신
        return
    
    for d in range(4): # 4방향 탐색(좌우상하)
        next_board = move(board, d) # 해당 방향으로 이동
        dfs(next_board, depth + 1) # 다음 깊이 탐색

dfs(board, 0) # 초기 깊이 0부터 시작
print(answer)