import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        char[][] map = new char[H][W];
        
        int[][][] dist = new int[4][H][W];
        int[][] targets = new int[2][2];
        int targetIdx = 0;

        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    targets[targetIdx++] = new int[]{i, j};
                }
                for (int d = 0; d < 4; d++) {
                    dist[d][i][j] = Integer.MAX_VALUE;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0}; // 상, 하, 좌, 우
        int[] dc = {0, 0, -1, 1};

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[3] - b[3]);

        int startR = targets[0][0];
        int startC = targets[0][1];
        int endR = targets[1][0];
        int endC = targets[1][1];

        for (int d = 0; d < 4; d++) {
            dist[d][startR][startC] = 0;
            pq.add(new int[]{startR, startC, d, 0});
        }

        int result = Integer.MAX_VALUE;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int dir = curr[2];
            int mirrors = curr[3];

            if (r == endR && c == endC) {
                result = Math.min(result, mirrors);
                continue;
            }

            if (dist[dir][r][c] < mirrors) continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W || map[nr][nc] == '*') continue;

                int nextMirrors = (dir == d) ? mirrors : mirrors + 1;

                if (dist[d][nr][nc] > nextMirrors) {
                    dist[d][nr][nc] = nextMirrors;
                    pq.add(new int[]{nr, nc, d, nextMirrors});
                }
            }
        }

        System.out.println(result);
    }
}