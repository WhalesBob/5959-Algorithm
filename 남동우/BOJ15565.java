import java.io.*;
import java.util.StringTokenizer;

public class BOJ15565 {
    static int left, right, min, ryan; 
  // 이중 포인터를 사용하기 위해 left,right 를 사용했고, 최소값, 라이언 갯수를 
  // 전역 변수로 선언했습니다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int size = Integer.parseInt(st.nextToken());
        int want = Integer.parseInt(st.nextToken());

        int[] array = new int[size];
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

      // 입력을 토대로 정답을 도출해 돌려줍니다.
        System.out.println(getAnswer(array, want));
    }
    static int getAnswer(int[] array, int want){
        left = 0; right = 0; min = Integer.MAX_VALUE; 
        ryan = array[0] == 1 ? 1 : 0;
// 왼쪽, 오른쪽 포인터와 최소값, 라이언 값을 초기화합니다.

      // left 가 right 보다 작거나 같고, right 가 배열 범위 안에 있도록 while 문을 설정합니다.
        while(left <= right && right < array.length){
            if(ryan >= want){
              // 라이언 갯수가 원하는 갯수보다 크거나 같을 때
              // left 포인터를 하나 증가시키며 관련 연산을 진행합니다.
                leftPlus(array);
            }else {
              // 라이언 갯수가 원하는 갯수보다 작을 때
              // right 값을 하나 증가시킵니다.
              // 다음 right 값 포인터에 라이언이 있다면, 라이언 갯수를 하나 증가시키고
              // right 값을 하나 늘려 줍니다.
                if (right + 1 < array.length && array[right + 1] == 1) {
                    ryan++;
                }
                right++;
            }
        }

      // 혹시나, ryan 갯수가 원하는 갯수보다 많은 상황에서 
      // right 값을 고정해 놓고 left 포인터를 오른쪽으로 당겼을 때
      // 최소값이 더 갱신될 가능성이 있다고 생각하여 
      // ryan 값이 want 값보다 더 큰 상황에서 계속 left 값을 오른쪽으로 당겨 갱신합니다.
        while(ryan >= want){
            leftPlus(array);
        }

      // 최종적으로, min 값이 한번도 업데이트되지 않았다면, -1
      // 업데이트된 이력이 있다면 min 값을 리턴하도록 합니다.
        return min == Integer.MAX_VALUE ? -1 : min;
    }
  // left 포인터를 오른쪽으로 당기는 메소드입니다. 
  // 중복되는 코드가 존재하여, 메소드 하나로 묶어 처리했습니다. 
    static void leftPlus(int[] array){
      // 현재 right - left + 1 이 min 보다 작다면, min 값을 업데이트합니다.
        if(right - left + 1 < min){
            min = right - left + 1;
        }

      // 기존의 left 포인터의 배열 값이 라이언이면, 라이언 갯수를 하나 감소시킵니다.
      // 이후, left 를 하나 증가시킵니다.
        if(array[left] == 1){
            ryan--;
        }
        left++;
    }
}
