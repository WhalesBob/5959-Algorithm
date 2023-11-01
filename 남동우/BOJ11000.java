import java.io.*;
import java.util.*;
public class BOJ11000 {
    static final long SEPARATOR = (long)Math.pow(10,10);
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int classCount = Integer.parseInt(br.readLine());

        PriorityQueue<Long> inputQueue = new PriorityQueue<>();
        PriorityQueue<Integer> classQueue = new PriorityQueue<>();
      // 시작 타이밍을 우선적으로 비교하고, 시작 타이밍이 같은 경우 종료 타이밍으로 비교하기 위해 PriorityQueue 를 생성합니다
      // (inputQueue)
      // 교실의 마지막 이용시간을 기준으로 비교하기 위해 , PriorityQueue 를 생성합니다. (classQueue)

      // 입력을 받아, 시작 시간과 종료 시간을 하나의 long 형 정수로 만들어 inputQueue 에 넣어줍니다.
        for(int i = 0; i < classCount; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inputQueue.add(getKey(start, end));
        }

      // inputQueue 에 element 가 있다면, classQueue 에 요소를 넣어 줍니다.
      // inputClass 의 로직은 아래에서 설명합니다.
        while(!inputQueue.isEmpty()){
            inputClass(classQueue, inputQueue.poll());
        }
      // classQueue 의 size 를 정답으로 출력합니다.
        System.out.println(classQueue.size());
    }
  // 시작/종료 시간이 10^9 이하이므로, 10의 10제곱을 기준으로 시작 시간과 종료 시간을
  // 하나의 long 형 정수로 바꿔 돌려주는 메소드입니다.
    static long getKey(int start, int end){
        return (long)(SEPARATOR * start + end);
    }
  // 넣고자 하는 강의를 넣어 주는 메소드입니다.
    static void inputClass(PriorityQueue<Integer> classQueue, long element){
        int start = (int)(element / SEPARATOR); // 시작 시간과 종료 시간을 element 에서 추출합니다.
        int end = (int)(element % SEPARATOR);

      // classQueue 가 비어 있지 않으면서, classQueue 의 가장 위에 있는 강의 끝 시간이
      // 현재 강의를 시작해야 하는 강의 시간보다 작을 때, 해당 교실을 사용할 수 있는 것입니다.
      // 어차피, 데이터가 정렬되어 들어가기 때문에, 이 데이터 뒤에오는 데이터는 시작 시간이나 종료 시간이
      // 해당 데이터의 시작/종료 시간보다 뒤에 오는 것일 것입니다. 
      // 충분히 Greedy 하게 문제를 바라볼 수 있으므로, 단순히 Greedy 하게 문제를 풀어 줍니다.
        if(!classQueue.isEmpty() && classQueue.peek() <= start){
            classQueue.poll();
        }

      // classQueue 에 end 시간을 넣어 줍니다.
        classQueue.add(end);
    }
}
