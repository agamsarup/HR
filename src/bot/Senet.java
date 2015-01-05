package bot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Senet {

	public static boolean charBefore(String s, int pos, char self)
	{
		while(--pos> 0)
		{
			if(s.charAt(pos) == self)
				return true;
		}
		
		return false;
	}
	
	public static int isMovePossible(String s, int pos, int move)
	{
		
		// priority of moves highest to lowest 10 to 1, 0 is move not possible
		// 10 - exit from place >= 26
		// 9 - replace opponent after 26 when 27 is empty
		// 9 - replace opponent before 26 
		// 7 - move to empty position at 29
		// 6 - move to empty position at 28 or 30
		// 2 - move to empty position before 26 TODO
			// 2 - if opon before 2
			// 3 - if opon before 1/3
			// 4 - if opon before 4/5
			// 5
		// 1 - swap from 26 to 28-30 TODO
			// 2- swap to 29
			// 1 - swap to 28,30
		
		int npos=pos+move;
		if(npos > 31)
		{
//			return false;
			return 0;
		}
		if(pos >= 26 && npos == 31)
		{
			return 10;
//			return true;
		}
		
		char posChar=s.charAt(pos);
		char nPosChar=s.charAt(npos);
		
		char oponChar='X';
		if(posChar == 'X')
			oponChar='O';
		
		if(pos == 26)
		{
			if(move == 1)
			{
				return 0;
//				return false;
			}
			if(nPosChar=='.')
			{
				if(move==2 || move==4)
					return 6;
				else
					return 7;
//				return true;
			}
			else if(nPosChar==posChar)
			{
				return 0;
//				return false;
			}
			else
			{
				if(s.charAt(27)=='.')
					return 9;
				else
				{
//					if(npos==29)
//						return 2;
//					else 
						return 1;
				}
//				return true;
				
//				// npos occupied by opponent's piece
//				if(s.charAt(27)=='.')
//					return true;
			}
						
		}
		else
		{
			if(npos > 26)
			{
				return 0;
//				return false;
			}
			else {
				if (nPosChar == '.') {
//					return 2;
					if(s.charAt(npos-2) == oponChar)
						return 2;
					else if(s.charAt(npos-1) == oponChar || s.charAt(npos-3) == oponChar)
						return 3;
					else if(s.charAt(npos-4) == oponChar || s.charAt(npos-5) == oponChar)
						return 4;
					else
						return 5;
//					return true;
				} else if (nPosChar == posChar)
				{
					return 0;
//					return false;
				}
				else {
					if (s.charAt(npos - 1) == nPosChar
							&& s.charAt(npos + 1) == nPosChar)
					{
						return 0;
//						return false;
					}
					else
					{
//						if(pos > 1 && oponChar==s.charAt(pos-1) && oponChar==s.charAt(pos+1) && charBefore(s, pos-1, posChar))
//							return 2;
//					else
						return 9;
//						return true;
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char player=br.readLine().trim().charAt(0);
		
		int points = Integer.parseInt(br.readLine());
		
		String[] board = new String[3];
		
		for(int i=0;i<3;i++)
			board[i]=br.readLine().trim();
		
		String s = "."+board[0]+new StringBuilder(board[1]).reverse().toString()+board[2];
		
//		System.out.println(s);
		
		int[] moveScore = new int[31];
		int bestPos=0;
		int bestScore=0;
		for(int i=30;i>=1;i--)
		{
			if(player==s.charAt(i)){
//				System.out.println(i);
//				break;
				moveScore[i]=isMovePossible(s, i, points);
//				if(i+points==31)
//					moveScore[i]=10;
//				else if(s.charAt(i+points)!='.')
//					moveScore[i]=8;
//				else
//					moveScore[i]=7;
				
				if(moveScore[i] > bestScore)
				{
					bestScore=moveScore[i];
					bestPos=i;
				}
			}
		}
		System.out.println(bestPos);
	}

}
