package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Anagram {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine().trim());
		
		for(int tc=0;tc<t;tc++)
		{
			String str = br.readLine();
			int length=str.length();
			if(length % 2==1)
				System.out.println(-1);
			else
			{
				int halfSize=length/2;
				int[] freq = new int[26];
				for(int i=0;i<halfSize;i++)
				{
					freq[str.charAt(i)-'a']++;
				}
				for(int i=halfSize;i<length;i++)
				{
					freq[str.charAt(i)-'a']--;
				}
				int sum=0;
				for(int i=0;i<26;i++)
					sum+=Math.abs(freq[i]);
				
				System.out.println(sum/2);
			}
		}

	}

}
