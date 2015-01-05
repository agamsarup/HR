package diff.string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommonChild {

	public static int lcs(String s1, String s2)
	{
		int l1=s1.length();
		int l2=s2.length();
		
		int[][] lcs = new int[l1+1][l2+1];
		
		for(int i=0;i<l1;i++)
		{
			for(int j=0;j<l2;j++)
			{
				if(s1.charAt(i) == s2.charAt(j))
					lcs[i+1][j+1]=lcs[i][j]+1;
				else
					lcs[i+1][j+1]=Math.max(lcs[i+1][j], lcs[i][j+1]);
			}
		}
		
		return lcs[l1][l2];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String str1 = br.readLine().trim();
		String str2 = br.readLine().trim();
		
		System.out.println(lcs(str1, str2));

	}

}
