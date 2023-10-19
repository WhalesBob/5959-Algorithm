import java.util.*;

public class BOJ1347 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int N = sc.nextInt();

        // F : 전진, L : 좌, R : 우

        // 하 좌 상 우
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};

        int[][] map = new int[101][101];
        int x = 50;
        int y = 50;
        map[x][y] = 1;
        int location = 0;
        int[] minmaxW = new int[]{50,50};
        int[] minmaxH = new int[]{50,50};

        for(char c : sc.next().toCharArray()) {
            switch(c) {
                case 'F':
                    x += dx[location];
                    y += dy[location];
                    map[x][y] = 1;

                    if(minmaxH[0] > x) minmaxH[0] = x;
                    if(minmaxH[1] < x) minmaxH[1] = x;
                    if(minmaxW[0] > y) minmaxW[0] = y;
                    if(minmaxW[1] < y) minmaxW[1] = y;

                    break;
                case 'R':
                    location = location==3 ? 0 : location+1;
                    break;
                case 'L':
                    location = location==0 ? 3 : location-1;
                    break;
                default:
                    break;
            }
        }

        // 출력
        for(int i=minmaxH[0]; i<=minmaxH[1]; i++) {
            for(int j=minmaxW[0]; j<=minmaxW[1]; j++)
                if(map[i][j]==0)
                    sb.append('#');
                else
                    sb.append('.');

            sb.append('\n');
        }

        System.out.print(sb.toString());
    }
}
