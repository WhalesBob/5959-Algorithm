package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj_2002 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		String[]in=new String[n];
		String[]out=new String[n];
		int[] chk=new int[n];
		
		for(int i=0;i<n;i++) {
			in[i]=br.readLine();//들어가는 순서
		}
		for(int i=0;i<n;i++) {
			out[i]=br.readLine();//나오는 순서
		}
		//여기까지 입력받음
		
		int badcar=0;//추월한 차 카운팅 할 변수 초기화
		
		for(int i=0;i<out.length;i++) {//out 순서의 배열을 기준으로 반복문
			boolean badbe=false;//반복문 탈출을 위한 flag
			for(int j=0;j<in.length;j++) {//in 순서의 차와 비교 위한 반복문
				if(out[i].equals(in[j])) {//현재 차의 in[] 순서 index 확인
					for(int k=0;k<j;k++) {//현재 차보다 in[] 먼저 한 차들 out했는지 확인!
						if(chk[k]!=1) {//먼저 들어간 차 들 중 한 차라도 나오지 않았다면
							badcar++;//추월한걸로~
							badbe=true;
							break;
						}
					}
					chk[j]=1;//해당차 나온거 체크
				}
				if(badbe==true) {
					break;
				}
			}
		}
		System.out.println(badcar);
		
	}

}
