import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        
        int[][] doors = new int[2][2];
        int doorIdx = 0;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == '#') {
                    doors[doorIdx][0] = i;
                    doors[doorIdx][1] = j;
                    doorIdx++;
                }
            }
        }

        int startR = doors[0][0], startC = doors[0][1];
        int endR = doors[1][0], endC = doors[1][1];

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int[][][] dist = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
            }
        }

        // int[]: {행, 열, 방향, 거울개수}
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[3]));

        for (int d = 0; d < 4; d++) {
            int nr = startR + dr[d];
            int nc = startC + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != '*') {
                dist[nr][nc][d] = 0;
                pq.offer(new int[]{nr, nc, d, 0});
            }
        }

        int ans = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int d = curr[2];
            int cost = curr[3];

            if (cost > dist[r][c][d]) continue;
            if (r == endR && c == endC) {
                ans = Math.min(ans, cost);
                continue;
            }

            // 직진
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && map[nr][nc] != '*') {
                if (dist[nr][nc][d] > cost) {
                    dist[nr][nc][d] = cost;
                    pq.offer(new int[]{nr, nc, d, cost});
                }
            }

            // 거울 설치
            if (map[r][c] == '!') {
                for (int nextD : new int[]{(d + 1) % 4, (d + 3) % 4}) {
                    int nnr = r + dr[nextD];
                    int nnc = c + dc[nextD];
                    if (nnr >= 0 && nnr < N && nnc >= 0 && nnc < N && map[nnr][nnc] != '*') {
                        if (dist[nnr][nnc][nextD] > cost + 1) {
                            dist[nnr][nnc][nextD] = cost + 1;
                            pq.offer(new int[]{nnr, nnc, nextD, cost + 1});
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }
}