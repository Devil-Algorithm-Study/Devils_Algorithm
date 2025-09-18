#include <stdio.h>
#include <stdlib.h>

int compare(const void *a, const void *b) {
    int x = *(int*)a;
    int y = *(int*)b;
    if (x < y) return 1;
    if (x > y) return -1;
    return 0;
}

int main() {
    int n = 0;
    scanf("%d", &n);

    int *wei = (int*)malloc(n * sizeof(int));
    for (int i = 0; i < n; i++) {
        scanf("%d", &wei[i]);
    }

    qsort(wei, n, sizeof(int), compare);

    int max = 0;
    int cnt = 1;
    for (int i = 0; i < n; i++) {
        //printf("wei : %d , max: %d, cnt: %d \n", wei[i], max, cnt);
        if (wei[i] * cnt > max) {
            max = wei[i] * cnt;
        }
        cnt++;
    }
    printf("%d", max);
    free(wei);
    return 0;
}
