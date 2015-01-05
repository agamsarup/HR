package diff.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfThrones2 {

	public static long factMod(int n, int mod)
	{
		long res=1;
		while(n > 0)
		{
			res=(res*n)%mod;
			n--;
		}
		
		return res;
	}
	
	public static long powMod(long n, long mod){
	    long res = 1;
	    int m = (int)(mod - 2);
	    while(m > 0){
	        if(m % 2==1){
	            res = (res * n) % mod;
	            --m;
	        }
	        m >>= 1;
	        n = (n * n) % mod;
	    }
	    return res;
	}
	
//	public static long inverseMod(long n, long mod)
//	{
//		return powMod(a, mod-2);
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		
		int[] freq = new int[26];
		int length=s.length();
		
		for(int i=0;i<length;i++)
		{
			freq[s.charAt(i)-'a']++;
		}
		
		long denom=1;

		int mod=1000000007;
		for(int i=0;i<26;i++)
		{
//			freq[i]/=2;
			denom=(denom*factMod(freq[i]/2, mod))%mod;
		}
//		System.out.println("denom="+denom);
		long num = factMod(length/2, mod);
//		System.out.println("num="+num);
		
		System.out.println((num*powMod(denom, mod))%mod);
		
	}

}
