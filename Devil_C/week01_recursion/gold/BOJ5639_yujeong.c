#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int before[10001];
int after[10001];
int N, idx, outIdx = 0;

void solve(int bound) {
    if (idx >= N || before[idx] > bound)
        return;
    int root = before[idx++];
    solve(root);                // 왼쪽 서브트리
    solve(bound);               // 오른쪽 서브트리
    after[outIdx++] = root;
}

int main(void) {
    // 공백/엔터로 구분된 정수들을 EOF까지 읽음
    N = 0;
    printf("전위 순회 결과를 입력하세요.(종료: 리눅스/맥 Ctrl+D, 윈도우 Ctrl+Z 후 Enter)\n:");
    while (scanf("%d", &before[N]) == 1) {
        N++;
        if (N > 10000) {  // 예외처리1 : 크기 제한 체크 (넘치면 더 읽지 않음)
            printf("error: 10000개 이하만 입력 가능\n");
            return 0;
        }
    }

    // 값 범위 + 중복 검사: seen 배열(0/1 표시)로 한 번에 처리
    const int MAX = 1000000; // const로 해서 변경 불가하게

    // 동적으로 할당하고 0으로 초기화
    // MAX + 1 -> 입력값이 1,000,000일 때 접근 가능하게 하기 위해
    // unsigned -> 양수여야 하기 때문에
    unsigned char* seen = (unsigned char*)calloc(MAX + 1, 1);

    if (!seen) {
        printf("메모리 할당 실패\n");
        return 0;
    }

    for (int i = 0; i < N; i++) {
        int x = before[i];

        // 예외처리2 : 범위 검사
        if (x < 1 || x > MAX) {
            printf("error: 1 이상 10^6 이하만 허용\n");
            free(seen);
            return 0;
        }

        // 예외처리3 : 중복 검사
        if (seen[x]) {
            printf("error: 중복 값 존재\n");
            free(seen);
            return 0;
        }

        seen[x] = 1;  // 처음 등장 표시
    }
    free(seen);

    // 전위 → 후위 변환
    solve(INT_MAX);

    printf("후위 순회 결과: ");
    for (int i = 0; i < outIdx; i++)
        printf("%d ", after[i]);
    printf("\n");

    return 0;
}