package combinatorics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JourneyToMars {

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
	
//	public static int getFirstKDigit2PowerBigDecimal(int n,int k)
//	{
//		BigDecimal log2= new BigDecimal("0.30102999566398119521373889472449");
//		BigDecimal val = log2.multiply(new BigDecimal(n));
//	    BigDecimal mantissa = val.subtract(new BigDecimal(val.toBigInteger()));
//	    BigInteger ten = new BigInteger("10");
//	    BigInteger answer = ten.p;
//	    double limit = Math.pow(10, k-1);
//	    while (answer < limit)
//	        answer*=10;
//	    return (int) answer;
//	}  
	
	public static int getFirstKDigit2Power(int n,int k)
	{
		double log2=0.30102999566398119521373889472449d;
		System.out.println("log2="+log2);
		System.out.println(Math.log10(2));
		log2=Math.log10(2);
	    double val = n*log2;
	    double mantissa = val - (int) val;
	    double answer = Math.pow(10, mantissa);
	    double limit = Math.pow(10, k-1);
	    while (answer < limit)
	        answer*=10;
	    return (int) answer;
	}   		
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++)
		{
			String[] nk = br.readLine().trim().split(" ");
			
			int n=Integer.parseInt(nk[0]);
			int k=Integer.parseInt(nk[1]);
			n=n-1;
			long mod=(long) Math.pow(10, k);
			long lastDigits=powMod(2, n, mod);
//			System.out.println("lastDigits="+lastDigits);
//			double N1,N2;
//			double log2=0.30102999566398119521373889472449;
//			N1 = n*log2 ;
//	        N1 = N1 - (long)N1;
//	        N2 = Math.pow(10,N1);
//	        N2 = N2*Math.pow(10,k-1);
	        
//	        System.out.println((int)N2+lastDigits);
	        System.out.println(getFirstKDigit2Power(n, k)+lastDigits);
		}

	}

}
