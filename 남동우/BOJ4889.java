import java.io.*;
import java.util.Stack;

public class BOJ4889 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseCount = 0;
        while(true){
            String input = br.readLine();
            if(input.contains("-")){ // - 가 포함된 입력을 받으면 while 문을 탈출합니다.
                break;
            }

            caseCount++;
            System.out.printf("%d. %d\n", caseCount, getStableString(input));
        }
    }
    static int getStableString(String input){
        Stack<Character> stack = new Stack<>();
        for(char c : input.toCharArray()){
            if(!stack.isEmpty() && stack.peek() == '{' && c == '}'){
              // 스택이 비어있지 않으면서, stack 맨 위가 열려 있고, 새로 받는 것이 닫는 괄호이면
              // 스택에서 하나를 뺍니다. 위의 조건에 만족하지 않으면, 스택에 하나를 더합니다.
                stack.pop();
            }else{
                stack.add(c);
            }
        }
        int count = 0;
        while(!stack.isEmpty()){
            char element = stack.pop(); // 스택에서 하나를 뺍니다.
            if(element == '{' && stack.peek() == '}'){ 
              // 스택에서 뺀 요소가 '{' 이면서 맨 위 요소가 '}' 이면 미리 하나 늘려 줍니다.
                count++;
            }
            stack.pop(); // 나머지는 }} 아니면 {{ 같은 요소입니다. 하나 더 빼고 카운트를 하나 늘려 줍니다.
            count++;
        }
        return count; // 도출된 count 를 return 합니다.
    }
}
