package csIndia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CuttingPaper {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		String[] mn;
		int m,n;
		int minSquare[];
		int area;
		int maxSquareSize;
		while (--t >= 0) {
			mn=br.readLine().split(" ");
			m=Integer.parseInt(mn[0]);
			n=Integer.parseInt(mn[1]);
			area=m*n;
			maxSquareSize=Math.min(n, m);
			minSquare=new int[area+1];
			minSquare[0]=0;
			for(int j=1;j<=area;j++)
			{
				
				minSquare[j]=minSquare[j-1]+1;
				for(int k=2;k<=maxSquareSize;k++)
				{
					if(j-k*k>=0)
					{
						if(minSquare[j-k*k]+1 <minSquare[j])
							minSquare[j]=minSquare[j-k*k]+1;
					}
					else
						break;
				}
				System.out.println("minSquare["+j+"]="+minSquare[j]);
			}
			System.out.println(minSquare[area]);
		}
	}

	private static int recCountSquare(int min, int max) {
		if (max % min == 0)
			return max / min;
		int q, r;
		q = max / min;
		r = max % min;
		return q + recCountSquare(r, min);

	}

}
