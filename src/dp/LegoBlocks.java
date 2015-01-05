package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LegoBlocks {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine().trim());
		
		for(int tc=0;tc<t;tc++)
		{
			String[] nm=br.readLine().trim().split(" ");
			
			int n=Integer.parseInt(nm[0]);
			int m=Integer.parseInt(nm[1]);
			
			int[][] ways=new int[n][m];
			int[][] allWays=new int[n][m];
			
			ways[0][0]=1;
//			allWays[0][0]=1;
			for(int i=1;i<m;i++)
			{
				ways[0][i]=2*ways[0][i-1];
//				allWays[0][i]=2*allWays[0][i-1];
//				System.out.println("ways["+0+"]["+i+"]="+ways[0][i]);
				if(i==3)
					break;
			}
			for(int i=4;i<m;i++)
			{
				ways[0][i]=ways[0][i-1]+ways[0][i-2]+ways[0][i-3]+ways[0][i-4];
				if(ways[0][i] >= 1000000007)
					ways[0][i]=ways[0][i]%1000000007;
//				allWays[0][i]=allWays[0][i-1]+allWays[0][i-2]+allWays[0][i-3];
//				System.out.println("ways["+0+"]["+i+"]="+ways[0][i]);
			}
			
			for(int i=1;i<n;i++)
			{
				ways[i][0]=1;
//				allWays[i][0]=1;
//				System.out.println("ways["+i+"]["+0+"]="+ways[i][0]);
			}
			
//			System.out.println("init done");
			
			for(int j=0;j<m;j++)
			{
				long temp=ways[0][j];
				for(int i=0;i<n;i++)
				{
					ways[i][j]=(int) temp;
					temp*=ways[0][j];
					if(temp >= 1000000007)
						temp%=1000000007;
				}
			}
				
			for(int i=1;i<n;i++)
			{
				for(int j=1;j<m;j++)
				{
					ways[i][j]=(int) Math.pow(ways[0][j],(i+1));
					System.out.println("ways["+i+"]["+j+"]="+ways[i][j]);
					System.out.println();
					for(int k=0;k<j;k++)
					{
//						ways[i][j]-=allWays[i][j-1-k]*allWays[i][k];
						ways[i][j]-=ways[i][j-1-k]*ways[i][k];
						System.out.println("ways["+i+"]["+(j-1-k)+"]="+ways[i][j-1-k]);
						System.out.println("ways["+i+"]["+k+"]="+ways[i][k]);
						System.out.println("ways["+i+"]["+j+"]="+ways[i][j]);
//						System.out.println();
					}
					System.out.println();
				}
			}
			
			System.out.println(ways[n-1][m-1]);
		}
	}

}
