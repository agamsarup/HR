package dp.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RedJohnIsBack {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr=new int[41];
		arr[0]=1;
		arr[1]=1;
		arr[2]=1;
		arr[3]=1;
		
		for(int i=4;i<=40;i++)
		{
			arr[i]=arr[i-1]+arr[i-4];
		}
		int M=arr[40];
//		System.out.println("M="+M);
		int[] marked= new int[M];
		marked[0]=1;
		marked[1]=0;
		int p=2;
		int multiple=4;
		while (p <= M) {
			while (multiple <= M) {
				marked[multiple - 1] = 1;
				multiple += p;
			}
			p=p+1;
			if(p > M)
				break;
			while(p<= M && marked[p-1]==1)
				p++;
			multiple=2*p;
		}
		int[] numPrimes=new int[M];
		numPrimes[0]=0;
		numPrimes[1]=1;
		for(int i=3;i<=M;i++)
		{
			numPrimes[i-1]=numPrimes[i-2]+1-marked[i-1];
		}
		
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<t;i++)
		{
			int n=Integer.parseInt(br.readLine());
//			System.out.println("n="+n+" arr[n-1]="+arr[n-1]);
			System.out.println(numPrimes[arr[n]-1]);
		}
		
	}

}
/*
testcase 7
19
5
35
25
8
37
12
17
38
14
38
14
29
31
26
20
30
25
17
36

2
4522
269
4
8078
9
32
10794
15
10794
15
816
1432
354
68
1077
269
32
6048
*/