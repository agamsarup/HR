package mod.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SalaryBlues {

	public static long gcd(long a, long b) {
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;

		return (b > 0) ? gcd(b, a % b) : a;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String nq[] = br.readLine().trim().split(" ");
		int n=Integer.parseInt(nq[0]);
		int q=Integer.parseInt(nq[1]);

		String[] data=br.readLine().trim().split(" ");
		long[] iData=new long[n];
		
		for(int i=0;i<n;i++)
		{
			iData[i]=Long.parseLong(data[i]);
		}
		
		long totGcd =gcd(iData[1]-iData[0],iData[2]-iData[0]);
		
		for(int i=3;i<n;i++)
		{
			totGcd=gcd(totGcd,iData[i]-iData[0]);
		}
		
		for(int i=0;i<q;i++)
		{
			long k=Long.parseLong(br.readLine().trim());
			System.out.println(gcd(totGcd, iData[0]+k));
		}
	}

}
