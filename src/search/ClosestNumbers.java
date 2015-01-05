package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ClosestNumbers {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		String[] data = br.readLine().trim().split(" ");
		int[] iData=new int[n];
		for(int i=0;i<n;i++)
			iData[i]=Integer.parseInt(data[i]);

		Arrays.sort(iData);
		
		int minDst=Integer.MAX_VALUE;
		for(int i=0;i<n-1;i++)
		{
			int diff = iData[i+1]-iData[i];
			if(diff < minDst)
			{
				minDst=diff;
			}
		}
		
		for(int i=0;i<n-1;i++)
		{
			int diff = iData[i+1]-iData[i];
			if(diff==minDst)
				System.out.print(iData[i]+" "+iData[i+1]+" ");
		}
	}

}
