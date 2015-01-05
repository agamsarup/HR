package mod.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution {
	
	private enum Player {
		Alice, Bob
	}

	Map<String , Boolean> dict;
	
	public Solution(int n)
	{
		dict = new HashMap<String, Boolean>((int) Math.pow(2, n));
	}
	
	public void correctMethod() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine());
			
			String[] data = br.readLine().trim().split(" ");
			
			char[] chars= new char[n];
			for(int i=0;i<n;i++)
				chars[i]=(char)(Integer.parseInt(data[i])+'a');
			
			String s = new String(chars);
//			String s = br.readLine().trim().replaceAll(" *", "");
//			System.out.println(s);
			System.out.println(recSolve(s, Player.Alice, Player.Bob));
			
		}
	}
	
	public Player recSolve(String s, Player curPlayer, Player secondPlayer) {
		if (dict.containsKey(s)) {
			Boolean canWin = dict.get(s);
			if (canWin)
				return curPlayer;
			else
				return secondPlayer;
		}

		if (isIncreasing(s)) {
			dict.put(s, false);
			return secondPlayer;
		}

		int length = s.length();
		for (int i = length - 1; i >= 0; i--) {
			// try all other possiblities
			StringBuilder sb = new StringBuilder(s);
			StringBuilder newS = sb.deleteCharAt(i);
			Player winner = recSolve(newS.toString(), secondPlayer, curPlayer);
			if (winner == curPlayer) {
				// mem[ln] = true;
				dict.put(s, true);
				return curPlayer;
			}
		}

		dict.put(s, false);
		return secondPlayer;
	}

	private boolean isIncreasing(String s) {
		int length=s.length();
		for (int i = length - 1; i > 0; i--) {
			if (s.charAt(i) < s.charAt(i-1))
				return false;
		}
		return true;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
			Solution pg = new Solution(15);
			pg.correctMethod();
	}

}
