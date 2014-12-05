package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FillTheTank {

	public static int gcd(int a, int b)
	{
	    while(b > 0)
	        b ^= a ^= b ^= a %= b;
	    return a;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String bq[]=br.readLine().split(" ");
		int b,q;
		b=Integer.parseInt(bq[0]);
		q=Integer.parseInt(bq[1]);
		
		int[] buckets=new int[b+1];
		String[] s=br.readLine().split(" ");
		
		for(int i=1;i<=b;i++)
			buckets[i]=Integer.parseInt(s[i-1]);
		
		int[][] gcd= new int[b+1][b+1];
		
		for(int i=1;i<=b;i++)
		{
			for(int j=1;j<=b;j++)
			{
				gcd[i][j]=gcd(buckets[i], buckets[j]);
			}
		}
		
		int query;
		boolean done;
		int q1,q2;
		while(--q >=0)
		{
			query=Integer.parseInt(br.readLine().trim());
			done =false;
			for(int i=1;i<=b;i++)
			{
				q1=query%buckets[i];
				for(int j=1;j<=b;j++)
				{
					q2=query%buckets[j];
					if(q1%gcd[i][j]==0 || q2%gcd[i][j]==0)
					{
						done=true;
						break;
					}
				}
				if(done)
					break;
			}
			if(done)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}

}
