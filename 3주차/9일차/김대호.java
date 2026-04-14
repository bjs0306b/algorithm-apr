import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] plum = new int[T + 1];
        for (int i = 1; i <= T; i++) {
            plum[i] = Integer.parseInt(br.readLine());
        }

        // dp[시간][이동횟수]
        int[][] dp = new int[T + 1][W + 1];

        for (int t = 1; t <= T; t++) {
            int currentPlumPos = plum[t];

            for (int w = 0; w <= W; w++) {
                int myPos = (w % 2 == 0) ? 1 : 2;
                
                int score = (myPos == currentPlumPos) ? 1 : 0;

                if (w == 0) {
                    dp[t][w] = dp[t - 1][w] + score;
                } else {
                    dp[t][w] = Math.max(dp[t - 1][w], dp[t - 1][w - 1]) + score;
                }
            }
        }

        int ans = 0;
        for (int w = 0; w <= W; w++) {
            ans = Math.max(ans, dp[T][w]);
        }

        System.out.println(ans);
    }
}