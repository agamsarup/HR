package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BrokenKeyboard {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t=Long.parseLong(br.readLine());
		
		String broken;
		String word;
		int repair;
		int[] freq;
		int brokenKeys;
		int wordLength;
		while(--t>=0)
		{
			broken=br.readLine();
			word=br.readLine();
			
			freq=new int[26];
			
			wordLength=word.length();
			for(int i=0;i<wordLength;i++)
			{
				freq[word.charAt(i)-'a']++;
			}
			
			repair=0;
			brokenKeys=broken.length();
			for(int i=0;i<brokenKeys;i++)
			{
				if(freq[broken.charAt(i)-'a'] > 0)
					repair++;
			}
			
			System.out.println(repair);
		}
	}

}
