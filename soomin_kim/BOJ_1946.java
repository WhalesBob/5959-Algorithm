import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        //테스트케이스
        for (int tc = 1; tc <= t; tc++) {
            int n = Integer.parseInt(br.readLine());

            int[][] scores = new int[n][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                scores[i][0] = Integer.parseInt(st.nextToken());
                scores[i][1] = Integer.parseInt(st.nextToken());

            }

            // 정렬 => comparator
            Arrays.sort(scores, new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    // 서류점수를 기준으로 오름차순함? => 등수니까 높은 등수순으로 정렬함
                    return o1[0] - o2[0];
                }
            });

            int interview = scores[0][1]; // 서류 점수 1등의 면접점수

            int count = 1;

            for (int i = 1; i <n; i++) {
                // 면접등수가 다른 지원자보다 낮은지(등수가) 아닌지 판단해야함 => 서류는 정렬했으니까 꼴찌빼면 됨
                if (scores[i][1] < interview) {
                    interview = scores[i][1];
                    count++;
                }

            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
