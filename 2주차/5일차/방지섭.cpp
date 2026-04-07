#include <bits/stdc++.h>
using namespace std;

struct node{
    int x,y,mir_cnt,dir;
};

int n,m;
char A[100][100];
int min_mir[100][100][4]; // min_mir[x][y][dir] = x,y에서 방향이 dir일 때의 최소 거울 사용량
pair<int,int> s, e;
void input(){
    cin >> m >> n;
    bool flag = false;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            for(int k=0;k<4;k++) min_mir[i][j][k] = INT32_MAX;
            cin >> A[i][j];
            if(A[i][j] == 'C'){
                if(flag){
                    e = {i,j};
                }
                else{
                    flag = true;
                    s = {i,j};
                }
                A[i][j] = '.';
            }
        }
    }
}

int dx[] = {-1,0,1,0}, dy[] = {0,-1,0,1};
void solve(){
    queue<node> q;
    for(int i=0;i<4;i++){
        q.push({s.first, s.second, 0, i}); 
        min_mir[s.first][s.second][i] = 0;
    }

    while(!q.empty()){
        node f = q.front();
        // cout << f.x << " " << f.y << "\n";
        q.pop();
        int nx = f.x + dx[f.dir], ny = f.y + dy[f.dir];
        if(!(nx >= 0 && nx < n && ny >= 0 && ny < m)) continue;
        if(A[nx][ny] == '*') continue;

        if(min_mir[nx][ny][f.dir] > f.mir_cnt){
            min_mir[nx][ny][f.dir] = f.mir_cnt; 
            q.push({nx, ny, f.mir_cnt, f.dir});
        }
        if(min_mir[nx][ny][(f.dir + 1)%4] > f.mir_cnt + 1){
            min_mir[nx][ny][(f.dir + 1)%4] = f.mir_cnt + 1;
            q.push({nx, ny, f.mir_cnt+1 , (f.dir + 1)%4});
        }
        if(min_mir[nx][ny][(f.dir + 3)%4] > f.mir_cnt + 1){
            min_mir[nx][ny][(f.dir + 3)%4] = f.mir_cnt + 1;
            q.push({nx, ny, f.mir_cnt+1, (f.dir + 3)%4});
        }
    }
    
    int ans = INT32_MAX;
    for(int i=0;i<4;i++) ans = min(ans, min_mir[e.first][e.second][i]);
    cout << ans;
}

int main(){
    cin.tie(0)->sync_with_stdio(0);

    input();
    solve();

    return 0;
}