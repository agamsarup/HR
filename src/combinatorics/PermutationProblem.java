package combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mod.math.PowerOfLargeNumbers;

public class PermutationProblem {

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
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		String[] nk;
		int n,k;
		long res;
		int mod=1000000007;
		int base;
		int p;
		while (--t >= 0) {
			nk=br.readLine().split(" ");
			n=Integer.parseInt(nk[0]);
			k=Integer.parseInt(nk[1]);
			res=0;
			base=10;
			if(k >= n/10f)
			{
				res=9;
				n--;
				if(k-1 > 0)
				{
					p=Math.min(k-1, n);
					res=(res*powMod(base, p, mod))%mod;
					n-=p;
				}
				base--;
				while(n>0)
				{
					p=Math.min(k, n);
					res=(res*powMod(base, p, mod))%mod;
					base--;
					n-=p;
				}
			}
			System.out.println(res);
		}
	}

}
