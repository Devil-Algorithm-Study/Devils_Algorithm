import sys

board = []

N = int(sys.stdin.readline().strip()) #\n 없애기 .strip()

#board 만들어
for row in range(N):
    line = sys.stdin.readline().split()
    values = []
    for x in line:
        values.append(int(x))
    board.append(values)


#왼쪽 기준
def sum_line(line):

    new_line = []
    for k in line:
        if k != 0:
            new_line.append(k)

    result = []

    combined = False #합쳐진거 이미 건너뛰어야돼

    for i in range(len(new_line)):
        if combined:
            combined = False
            continue
            #합쳐졌으면 건너뛰고 다음으로 걍 진행
        if i + 1 < len(new_line) and new_line[i] == new_line[i+1]:
            result.append(new_line[i] * 2)
            combined = True

        else:
            result.append(new_line[i])

    while len(result) < N:
        result.append(0)

    return result

def move(board, direction):
    new_board = []

    for k in range(N):
        row = []
        for j in range(N):
            row.append(0)
        new_board.append(row)

    #왼쪽
    if direction == 0:
        for i in range(N):
            new_board[i] = sum_line(board[i])

    #오른쪽
    elif direction == 1:
        for i in range(N):
            new_board[i] = sum_line(board[i][::-1])[::-1] #보드 뒤집어서 하기 왜냐면 왼쪽 기준이니까

    #위쪽
    #음므믐ㅁㅁ 왼쪽 기준이니까 세로 계산해서 하기
    elif direction == 2:
        for i in range(N):
            col = []
            for j in range(N):
                col.append(board[j][i])

            new_col = sum_line(col)

            for j in range(N):
                new_board[j][i] = new_col[j]

    #아래쪽
    #위쪽 뒤짐으면 되지 않나
    elif direction == 3:
        for i in range(N):
            col = []
            for j in range(N-1, -1, -1):
                col.append(board[j][i])

            new_col = sum_line(col)[::-1]

            for j in range(N):
                new_board[j][i] = new_col[j]

    return new_board


#최대 5번까지 해보기 for문 5번?
output = 0
for d1 in range(4):
    for d2 in range(4):
        for d3 in range(4):
            for d4 in range(4):
                for d5 in range(4):
                    directions = [d1, d2, d3, d4, d5]
                    copy_board = board
                    for d in directions:
                        copy_board = move(copy_board, d)
                    for i in range(N):
                        output = max(output, max(copy_board[i]))

print(output)

