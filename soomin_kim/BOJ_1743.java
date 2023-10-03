import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1743 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 세로 길이
        int m = Integer.parseInt(st.nextToken()); // 가로 길이
        int k = Integer.parseInt(st.nextToken()); // 음쓰 개수

        List<int[]> list = new ArrayList<>(); // 음쓰 위치 저장 list
        // 1. 입력 받기
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int[] pos = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            list.add(pos);
        }

        //2. bfs 탐색
        Queue<int[]> q = new ArrayDeque<>();
        int count; // 인접한 음식물 갯수
        int max = 0; // 가장큰 음식물 갯수를 위함...
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int j = 0; j < list.size(); j++) {
            // 3. 음식물 탐색 시이작!
            q.add(list.get(j));
            list.remove(j--); // 탐색한 음쓰는 list에서 제거
            count = 1; // 이 음쓰 부터 탐색하니까 크기는 1이즹

            while (!q.isEmpty()) {

                int[] now = q.poll();
                int r = now[0]; 
                int c = now[1];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dx[i];
                    int nc = c + dy[i];
                    int[] next = new int[]{nr, nc};

                    if (nr < 1 || nr > n || nc < 1 || nc > m) continue;

                    int index = -1; // list에 같은 거 있으면 인덱스 기억해서 삭제할라고

                    // 음식물이 있는 위치인지 검사
                    for (int idx = 0; idx < list.size(); idx++) {
                        if (list.get(idx)[0] == nr && list.get(idx)[1] == nc) {
                            index = idx;
                            break;
                        }
                    }

                    if (index == -1) continue;
                    
                    // 음식물 위치라면?
                    q.add(next);
                    count++;
                    list.remove(index); // 탐색했으니 제거
                }
            }
            max = Math.max(max, count); // 최대값 구하기

        }
        System.out.println(max);
    }
}
