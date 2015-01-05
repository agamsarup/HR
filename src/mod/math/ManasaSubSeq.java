package mod.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ManasaSubSeq {

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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String s=br.readLine().trim();
		int length=s.length();
		
		long m= 1000000007;
		long sum=0;
		for(int i=0;i<length;i++)
		{
			sum=(sum+(((s.charAt(i)-'0')*powMod(2, i, m))%m*powMod(11, length-i-1, m))%m)%m;
		}
		
		System.out.println(sum);
	}

}
