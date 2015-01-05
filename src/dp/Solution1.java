package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1 {
	public class Pair
	{
		int a;
		int b;
		
		public Pair(int a, int b)
		{
			this.a=a;
			this.b=b;
		}
		
		@Override
		public String toString() {
			return a+" "+b;
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine().trim());

		Solution1 is = new Solution1();
		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine().trim());
			int[] bs = new int[n];
			List<Pair> intervals = new ArrayList<Pair>(n);
			
			for(int i=0;i<n;i++)
			{
				String[] ab=br.readLine().trim().split(" ");
				bs[i]=Integer.parseInt(ab[1]);
				intervals.add(is.new Pair(Integer.parseInt(ab[0]), bs[i]));
			}
			
			Arrays.sort(bs);
//			System.out.println(Arrays.toString(bs));
			for(int i=0;i<n;i++)
			{
				int numCovered=0;
				int bToDelete = -1;
//				int aToDelete = -1;
				int pairindexToDelete=-1;
				
				int size=intervals.size();
				for(int j=0;j<size;j++)
				{
					Pair p = intervals.get(j);
					if(p.a <= bs[i] && p.b >= bs[i])
					{
						numCovered++;
						if(p.b > bToDelete)
						{
							bToDelete=p.b;
//							aToDelete=p.a;
							pairindexToDelete=j;
						}
					}
				}
				
				if(numCovered > 2)
				{
//					System.out.println("numCovered="+numCovered);
//					System.out.println("delete "+aToDelete+" "+bToDelete);
					intervals.remove(pairindexToDelete);
//					System.out.println(intervals.toString());
					if(numCovered > 3)
						i--;
				}
			}
			
			System.out.println(intervals.size());
//			for(int i=0;i<intervals.size();i++)
//			{
//				System.out.println(intervals.get(i).a+" "+intervals.get(i).b);
//			}
		}

	}

}
