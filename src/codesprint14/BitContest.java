package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BitContest {

	public static int numSetBits(int n)
	{
		int ans=0;
		while(n>0)
		{
			ans+=n%2;
			n=n>>1;
		}
		return ans;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int n;
		int xorsum=0;
		while(--t>=0)
		{
			n = Integer.parseInt(br.readLine());
			xorsum^=numSetBits(n);
		}

		if(xorsum==0)
			System.out.println("The other player :(");
		else
			System.out.println("Shaka :)");
		
	}

}
