import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] adj;
    static int[] order;
    static int[] targetOrder;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }

        targetOrder = new int[N];
        order = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            targetOrder[i] = Integer.parseInt(st.nextToken());
            order[targetOrder[i]] = i;
        }

        if (targetOrder[0] != 1) {
            System.out.println(0);
            return;
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i], (a, b) -> order[a] - order[b]);
        }

        if (checkBFS()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    static boolean checkBFS() {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();
        visited = new boolean[N + 1];

        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            result.add(curr);

            for (int next : adj[curr]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(next);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            if (result.get(i) != targetOrder[i]) {
                return false;
            }
        }
        return true;
    }
}