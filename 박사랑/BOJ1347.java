import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ1347 {

    static int N;
    static char[] op;
    static int[] dr={1,0,-1,0}; // 남 서 북 동 (오른쪽 R 방향)
    static int[] dc={0,-1,0,1};
    static char[][] map;
    static int dir=0;
    static ArrayList<Integer> d=new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N=Integer.parseInt(br.readLine());
        op=new char[N];
        op=br.readLine().toCharArray();

        int min_r=0, min_c=0, max_r=0, max_c=0;
        int now_r=0, now_c=0; // 기준 위치

        for(int i=0;i<N;i++){
            if(op[i]=='R') { // 오른쪽으로 방향 꺾기
                dir = (dir + 1) % 4;
            }
            else if (op[i]=='L') { // 왼쪽으로 방향 꺾기
                dir = dir==0 ? 3:dir-1;
            }
            else { // 전진
                d.add(dir);
                now_r+=dr[dir];
                now_c+=dc[dir];
                min_r=Math.min(min_r,now_r);
                max_r=Math.max(max_r,now_r);
                min_c=Math.min(min_c,now_c);
                max_c=Math.max(max_c,now_c);
            }
        }
        // map 배열 크기 구해주기
        int r_size=max_r-min_r+1;
        int c_size=max_c-min_c+1;
        map=new char[r_size][c_size];
        // 현재 위치 구하기
        int r=-min_r;
        int c=-min_c;
        // map 채우기
        map[r][c]='.';
        for(int i:d){
            r+=dr[i];
            c+=dc[i];
            map[r][c]='.';
        }
        // 정답 출력
        StringBuilder ans=new StringBuilder();
        for(int i=0;i<r_size;i++){
            for(int j=0;j<c_size;j++){
                if(map[i][j]=='.'){
                    ans.append('.');
                }else {
                    ans.append('#');
                }
            }
            ans.append("\n");
        }
        System.out.println(ans);
    }
}
