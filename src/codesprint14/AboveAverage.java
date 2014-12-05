package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboveAverage {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t=Long.parseLong(br.readLine());
		
		String[] s;
		int length;
		int[] score;
		float avg;
		int numAboveAvg;
		while(--t>=0)
		{
			s=br.readLine().split(" ");
			length=Integer.parseInt(s[0]);
			
			score=new int[length+1];
			avg=(float) 0;
			numAboveAvg=0;
			for(int i=1;i<=length;i++)
			{
				score[i]=Integer.parseInt(s[i]);
				avg+=score[i];
			}
			
			avg/=length;
			
			for(int i=1;i<=length;i++)
			{
				if(score[i] > avg)
					numAboveAvg++;
			}
			
			System.out.println(numAboveAvg);
		}
	}

}
