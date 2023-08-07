package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class boj_8911 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		int[][]side= {{-1,0},{0,1},{1,0},{0,-1}};//북동남서
		Deque<int[]>q=new ArrayDeque<>();
		for(int i=0;i<side.length;i++) {
			q.offer(side[i]);
		}
		HashMap<Character, Integer>key=new HashMap<>();
		key.put('F', 1);
		key.put('B', -1);
		key.put('L', 2);
		key.put('R', 3);
		int max_x;
		int min_x;
		int max_y;
		int min_y;
		
		String[] input=new String[n];
		for(int i=0;i<n;i++) {
			int x=0,y=0;//초기 시작
			int[] look=Arrays.copyOf(q.peek(), q.peek().length);//북쪽으로 초기화
			input[i]=br.readLine();//컨트롤 값 입력
			max_x=0;
			min_x=0;
			max_y=0;
			min_y=0;
			for(int j=0;j<input[i].length();j++) {
				if(key.get(input[i].charAt(j))!=1 && key.get(input[i].charAt(j))!=-1) {//앞이나 뒤가 아닐 때
					if(key.get(input[i].charAt(j))==2) {//왼쪽
						int[]temp=Arrays.copyOf(q.peekLast(), q.peekLast().length);
						q.removeLast();
						q.offerFirst(temp);
						look=Arrays.copyOf(temp, temp.length);
					}else {//오른쪽
						int[]po=Arrays.copyOf(q.peek(),q.peek().length);
						q.poll();
						int[]temp=Arrays.copyOf(q.peek(), q.peek().length);
						look=Arrays.copyOf(temp, temp.length);
						q.offer(po);
					}
				}else {
					x=(key.get(input[i].charAt(j))*look[0])+x;
					y=(key.get(input[i].charAt(j))*look[1])+y;
					min_x=Math.min(min_x, x);
					max_x=Math.max(max_x, x);
					min_y=Math.min(min_y, y);
					max_y=Math.max(max_y, y);
				}
			}int result=(max_x-min_x)*(max_y-min_y);
				System.out.println(result);
			
		}
	}
}
