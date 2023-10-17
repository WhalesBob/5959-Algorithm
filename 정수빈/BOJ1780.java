import java.util.*;
import java.io.*;

public class BOJ1780 {
    static int[] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        count = new int[3];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0; j<N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 분할 정복
        // 종이가 모두 같은 수로 되어 있으면 그대로 사용한다
        // 그렇지 않은 경우 같은 크기의 9개로 자르고, 각각에 대해 반복한다

        cut(map, 0,0,N);

        for(int n : count)
            System.out.println(n);
    }

    // 9분할 커팅한다
    static void cut(int[][] map, int x, int y, int n) {
        if(check(map,x,y,n)) return;

        cut(map, x, y, n/3);
        cut(map, x, y+n/3, n/3);
        cut(map, x, y+(n/3)*2, n/3);

        cut(map, x+n/3, y, n/3);
        cut(map, x+n/3, y+n/3, n/3);
        cut(map, x+n/3, y+(n/3)*2, n/3);

        cut(map, x+(n/3)*2, y, n/3);
        cut(map, x+(n/3)*2, y+n/3, n/3);
        cut(map, x+(n/3)*2, y+(n/3)*2, n/3);
    }

    // 범위 내 종이 패턴이 같은지 확인한다
    static boolean check(int[][] map, int x, int y, int n) {
        int value = map[x][y];

        for(int i=x; i<x+n; i++)
            for(int j=y; j<y+n; j++)
                if(value != map[i][j]) return false;

        count[value+1] += 1;
        return true;
    }
}
