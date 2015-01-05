package y14w11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SherlockSquare {

	public static long powMod(long n, long k, long m) {
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
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine().trim());
		long n;
		long mod = 1000000007;
		long sum;
		while(--tc>=0)
		{
			sum=4;
			n=Long.parseLong(br.readLine().trim());
			if(n==0)
				System.out.println(sum);
			else
			{
				sum=(sum+(2*(powMod(2, n, mod)-1+mod))%mod)%mod;
				System.out.println(sum);
				System.out.println((mod-1)%mod);
			}
		}
	}

}
