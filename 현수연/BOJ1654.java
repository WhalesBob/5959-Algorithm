package java_test;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k, n;
		k=sc.nextInt(); // 가지고 있는 랜선 개수 입력
		n=sc.nextInt(); // 필요한 랜선 개수 입력
		int[] arr=new int[k];
		for(int i=0;i<k;i++)
			arr[i]=sc.nextInt(); // 랜선의 길이 입력
		Arrays.sort(arr);
		long mid, low, high;
		low=1; high=arr[k-1];
		int max_length=-1; // 실제 우리가 구할 입력 최대값
		while(low<=high) {
			mid = (low+high)/2;
			int num_sum=0;
			for(int i=0;i<k;i++)
				num_sum+=(arr[i]/mid);
			if(num_sum<n)
				high=mid-1;
			else {
				if(max_length<mid)
					max_length=(int)mid;
				low=mid+1;
			}
		}
		System.out.println(max_length);
		sc.close();
	}
}
