#include <iostream>
#include <vector>
#include <algorithm>
#include <cmath>

using namespace std;

int t, w, dp[1001][31]; // dp[x][y] = x번째에 y번 움직였을 때 받을 수 있는 자두의 최대 개수.
vector<int> v;
void input() {
	cin >> t >> w;
	v.resize(t+1); for (int i = 1; i <= t; i++) cin >> v[i];
}

void solve() {
	for (int i = 1; i <= t; i++) {
		for (int j = 0; j <= w; j++) {
			dp[i][j] = max(dp[i][j], dp[i - 1][j] + ((1 + j) % 2 == v[i] % 2));
			if (j > 0) dp[i][j] = max(dp[i][j], dp[i - 1][j - 1] + (j % 2 == v[i] % 2));
		}
	}
	int ans = 0;

	for (int i = 0; i <= w; i++) ans = max(ans, dp[t][i]);
	cout << ans;
}

int main() {
	cin.tie(0)->sync_with_stdio(0);

	input();
	solve();

	return 0;
}