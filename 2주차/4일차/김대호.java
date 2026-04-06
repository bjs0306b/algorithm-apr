import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int buildingCount = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty() && stack.peek() > h) {
                stack.pop();
            }

            if (h > 0 && (stack.isEmpty() || stack.peek() < h)) {
                stack.push(h);
                buildingCount++;
            }
        }

        System.out.println(buildingCount);
    }
}