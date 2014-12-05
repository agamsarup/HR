package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Towers {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n=Long.parseLong(br.readLine());
		int k=Integer.parseInt(br.readLine());
		
		String[] arr= br.readLine().split(" ");
		int[] iArr=new int[k];
		Set<String> set = new TreeSet<String>();
		for(String s: arr)
			set.add(s);
		
		Iterator<String> itr=set.iterator();
		int i=0;
		while(itr.hasNext())
		{
			iArr[i]=Integer.parseInt(itr.next());
			i++;
		}
		
		long mod=1000000007;
		long ways=0;
		long q;
		long r;
		long cur;
		long max=(long) Math.pow(2, k);
		for(int j=1;j<=max;j++)
		{
			cur=j;
			if(cur%2==1)
			{
				
			}
		}
	}

}
