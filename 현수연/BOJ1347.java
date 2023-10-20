import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1347 {
	static int dx[] = {1,0,-1,0};
	static int dy[] = {0,-1,0,1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 작성한 움직임의 길이 및 움직임 내용 입력
		int N = Integer.parseInt(br.readLine());
		char[] in = br.readLine().toCharArray();
		// 홍준이의 방향 (아래를 보는 것이 기본 방향 : 0)
		int dir=0;
		// 홍준이의 위치 배열
		int x[] = new int[N+1];
		int y[] = new int[N+1];
		// 홍준이가 간 위치의 x,y 최소 최대값
		int minX=0, minY=0, maxX=0, maxY=0;
		// 홍준이의 움직임에 따라 위치 배열에 저장
		for(int i=1;i<=N;i++) {
			// 전진 시
			if(in[i-1]=='F') {
				// 이동과 동시에 위치 저장
				x[i]=x[i-1]+dx[dir];
				y[i]=y[i-1]+dy[dir];
			}
			// 방향만 이동
			else {
				// 우회전
				if(in[i-1]=='R') {
					dir=(dir+1)%4;
				}
				// 좌회전
				else {
					dir=(dir+3)%4;
				}
				// 현재 위치 저장
				x[i]=x[i-1];
				y[i]=y[i-1];
			}
			// 최소 최대값 계산
			minX=minX<x[i]?minX:x[i];
			minY=minY<y[i]?minY:y[i];
			maxX=maxX>x[i]?maxX:x[i];
			maxY=maxY>y[i]?maxY:y[i];
		}
		// 최소 최대값 이용하여 총 이동한 2차원 크기 계산
		int xSize=maxX-minX+1;
		int ySize=maxY-minY+1;
		// 계산한 크기로 map 생성
		char map[][] = new char[xSize+1][ySize+1];
		// 홍준이 위치 배열에 있는 곳 모두 .으로 저장
		for(int i=0;i<=N;i++) {
			map[x[i]-minX][y[i]-minY]='.';
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<xSize;i++) {
			for(int j=0;j<ySize;j++) {
				// 만약에 .이 아닐경우 #저장
				if(map[i][j]!='.')
					map[i][j]='#';
				// map 값 StringBuilder에 append
				sb.append(map[i][j]);
			}
			sb.append("\n");
		}
		// 최종 지도 출력
		System.out.println(sb);
	}
}
