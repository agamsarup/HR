package warmup.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author agam
 *
 */
public class GemStones {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		
		int[][] present = new int[n][26];
		for(int i=0;i<n;i++)
		{
			String s = br.readLine();
			for(int j=0;j<s.length();j++)
			{
				present[i][s.charAt(j)-'a']=1;
			}
		}
		int ans=0;
		for(int j=0;j<26;j++)
		{
			int add=1;
			for(int i=0;i<n;i++)
			{
				if(present[i][j]==0)
				{
					add=0;
					break;
				}
			}
			ans+=add;
		}
		System.out.println(ans);
	}

}
