import java.io.*;

public class boj1347 {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        br.readLine();
        char[] move = br.readLine().toCharArray();
        
        char[][] miro = new char[101][101];
        for (int i=0; i<101; i++) {
            for (int j=0; j<101; j++) {
                miro[i][j] = '#';
            }
        }
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};
        
        int x = 50, y = 50; // 초기 좌표
        int d = 2; // 초기 방향 (북쪽)
        miro[x][y] = '.';
        
        for (char m: move) {
            // 오른쪽
            if (m == 'R') {
                d = (d-1 >= 0)? d-1: 3;
                continue;
            }
            
            // 왼쪽
            if (m == 'L') {
                d = (d+1 < 4)? d+1: 0;
                continue;
            }
            
            // 앞쪽
            x += dx[d];
            y += dy[d];
            miro[x][y] = '.';
        }
        
        // 미로 인덱스 찾기
        int startX = 50, startY = 50, endX = 50, endY = 50;
        for (int i=0; i<101; i++) {
            for (int j=0; j<101; j++) {
                if (miro[i][j] == '.') {
                    startX = Math.min(startX, i);
                    startY = Math.min(startY, j);
                    endX = Math.max(endX, i);
                    endY = Math.max(endY, j);
                }
            }
        }
        
        // 출력
        for (int i=startX; i<=endX; i++) {
            for (int j=startY; j<=endY; j++) {
                sb.append(miro[i][j]);
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }
    

}