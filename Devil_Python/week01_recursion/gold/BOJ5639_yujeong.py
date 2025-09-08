import sys # 시스템 모듈 불러오기
sys.setrecursionlimit(10**6) # 재귀 깊이 제한 설정 (늘림)

before = list(map(int, input("전위 순회 결과를 입력하세요.: ").split())) # 전위 순회 결과를 리스트로 입력받기

# 예외처리1 : 입력값이 조건에 맞지 않는 경우
for i in range(len(before)):
    if before[i] < 1 or before[i] > 10**6 or len(before) > 10000:
        print("error: 1 이상 10^6 이하의 서로 다른 양의 정수를 10000개 이하로 입력해주세요.")
        sys.exit(0)

# 예외처리2 : 중복된 값이 있는 경우
if len(before) != len(set(before)):
    print("error: 같은 키를 가지는 노드가 존재합니다.")
    sys.exit(0)

length = len(before) # 전위 순회 결과의 길이 (노드의 개수)
index = 0 # 전위 순회 배열을 왼->오 순서로 읽을 전역 인덱스
after = [] # 후위 순회 결과를 저장할 리스트

def solve(bound): # 후위 순회 결과를 구하는 재귀 함수
    global index # 전역 인덱스 사용 
    
    if index >= length or before[index] > bound: # 인덱스가 배열 길이보다 크거나, 현재 노드가 경계값보다 크면 종료
        return
    
    root = before[index] # 현재 노드를 루트로 설정
    index += 1 # 인덱스 증가 (다음 노드로 이동)
    
    solve(root) # 왼쪽 서브트리 탐색 (현재 노드가 경계값)
    solve(bound) # 오른쪽 서브트리 탐색 (부모 노드가 경계값)
    after.append(root) # 후위 순회 결과에 현재 노드 추가
    
solve(float('inf')) # 무한대를 경계값으로 설정하여 재귀 함수 시작
print("후위 순회 결과:", " ".join(map(str, after))) # 후위 순회 결과 출력