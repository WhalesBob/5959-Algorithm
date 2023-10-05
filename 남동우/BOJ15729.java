import java.io.*;
import java.util.StringTokenizer;

public class BOJ15729 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] originalArray = new int[n]; // 바꿀 배열을 초기화해 줍니다. 그냥 선언만 해도
      // 디폴트가 0 배열입니다. 
        int[] toMakeArray = makeArray(br, n); // 배열을 입력받아 옵니다. 

        int count = 0;
      // 바꾸는 횟수를 0으로 초기화합니다.
        for(int i = 0; i < originalArray.length; i++){
          // 제일 왼쪽에서부터 하나씩 같은지 다른지 확인합니다.
          // 바꾸고자 하는 배열과, 참고할 배열 숫자가 다를 때, 해당 숫자의 오른쪽 2개를 포함해
          // 3개의 숫자를 바꿉니다.
          // 이후 count 를 하나 증가시킵니다.
            if(originalArray[i] != toMakeArray[i]){
                changeArray(originalArray, i); 
                count++;
            }
        }

      // count 를 출력합니다.
        System.out.println(count);
    }
    static void changeArray(int[] array, int start){
      // start 에서부터 오른쪽으로 3개를 바꿉니다. 
      // ArrayIndexOutOfBoundException 이 나지 않도록, i 가 array 밖으로 
      // 나가지 않도록 처리해 줍니다.
        for(int i = start; i < start + 3 && i < array.length; i++){
            array[i] = (array[i] + 1) % 2;
        }
    }
    static int[] makeArray(BufferedReader br, int size) throws IOException{
        int[] array = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i < array.length; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }
        return array;
    }
}
