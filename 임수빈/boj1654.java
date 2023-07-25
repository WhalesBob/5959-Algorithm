import java.io.*;
import java.util.*;

public class boj1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        // 이미 가지고 있는 랜선의 개수
        int k = Integer.parseInt(st.nextToken());
        // 필요한 랜선의 개수
        int n = Integer.parseInt(st.nextToken());

        long start = 1;
        long end = 0;

        // 이미 가지고 있는 랜선의 길이
        int [] lines = new int[k];
        for (int i=0; i<k; i++) {
            int line = Integer.parseInt(br.readLine());
            lines[i] = line;

            // 최댓값 찾기
            if (end < line) {
                end = line;
            }
        }
        end++;

        long answer = 0;
        while (start <= end) {
            long cut = 0;
            long mid = (start + end) / 2;

            // 자르기
            for (int line:lines) {
                cut += line / mid;
            }

            if (cut >= n) { // 자를 범위 늘리기
                answer = mid;
                start = mid + 1;
            } else { // 자를 범위 줄이기
                end = mid - 1;
            }
        }

        bw.write(answer + "\n");
        bw.flush();
        bw.close();
    }
}
