import java.io.*;
import java.util.*;

public class BOJ24429 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Line> inputQueue = new PriorityQueue<>();
        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            inputQueue.add(new Line(start, end));
        }
      // 먼저, 입력받은 판자들을 PriorityQueue 안에 넣어 정렬해 줍니다. 
      // 이후, ArrayDeque 를 활용해 겹치는 판자들을 모두 하나로 만들어 줍니다. 
      // 판자를 겹쳐서 하나의 판자로 처리하는 로직은, 메소드 안에서 설명하도록 하겠습니다.
      
        ArrayDeque<Line> deque = new ArrayDeque<>();

        while(!inputQueue.isEmpty()){
            putInStack(deque, inputQueue.poll());
        }

      // 겹치는 판자들을 모두 하나로 만든 뒤, 그 판자들을 활용하여 갈 수 있는 가장 먼 거리를 출력합니다.
        System.out.println(getPossiblePosition(deque));
    }

  // 입력받은 판자들을 활용하여 갈 수 있는 가장 먼 거리를 반환하는 메소드입니다. 
  // 재귀적으로 문제를 풀게 되면, StackOverFlow 가 발생할 수 있어 직접 Stack 을 활용해 문제를 해결했습니다.
    static int getPossiblePosition(ArrayDeque<Line> deque){

      // stack 을 선언하고, 첫번째 판자를 스택에 집어 넣은 뒤, 현재 스택 가장 위에 있는 값의 끝을 max 로 초기화합니다.
        Stack<Line> stack = new Stack<>();
        stack.push(deque.removeFirst());
        int max = stack.peek().end;

      // 입력받은 판자가 다 떨어지거나, 스택에 아무것도 남지 않는다면 while 문을 종료합니다.
        while(!deque.isEmpty() && !stack.isEmpty()){

          // 현재 스택 가장 위의 판자(현재 사람이 있는 판자) 길이보다 다음 판자와의 거리가 더 짧거나 같다면, 뛰어넘을 수 있습니다. 
          // 스택에 해당 판자를 추가해 주고, 갈 수 있었던 가장 먼 거리를 업데이트해 줍니다.

          // 만약, 갈 수 없다면, 그 전 판자에서 지금 판자를 뛰어넘어 갈 수 있는지 조사합니다. 
          // 이때, 단순히 현재 stack 에서 가장 위에 있는 판자를 빼 주고, 아직 도달하지 못한 판자까지 한 번에 갈 수 있는지만  
          // 조사하면 될 것입니다. 
          // deque 에 남아 있는 판자는, 아직 도달하지 못한 판자이고, stack 에 있는 판자는 지금까지 간 판자라고 생각하면 됩니다.
          // 도달하지 못했다면, 그 전 판자로 되돌아가, 아직 도달하지 못한 판자 중 가장 가까운 판자로 재시도하여 뛰어넘어 보는 것입니다.
          // 만약 stack 이 다 빌때까지 현재 도달하고자 하는 판자에 도달하지 못했다면, while 문이 탈출되어 현재 기록된 max 값을
          // 반환할 것입니다.
            if(stack.peek().getLineLength() >= deque.peekFirst().start - stack.peek().end){
                stack.add(deque.removeFirst());
                if(max < stack.peek().end){
                    max = stack.peek().end;
                }
            }else{
                stack.pop();
            }
        }

        return max;
    }

  // element 를 입력받아 현재 판자와 겹친다면 현재 판자의 길이를 늘려주어 하나로 합치고, 겹치지 않는다면
  // 판자 리스트에 새로 추가해 주는 메소드입니다. 
  // 결과적으로, deque 에는 겹치는 판자들을 모두 통합하고 겹치지 않는 판자들이 들어 있는 모양이 될 것입니다. 
    static void putInStack(ArrayDeque<Line> deque, Line element){
        if(deque.isEmpty() || deque.peekLast().end < element.start){
            deque.addLast(element);
            return;
        }

        deque.peekLast().end = Math.max(deque.peekLast().end, element.end);
    }
    static class Line implements Comparable<Line>{
        int start;
        int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getLineLength(){
            return this.end - this.start;
        }

        @Override
        public int compareTo(Line o) {
            if(this.start == o.start){
                return Integer.compare(this.end, o.end);
            }
            return Integer.compare(this.start, o.start);
        }
    }
}
