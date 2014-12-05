package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AlternatingCharacters {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		String s;
		int length;
		int i;
		int ans;
		while(--t>=0)
		{
			s=br.readLine();
			length=s.length();
			i=0;
			ans=0;
			while(++i<length)
			{
				if(s.charAt(i) == s.charAt(i-1))
					ans++;
			}
			
			System.out.println(ans);
		}
	}

}
