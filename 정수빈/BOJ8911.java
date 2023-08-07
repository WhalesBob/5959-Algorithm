import java.util.*;
import java.io.*;

public class BOJ8911 {
	// 4방향 저장 (시계 방향)
	// x : 1 0 -1 0
	// y : 0 1 0 -1
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
			
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			char[] commend = br.readLine().toCharArray();

      // 기본 값 위로 보고 있는 거북이
			int index_x = 0;
			int index_y = 0;
			int location = 0;

      // 최대 최소 저장
			int max_x = 0;
			int max_y = 0;
			int min_x = 0;
			int min_y = 0;
			
			// 다니면서 x, y의 최댓값 저장
			for(int i=0; i<commend.length; i++) {
        // (x+dx[i], y+dy[i]) -> F (+), B (-)
				if(commend[i] == 'F') {
					index_x += dx[location];
					index_y += dy[location];
					
					if(index_x > max_x) max_x = index_x;
					if(index_x < min_x) min_x = index_x;
					if(index_y > max_y) max_y = index_y;
					if(index_y < min_y) min_y = index_y;
					
				}
				else if(commend[i] == 'B') {
					index_x -= dx[location];
					index_y -= dy[location];
					
					if(index_x > max_x) max_x = index_x;
					if(index_x < min_x) min_x = index_x;
					if(index_y > max_y) max_y = index_y;
					if(index_y < min_y) min_y = index_y;
					
				}
        // location -> L (-), R (+)
				else if(commend[i] == 'L')
					location = location-1 < 0? 3 : location-1;
				else // R
					location = (location+1)%4;
			}
      // 차이 값을 계산해 곱셈
			sb.append(Math.abs(max_x-min_x) * Math.abs(max_y-min_y)+"\n");
		}
		System.out.println(sb.toString());
	}
}
