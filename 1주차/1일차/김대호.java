import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (K == 1) {
                sb.append(Math.abs(x - y)).append("\n");
                continue;
            }

            long distance = 0;
            while (x != y) {
                if (x > y) {
                    x = (x - 2) / K + 1;
                } else {
                    y = (y - 2) / K + 1;
                }
                distance++;
            }
            sb.append(distance).append("\n");
        }

        System.out.print(sb.toString());
    }
}