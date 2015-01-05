package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PlayGame {

	public static void method2() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine().trim());
		
		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine().trim());
		
			String[] bricks=br.readLine().trim().split(" ");
			int[] brickVal=new int[n+1];
			for(int i=0;i<n;i++)
			{
				brickVal[i]=Integer.parseInt(bricks[i]);
			}
			
			long[] dp = new long[n+3];
			dp[n] = dp[n+1] = dp[n+2]=0;
			long sum = 0; 
	        for (int i = n - 1; i >= 0; --i)
	        { 
	              sum += brickVal[i]; 
	              dp[i] = sum - Math.min(Math.min(dp[i + 1], dp[i + 2]), dp[i + 3]); 
	        }
	        
	        System.out.println(dp[0]);
		}
	}
	
	public static void method1() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine().trim());
		
		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine().trim());
			
			String[] bricks=br.readLine().trim().split(" ");
			int[] brickVal=new int[n];
			for(int i=n-1;i>=0;i--)
			{
				brickVal[i]=Integer.parseInt(bricks[n-i-1]);
			}
			long[] sum = new long[n+1];
			sum[0]=0;//brickVal[0];
			for(int i=1;i<n;i++)
			{
				sum[i]=sum[i-1]+brickVal[i-1];
			}
			
			long[] dp = new long[n+1];
			dp[0]=0;
			for(int i=1;i<=3;i++)
			{
				dp[i]=sum[i];
//				System.out.println("dp["+i+"]="+dp[i]);
			}
			
			for(int i=4;i<=n;i++)
			{
				long val1 = sum[i-1]-dp[i-1]+brickVal[i-1];
				long val2 = sum[i-2]-dp[i-2]+brickVal[i-1]+brickVal[i-2];
				long val3 = sum[i-3]-dp[i-3]+brickVal[i-1]+brickVal[i-2]+brickVal[i-3];
//				System.out.println("v1="+val1+" v2="+val2+" v3="+val3);
				dp[i]=Math.max(val1, val2);
				dp[i]=Math.max(val3, dp[i]);
//				System.out.println("dp["+i+"]="+dp[i]);
			}
			
			System.out.println(dp[n]);
		}

	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		method1();
	}

}
