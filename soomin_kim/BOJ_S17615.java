import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_S17615 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String[] balls = st.nextToken().split("");

        // 배열을 오른쪽부터 왼쪽으로 탐색
        String lastBall = balls[balls.length-1]; //제일 오른쪽 공의 색 저장
        int rightSame = 0; // 같은 색 count하는 변수
        int rightDifferent = 0; // 다른 색 count하는 변수
        for(int i = balls.length-2; i >=0; i--){
            if(lastBall.equals(balls[i]) && rightDifferent > 0){ // 다른색을 한 번이라도 만난 후부터 같은 색 count 하기! 그전 같은 색들은 굳이 옮길 필요가 없기 때문이다.
                rightSame++;
            }
            else if(!lastBall.equals(balls[i])){
                rightDifferent++;
            }
        }

        // 배열을 왼쪽부터 오른쪽으로 탐색 / 위의 오른쪽부터 왼쪽으로 탐색하는 로직과 동일하다.
        int leftSame = 0;
        int leftDifferent = 0;
        String firstBall = balls[0];
        for(int i = 1; i<balls.length; i++){
            if(firstBall.equals(balls[i]) && leftDifferent > 0){
                leftSame++;
            }
            else if(!firstBall.equals(balls[i])){
                leftDifferent++;
            }
        }
        int result = 0; // 결과값 변수
        if(rightDifferent != 0) { // 다른 색이 없으면 => 0 출력
            int sameCount = Math.min(leftSame, rightSame); // 같은 색 비교(왼쪽부터 탐색 경우 vs 오른쪽부터 탐색 경우)
            int differentCount = Math.min(leftDifferent, rightDifferent); // 다른 색 비교(왼쪽부터 탐색 경우 vs 오른쪽부터 탐색 경우)

            result = Math.min(sameCount, differentCount); // 같은 색 vs 다른 색 비교
        }

        System.out.println(result);
    }
}
