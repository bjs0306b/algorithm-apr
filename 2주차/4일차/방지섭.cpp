#include <bits/stdc++.h>
using namespace std;

int n;
vector<int> v;
void input(){
    cin >> n;
    v.resize(n+1);
    for(int i=0;i<n;i++){
        int a,b; cin >> a >> b;
        v[i] = b;
    }
    v[n] = 0;
}

void solve(){
    stack<int> st;
    int cnt = 0;
    for(int i=0;i<=n;i++){
        while(!st.empty() && st.top() > v[i]){
            st.pop();
            cnt++;
        }
        if(st.empty() || st.top() < v[i]) st.push(v[i]);
    }
    cout << cnt;
}

int main(){
    cin.tie(0)->sync_with_stdio(0);

    input();
    solve();

    return 0;
}