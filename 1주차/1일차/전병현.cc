#include <iostream>
#include <memory.h>
#include <string>
#include <vector>
#include <stack>
#include <queue>
#include <algorithm>
#include <set>
#include <unordered_map>
#include <unordered_set>
using namespace std;
typedef long long int ll;

ll n, k, q;
void f(ll x, pair<ll, ll> &st) {
	ll p = 1;
	ll d = 1;
	x--;
	while (x >= p) {
		x -= p;
		p *= k;
		d++;
	}
	st.first = d;
	st.second = x;
}

int main() {
	cin.tie(0);cout.tie(0)->sync_with_stdio(0);
	cin >> n >> k >> q;
	for (int i = 0; i < q; i++) {

		pair<ll, ll> a[2];
		ll x, y;
		cin >> x >> y;
		if (k == 1) {
			cout << abs(x - y) << "\n";
			continue;
		}
		f(x, a[0]);
		f(y, a[1]);
		if (a[0].first > a[1].first) {
			swap(a[0], a[1]);
		}
		int cnt = 0;
		while (a[0].first < a[1].first) {
			a[1].first--;
			a[1].second /= k;
			cnt++;
		}
		while (a[0].second != a[1].second) {
			a[0].second /= k;
			a[1].second /= k;
			cnt+=2;
		}
		cout << cnt << "\n";
	}
}
