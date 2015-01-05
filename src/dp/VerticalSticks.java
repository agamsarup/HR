package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class VerticalSticks {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		DecimalFormat twoDForm = new DecimalFormat("#.00");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine().trim());

		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine().trim());
			
			int[] stickLen=new int[n];
			int[] large=new int[n];
			String[] s=br.readLine().split(" ");
			for(int i=0;i<n;i++)
			{
				stickLen[i]=Integer.parseInt(s[i]);
			}
			
			double sum=0;
			for(int i=0;i<n;i++)
			{
				int count=0;
				
				for(int j=0;j<n;j++)
				{
					if(stickLen[i] <= stickLen[j])
						count++;
				}
				
				sum+=(n+1)/(count+1.0);
			}
			
//			System.out.println(Math.round(sum*100.0)/100);
			System.out.println(twoDForm.format(sum));
		}
		
	}

}
