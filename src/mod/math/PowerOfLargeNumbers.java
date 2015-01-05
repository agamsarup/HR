package mod.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PowerOfLargeNumbers {

	int[] A;
	int[] B;
	
	public PowerOfLargeNumbers(int n)
	{
		A=new int[n];
		B=new int[n];
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		PowerOfLargeNumbers poln = new PowerOfLargeNumbers(100001);
		
		poln.run();
	}

	public long powMod(long n, long k, long m) {
		if (k == 0)
			return 1;
		if (k == 1)
			return n % m;
		if (k % 2 == 0) {
			long temp = powMod(n, k / 2, m);
			return (temp * temp) % m;
		} else {
			long temp = powMod(n, k / 2, m);
			temp = (temp * temp) % m;
			return (temp * n) % m;
		}
	}
	
	private void run() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		long m=1000000007;
		long n=1000000006;
		
		for(int tc=0;tc<t;tc++)
		{
			String[] ab = br.readLine().trim().split(" ");
			
			int al=ab[0].length();
			int bl=ab[1].length();
			
			for(int i=0;i<al;i++)
			{
				A[i]=ab[0].charAt(i)-'0';
			}
			for(int i=0;i<bl;i++)
			{
				B[i]=ab[1].charAt(i)-'0';
			}
			
			long moda=A[0]%m;
			long modb=B[0]%n;
			
			for(int i=1 ; i< al ; i++)
	        {
	            moda = (10*moda + A[i] )% m; 
	        }
			
			for(int i=1 ; i< bl ; i++)
	        {
	            modb = (10*modb + B[i] )% n; 
	        }
			
			System.out.println(powMod(moda, modb, m));
		}
	}

}
