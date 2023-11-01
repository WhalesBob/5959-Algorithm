import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ20056 {

    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] d = {{0, 2, 4, 6}, {1, 3, 5, 7}};

    static class Fireball {
        int r;
        int c;
        int idx;
        int mass;
        int speed;
        int dir;
        int flag; // 0:겹치지 않음 1:겹침->같은방향 2:겹침->다른방향
        int cnt; // 겹치는 갯수

        public Fireball(int r, int c, int idx, int mass, int speed, int dir, int flag, int cnt) {
            this.r = r;
            this.c = c;
            this.idx = idx;
            this.mass = mass;
            this.speed = speed;
            this.dir = dir;
            this.flag = flag;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 배열 크기
        M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
        K = Integer.parseInt(st.nextToken()); // 이동 횟수

        ArrayList<Fireball> balls = new ArrayList<>();

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            balls.add(new Fireball(r, c, r * N + c, m, s, d, 0, 1));
        }

        while (K-- > 0) {
            // 방향 d로 s만큼 이동
            for (Fireball b : balls) {
                b.r = b.r + b.speed * dr[b.dir];
                b.r = (b.r + b.speed * N) % N;
                b.c = b.c + b.speed * dc[b.dir];
                b.c = (b.c + b.speed * N) % N;
                b.idx = b.r * N + b.c;
            }

            // 이동이 끝난 뒤 2개 이상의 파이어볼이 있는지
            balls.sort((o1, o2) -> o1.idx - o2.idx);
            for (int i = 0; i < balls.size() - 1; i++) {
                Fireball b1 = balls.get(i);
                Fireball b2 = balls.get(i + 1);
                if (b1.idx == b2.idx) { // 자리가 겹친다
                    b1.mass += b2.mass;
                    b1.speed += b2.speed;
                    b1.cnt++;
                    if (b1.flag != 2) {
                        if (b1.dir % 2 == b2.dir % 2) { // 방향이 같다
                            b1.flag = 1;
                        } else { // 방향이 다르다
                            b1.flag = 2;
                        }
                    }
                    balls.remove(i + 1);
                    i--;
                }
            }

            // 파이어볼이 4개로 나누어진다
            int size = balls.size();
            for (int now = 0; now < size; now++) {
                Fireball b = balls.get(now); //  현재 인덱스 꺼내
                if (b.flag != 0) {
                    if (b.mass / 5 > 0) { // 질량이 0이상
                        int m = b.mass / 5;
                        int s = b.speed / b.cnt;
                        for (int j = 0; j < 4; j++) { // 4개로 쪼개진다
                            int dd = b.flag == 1 ? 0 : 1;
                            balls.add(new Fireball(b.r, b.c, b.idx, m, s, d[dd][j], 0, 1));
                        }
                    }
                    balls.remove(b);
                    now--;
                    size--;
                }
            }
        }
        // 정답 구하기
        int ans = 0;
        for (Fireball b : balls) {
            ans += b.mass;
        }
        System.out.println(ans);
    }
}
