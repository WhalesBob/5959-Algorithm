import java.io.*;

public class BOJ1802 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        
        for(int t = 0; t < T; t++){
            int[] inputArray = makeArray(br);
            bw.write(canFold(inputArray, 0, inputArray.length - 1) ? "YES\n" : "NO\n");
          // 입력을 받아 정수 배열을 만든 뒤, canFold 메소드를 통해 접을 수 있는지 없는지 판단하고 결과를 출력합니다.
        }
        
        bw.flush();
    }
    static int[] makeArray(BufferedReader br) throws IOException{
        char[] charArray = br.readLine().trim().toCharArray();
        int[] intArray = new int[charArray.length];
        for(int i = 0; i < intArray.length; i++){
            intArray[i] = charArray[i] - '0';
        }
        return intArray;
    }
  // 재귀적으로, 종이를 접을 수 있는지 판단하는 메소드입니다. 
    static boolean canFold(int[] array, int start, int end){
        if(start == end){ // start 와 end 가 같으면, true 를 리턴합니다.
            return true;
        }

      // 중간 값을 가지고, s(start), e(end) 두 변수를 만들어 array[s] == array[e] 인지 비교해 줍니다.
      // array[mid] 가 0이든 1이든 상관없이, array[s] 와 array[e] 가 달라야 겹칠 수 있습니다. 
      // 중간 부분을 접고, 모든 부분이 겹친다면, 다음 중간을 접기 위해 재귀함수를 사용하여 판단할 수 있습니다.
        int mid = (start + end) / 2;
        for(int s = 0; s < mid; s++){
            int e = (end - s);
            if(array[s] == array[e]){
                return false;
            }
        }

        return canFold(array, start, mid - 1);
    }
}
