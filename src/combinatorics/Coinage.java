package combinatorics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Coinage{

	public static Set<String> set;
	
	public static int[] denom = new int[]{1,2,5,10};
	
	public static long numPossRec(int coinCount[], int m, int n)
	{
		if(n==0)
			return 1;
		if(n<0)
			return 0;
		if(m <= 0 && n>=1)
			return 0;
	
		for(int i=0;i<m;i++)
			if(coinCount[i] < 0)
				return 0;
		
		int countBefore=coinCount[m-1];
		coinCount[m-1]--;
		long count1=numPossRec(coinCount, m, n-denom[m-1]);
		coinCount[m-1]++;
		
		long count2=numPossRec(coinCount, m-1, n);
		
		return count1+count2;
	}
	
	public static long numPoss(int n, int a, int b, int c, int d, Set<String> set) {
		
		String key=a+"."+b+"."+c+"."+d;
		if(set.contains(key))
			return 0;
		else
			set.add(key);
		if(n < 0 || a<0 || b< 0 || c<0 || d<0)
			return 0;
		if (n == 0 && a>=0 && b>= 0 && c>=0 && d>=0)
		{
			//System.out.println("a="+a+" b="+b+" c="+c+" d="+d);
//			if(!set.contains(a+"."+b+"."+c+"."+d))
//			{
//				set.add(a+"."+b+"."+c+"."+d);
//					return 1;
//			}
			return 1;
		}
		else
			return numPoss(n - 1, a - 1, b, c, d, set)
					+ numPoss(n - 2, a, b - 1, c, d, set)
					+ numPoss(n - 5, a, b, c - 1, d, set)
					+ numPoss(n - 10, a, b, c, d - 1, set);
	}

	public static long numPossFast(int n, int a, int b, int c, int d) {

		int[] arr = new int[n+1];
		arr[0]=1;
		int[] abcd = new int[]{a,b,c,d};
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<n-abcd[i];j++)
			{
				arr[j+abcd[i]]+=arr[j];
			}
		}
		for(int i=0;i<=n;i++)
		{
			System.out.println("arr="+arr[i]);
		}
		return arr[n];
	}
	
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());
		while (tc-- > 0) {
			int n = Integer.parseInt(br.readLine());

			String[] abcd = br.readLine().trim().split(" ");
			int a = Integer.parseInt(abcd[0]);
			int b = Integer.parseInt(abcd[1]);
			int c = Integer.parseInt(abcd[2]);
			int d = Integer.parseInt(abcd[3]);
			
			set=new HashSet<String>();
			
//			System.out.println(numPossFast(n, a, b, c, d));
//			System.out.println(numPoss(n, a, b, c, d,set));
			System.out.println(numPossRec(new int[]{a,b,c,d}, 4, n));
		}

	}

}
