#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <stdlib.h>

int save = -1; // K번째로 저장되는 수
int save_point = 0; // 저장 횟수
int K; // K번째

// 병합하기
void merge(int arr[], int p, int q, int r) {
    int i = p; // 왼쪽 부분 배열의 시작 인덱스
    int j = q + 1; // 오른쪽 부분 배열의 시작 인덱스
    int t = 0; // 임시 배열의 인덱스
    int* temp = (int*)malloc((r - p + 1) * sizeof(int)); // 임시 배열 동적 할당

    // 왼/오 배열 모두 남아 있을 때
    while (i <= q && j <= r) {
        if (arr[i] <= arr[j])
            temp[t++] = arr[i++];
        else
            temp[t++] = arr[j++];
    }

    while (i <= q)
        temp[t++] = arr[i++];

    while (j <= r)
        temp[t++] = arr[j++];

    i = p;
    t = 0;

    while (i <= r) {
        arr[i++] = temp[t++];
        save_point++;

        if (save_point == K)
            save = arr[i - 1];
    }
}

// 병합 정렬
void merge_sort(int arr[], int p, int r) {
    if (p < r) {
        int q = (p + r) / 2;
        merge_sort(arr, p, q);
        merge_sort(arr, q + 1, r);
        merge(arr, p, q, r);
    }
}

int main() {
    int N;
    printf("배열의 크기 N, 저장 횟수 K를 입력하세요: ");
    if (scanf("%d %d", &N, &K) != 2) { // 입력 오류 처리
        printf("입력 오류\n");
        return 0;
    }

    if (N >= 5 && N <= 500000 && K >= 1 && K <= 100000000) {
        int* arr = (int*)malloc(N * sizeof(int));
        if (!arr) { // 메모리 할당 실패 처리
            printf("메모리 할당 실패\n");
            return 0;
        }
        printf("배열 A의 원소 %d개를 입력하세요: ", N);

        for (int i = 0; i < N; i++) {
            if (scanf("%d", &arr[i]) != 1) { // 입력 오류 처리
                printf("입력 오류\n");
                free(arr);
                return 0;
            }

            if (arr[i] < 1 || arr[i] > 1000000000) { // 원소 범위 검사
                printf("배열 A의 원소는 1 이상 10^9 이하의 자연수여야 합니다.\n");
                free(arr);
                return 0;
            }
        }

        merge_sort(arr, 0, N - 1); // 병합 정렬 수행
        printf("\n총 저장 횟수: %d\n", save_point);

        if (save != -1)
            printf("배열 A에 %d번째로 저장되는 수는 %d입니다.\n", K, save);
        else
            printf("저장 횟수 %d가 K인 %d보다 작습니다.\nerror: %d\n", save_point, K, save);

        free(arr); // 동적 할당 해제
    }
    else {
        printf("N은 5 이상 500,000 이하의 자연수, K는 1 이상 10^8 이하의 자연수여야 합니다.\n");
    }

    return 0;
}