import java.util.*;

public class BOJ9935 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputString = sc.next();
		String compareString = sc.next();
        
		String result = getLastString(inputString, compareString); // 위에서 입력 문자열과 폭발 문자열을 받아, 남은 결과를 받습니다.
		System.out.println(result.length() == 0 ? "FRULA" : result); 
    // 위의 결과에서, 문자열 길이가 0일 때 "FRULA" 을 출력하고, 아니면 받은 결과를 리턴합니다.
	}
	static String getLastString(String input, String compare) {
		CustomStack<Character> inputStack = new CustomStack<>(); // 아래에 Stack 클래스의 자식 클래스 CustomStack 을 만들어 선언해 줍니다. 
		CustomStack<Character> compareStack = new CustomStack<>(); // 문자열을 비교할 compare 도 스택화 하여 객체 선언합니다.
		
		for(char c : compare.toCharArray()) {
			compareStack.add(c);
		}
		
		for(char c : input.toCharArray()) {
			inputStack.add(c); 
			if(CustomStack.containsInStack(inputStack, compareStack)) { 
        // 만약에 inputStack 맨 위에 compareStack 이 모두 포함되어 있다면, 아래에서 for 문을 돌면서 compareStack 갯수만큼 
        // inputStack 에서 빼 줍니다.
        
				for(int i = 0; i < compareStack.size() && !inputStack.isEmpty(); i++) {
					inputStack.pop();
				}
			}
		}
		
		return inputStack.getAllandMakeString();
    // inputStack 요소를 모두 빼어, 문자열로 만들어 리턴해 줍니다.
	}
}
class CustomStack<E> extends Stack<E>{

  // Stack 의 부모 Vector 에 정의되어 있는 protected Object[] elementData를 사용하기 위해 자식 클래스를 만듭니다.
  // 아래는, elementData 에서 인덱스를 바탕으로 요소를 빼 주는 메소드입니다.
	Character getElementUsingIndex(int index){
		if(this.elementData[index] instanceof Character) {
			return (char)this.elementData[index];
		}
		return null;
	}

  // compare 스택이 stack 의 윗부분에 모두 포함되어있는지 보는 method 입니다.
	static boolean containsInStack(CustomStack stack, CustomStack compare) {

    // 비교 스택이 받은 스택보다 작으면, false 를 돌려줘야 합니다. 당연히 compare 의 모든 요소를 포함할 수 없습니다.
		if(stack.size() < compare.size()) {
			return false;
		}

    // 스택의 맨 위에서부터, compare 의 크기까지 돌면서, compare 스택 요소의 값이 모두 포함되어 있는지 확인하는 for 문입니다.
		for(int i = 0; i < compare.size() && i < stack.size(); i++) {
			int compareIndex = compare.size() - i - 1;
			int stackIndex = stack.size() - i - 1;
			if(!stack.getElementUsingIndex(stackIndex).equals(compare.getElementUsingIndex(compareIndex))) {
				return false;
			}
      // 맨 위에서부터 비교하며, compare stack의 끝까지, 하나라도 다르면 false 를 리턴합니다. for 문을 탈출하면, true 를 리턴합니다.
		}
		
		return true;
	}
	
	String getAllandMakeString() {
		StringBuilder builder = new StringBuilder();
		while(!this.isEmpty()) {
			builder.append(this.pop());
		}
		return builder.reverse().toString();
	}
  // 스택 내에 있는 요소를 전부 빼 builder 에 집어넣으면, 반대로 append 될 것입니다. 다시 reverse() 메소드를 써서 뒤집고, toString()
  // 으로 리턴해 줍니다.
}
