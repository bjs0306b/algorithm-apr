#include <bits/stdc++.h>
using namespace std;

int n;
int A[10][4][4][4]; // 효능 : a번째 입력에서 시계 방향으로 b번 돌렸을 때 (c, d)의 값
char B[10][4][4][4]; // 원소 : a번째 입력에서 시계 방향으로 b번 돌렸을 때 (c, d)의 값
bool visited[10];
map<char, int> color_value;
int ans = 0;

pair<int, char> bomb[3][5][5]; // 폭탄 : dep가 a일 때 (b,c)의 효능(int)과 품질(char)

void input(){
    cin >> n;
    for(int i=0;i<n;i++){
        for(int j=0;j<4;j++){
            for(int k=0;k<4;k++){
                cin >> A[i][0][j][k];
            }
        }
        for(int j=0;j<4;j++){
            for(int k=0;k<4;k++){
                cin >> B[i][0][j][k];
            }
        }
    }

    for(int i=0;i<3;i++){
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                bomb[i][j][k] = {0, 'W'};
            }
        }
    }

    color_value['R'] = 7, color_value['B'] = 5, color_value['G'] = 3, color_value['Y'] = 2, color_value['W'] = 0;
}

void make_rotate_arr(int idx){
    for(int i=1;i<4;i++){
        for(int j=0;j<4;j++){
            for(int k=0;k<4;k++){
                A[idx][i][k][3-j] = A[idx][i-1][j][k];
                B[idx][i][k][3-j] = B[idx][i-1][j][k];
            }
        }
    }
}

void add_bomb(int dep, int idx, int x, int y, int rotate_cnt){
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            bomb[dep][i][j].first = 0;
            bomb[dep][i][j].second = 'W';
        }
    }

    for(int i=0;i<4;i++){
        for(int j=0;j<4;j++){
            bomb[dep][x+i][y+j] = {A[idx][rotate_cnt][i][j], B[idx][rotate_cnt][i][j]};
        }
    }
}

void dfs(int dep){
    if(dep == 3){   
        int sum = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                int A_sum = 0;
                for(int k=0;k<3;k++){
                    A_sum += bomb[k][i][j].first;
                    if(A_sum < 0) A_sum = 0;
                    if(A_sum > 9) A_sum = 9;
                }
                char temp = 'W';
                for(int k=0;k<3;k++) if(bomb[k][i][j].second != 'W') temp = bomb[k][i][j].second; 
                sum += color_value[temp] * A_sum;
            }
        }
        ans = max(ans, sum);
        return;
    }

    for(int i=0;i<n;i++){
        if(visited[i]) continue;
        visited[i] = true;

        // 좌측 상단으로 정할 위치
        for(int j=0;j<2;j++){
            for(int k=0;k<2;k++){
                for(int l=0;l<4;l++){ // 몇번 돌릴 건가
                    add_bomb(dep, i, j, k, l);
                    dfs(dep+1);
                }
            }
        }

        visited[i] = false;
    }
}

void solve(){
    for(int i=0;i<n;i++) make_rotate_arr(i);

    dfs(0);

    cout << ans;
}

int main(){
    cin.tie(0)->sync_with_stdio(0);
    // freopen("input.txt", "r", stdin);
    
    input();
    solve();

    return 0;
}