package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LonelyInteger {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine());
		
		String[] data = br.readLine().trim().split(" ");
		int[] iData=new int[n];
		for(int i=0;i<n;i++)
			iData[i]=Integer.parseInt(data[i]);
		
		int[] count = new int[101];
		for(int i=0;i<n;i++)
			count[iData[i]]++;
		
		for(int i=0;i<101;i++)
		{
			if(count[i]==1)
			{
				System.out.println(i);
				break;
			}
		}
	}

}
