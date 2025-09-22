#include <stdio.h>
#include <stdlib.h>

int desc(const void *a, const void *b) { 
    return *(int*)b - *(int*)a;
}

int asc(const void *a, const void *b) { 
    return *(int*)a - *(int*)b;
}

int main() {
    int n;
    scanf("%d", &n);
    int *arr = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) scanf("%d", &arr[i]);

    int pos[100], neg[100];
    int pCnt = 0, nCnt = 0, ones = 0, zeros = 0;

    for (int i = 0; i < n; i++) {
        if (arr[i] > 1) pos[pCnt++] = arr[i];
        else if (arr[i] == 1) ones++;
        else if (arr[i] == 0) zeros++;
        else neg[nCnt++] = arr[i];
    }

    qsort(pos, pCnt, sizeof(int), desc);
    qsort(neg, nCnt, sizeof(int), asc); 

    long long sum = 0;

    for (int i = 0; i + 1 < pCnt; i += 2)
        sum += pos[i] * pos[i + 1];
    if (pCnt % 2 == 1) sum += pos[pCnt - 1];

    for (int i = 0; i + 1 < nCnt; i += 2)
        sum += neg[i] * neg[i + 1];
    if (nCnt % 2 == 1 && zeros == 0) sum += neg[nCnt - 1];

    sum += ones;

    printf("%lld\n", sum);
    free(arr);
    return 0;
}
