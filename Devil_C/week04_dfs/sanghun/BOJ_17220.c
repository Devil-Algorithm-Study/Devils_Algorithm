#include <stdio.h>
#include <stdlib.h>

struct supply {
    int cur;
    struct supply *next;
};

struct supply *graph[128];
int visited[128];
int deleted[128];
int indegree[128];
int count = 0;

void con(int u, int v) {
    struct supply *sup = (struct supply*)malloc(sizeof(struct supply));
    sup->cur = v;
    sup->next = graph[u];
    graph[u] = sup;
    indegree[v]++;
}

void dfs(int start) {
    if (visited[start] || deleted[start]) return;
    visited[start] = 1;
    count++;

    struct supply *current = graph[start];
    while (current != NULL) {
        if (!visited[current->cur] && !deleted[current->cur]) {
            dfs(current->cur);
        }
        current = current -> next;
    }
}

int main() {
    int n, m;
    scanf("%d %d", &n, &m);

    for (int i = 0; i < m; i++) {
        char u, v;
        scanf(" %c %c", &u, &v);
        con(u, v);
    }

    int k;
    scanf("%d", &k);
    for (int i = 0; i < k; i++) {
        char d;
        scanf(" %c", &d);
        deleted[d] = 1;
    }
    int origin = 0;

    for (int i = 'A'; i < 'A' + n; i++) {
        if (!deleted[i] && (indegree[i] == 0)) {
            dfs(i);
            origin ++;
        }
    }

    printf("%d", count - origin);
    return 0;
}
