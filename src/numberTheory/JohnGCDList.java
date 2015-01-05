package numberTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class JohnGCDList {

	public static int gcd(int a, int b) {
		if (a < 0)
			a = -a;
		if (b < 0)
			b = -b;

		return (b > 0) ? gcd(b, a % b) : a;
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine().trim());

		int n;
		String[] data;
		int[] idata;
		while(--tc>=0)
		{
			n = Integer.parseInt(br.readLine().trim());
			data=br.readLine().trim().split(" ");
			idata=new int[data.length];
			
			for(int i=0;i<n;i++)
			{
				idata[i]=Integer.parseInt(data[i]);
			}
			
			System.out.print(idata[0]+" ");
			for(int i=0;i<n-1;i++)
				System.out.print(idata[i]*idata[i+1]/gcd(idata[i],idata[i+1])+" ");
			System.out.println(idata[n-1]);
		}
	}

}