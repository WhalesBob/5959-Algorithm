import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class FireBall{
    int r;
    int c;
    int m;
    int s;
    int d;

    public FireBall(int r, int c, int m, int s, int d) {
        super();
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }


}

public class BOJ_20056 {

    static int N, M, K;
    static Queue<FireBall>[][] map;
    static ArrayList<FireBall> list; // map에 추가해야 할 파이어볼 정보들

    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new LinkedList[N][N];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[r][c].add(new FireBall(r, c, m, s, d));
        }

        for(int k=0; k<K; k++){

            // 현재 맵에서 어디에 파이어볼이 있는지 확인하여 list에 삽입
            findFireBalls();
            // list 바탕으로 맵 갱신
            updateMap();
            // 파이어볼 나누기
            divideFireBall();
            // list 바탕으로 맵 갱신
            updateMap();
        }

        int result = 0;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                while(!map[i][j].isEmpty()){
                    result += map[i][j].poll().m;
                }
            }
        }

        System.out.println(result);
    }

    static void findFireBalls(){
        list = new ArrayList<>();

        // 현재 맵에서 어디에 파이어볼이 있는지 확인하여 list에 삽입
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                // 파이어볼이 있는지 확인
                while(!map[i][j].isEmpty()){
                    // 이동정보를 업데이트 해야 하므로, map에서는 빼준다.
                    moveFireBall(map[i][j].poll());
                }
            }
        }
    }

    // 파이어볼의 이동정보를 임시 list에 넣음
    static void moveFireBall(FireBall ball){
        int nr = ball.r + (dx[ball.d] * ball.s) % N;
        int nc = ball.c + (dy[ball.d] * ball.s) % N;
        
        nr = nr < 0 ? N+nr : nr % N;
        nc = nc < 0 ? N+nc : nc % N;
        
        list.add(new FireBall(nr, nc, ball.m, ball.s, ball.d));
    }

    static void updateMap(){
        // list를 참고하여 map 업데이트
        for(int i=0; i<list.size(); i++){
            FireBall ball = list.get(i);
            map[ball.r][ball.c].add(new FireBall(ball.r, ball.c, ball.m, ball.s, ball.d));
        }
    }

    static void divideFireBall(){
        list = new ArrayList<>();

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if (map[i][j].size() >= 2){
                    int nm = 0; // 새로운 질량
                    int ns = 0; // 새로운 속도
                    int[] cnt = new int[2]; // [0]: 짝수 개수, [1]: 홀수 개수
                    int size = map[i][j].size();

                    while(!map[i][j].isEmpty()){
                        FireBall ball = map[i][j].poll();
                        nm += ball.m; ns += ball.s;

                        cnt[ball.d % 2 == 0 ? 0 : 1]++;
                    }

                    // 사라지는 파이어볼인 경우! 추가 할 필요 없음
                    if (nm < 5) continue;

                    ns /= size;
                    nm /= 5;
                    if (cnt[0] == size || cnt[1] == size){
                        for(int d=0; d<=6; d+=2)
                            list.add(new FireBall(i, j, nm, ns, d));
                    }
                    else {
                        for(int d=1; d<=7; d+=2)
                            list.add(new FireBall(i, j, nm, ns, d));
                    }
                }
            }
        }
    }
}

