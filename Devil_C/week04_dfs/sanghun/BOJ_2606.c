#include <stdio.h>
#include <stdlib.h>

struct virus{
    int node;
    struct virus* next;
};

struct virus* graph[101];

void network(int u, int v){
    struct virus *vir;
    vir = (struct virus*)malloc(sizeof(struct virus));
    vir -> node = v;
    vir -> next = graph[u];
    graph[u] = vir;
}

int visited[101];
int count = 0;
void dfs(int start){
    visited[start] = 1;
    count ++;
    struct virus* current = graph[start];
    while(current != NULL){
        if(!visited[current -> node]){
            dfs(current -> node);
        }
        current = current -> next;
    }
    return;
}

int main(){
    int com;
    int num;
    scanf("%d %d", &com, &num);
    
    for(int i=0; i<num; i++){
        int u, v;
        scanf("%d %d", &u, &v);
        network(u, v);
        network(v, u);
    }

    dfs(1);
    printf("%d", count -1);
    return 0;
}