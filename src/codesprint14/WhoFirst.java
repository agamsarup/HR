package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WhoFirst {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		
		int freq[]=new int[26];
		
		int length=s.length();
		int maxAlength=0;
		for(int i=0;i<length;i++)
		{
			freq[s.charAt(i)-'a']++;
		}
		int aFreq=freq[0];
		if(aFreq>0)
			maxAlength=1;
		else
		{
			System.out.println("a");
			return;
		}
		int curAlength=1;
		
		for(int i=1;i<length;i++)
		{
			if(s.charAt(i)=='a')
			{
				if(s.charAt(i-1) == 'a')
					curAlength++;
				else
				{
					if(curAlength>maxAlength)
					{
						maxAlength=curAlength;
					}
					curAlength=1;

				}
			}
		}
//		System.out.println(maxAlength);
		if(curAlength>maxAlength)
		{
			maxAlength=curAlength;
		}
//		System.out.println(maxAlength);
		StringBuilder sb = new StringBuilder("");
		for(int i=0;i<=maxAlength;i++)
			sb.append('a');
		System.out.println(sb.toString());
	}

}
