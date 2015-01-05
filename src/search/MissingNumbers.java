package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MissingNumbers {


	public static void wrongMethod() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n1 = Integer.parseInt(br.readLine());
		
		String[] data1 = br.readLine().trim().split(" ");

		System.out.println("n1="+n1+" "+data1.length);
		
		int n2 = Integer.parseInt(br.readLine());

		
		String[] data2 = br.readLine().trim().split(" ");

		System.out.println("n2="+n2+" "+data2.length);
		
		Set<String> set1 = new HashSet<String>();
		Set<String> set2 = new HashSet<String>();
		
		for(int i=0;i<n1;i++)
			set1.add(data1[i]);
		for(int i=0;i<n2;i++)
			set2.add(data2[i]);
		
		set2.removeAll(set1);
		
		Object[] arr = set2.toArray();
		
		int size=arr.length;
		
		System.out.println(size);
		
		int[] iArr=new int[size];
		
		for(int i=0;i<size;i++)
			iArr[i]=Integer.parseInt((String)arr[i]);
		
		Arrays.sort(iArr);
		
		System.out.println(Arrays.toString(iArr));

	}
	
	public static void correctMethod() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n1 = Integer.parseInt(br.readLine());
		
		String[] data1 = br.readLine().trim().split(" ");
		int[] idata1=new int[n1];
//		System.out.println("n1="+n1+" "+data1.length);
		
		int n2 = Integer.parseInt(br.readLine());

		
		String[] data2 = br.readLine().trim().split(" ");
		int[] idata2=new int[n2];
//		System.out.println("n2="+n2+" "+data2.length);
		
		int min1=Integer.MAX_VALUE,min2=Integer.MAX_VALUE;
		
		for(int i=0;i<n1;i++)
		{
			idata1[i]=Integer.parseInt(data1[i]);
		}
		for(int i=0;i<n2;i++)
		{
			idata2[i]=Integer.parseInt(data2[i]);
		}
		for(int i=0;i<n1;i++)
		{
			if(idata1[i]<min1)
				min1=idata1[i];
		}
		for(int i=0;i<n2;i++)
		{
			if(idata2[i]<min2)
				min2=idata2[i];
		}
		
		int[] count1=new int[101];
		int[] count2=new int[101];
		for(int i=0;i<n1;i++)
		{
			count1[idata1[i]-min1]++;
		}
		
		for(int i=0;i<n2;i++)
		{
			count2[idata2[i]-min2]++;
		}
//		System.out.println("min1="+min1);
//		System.out.println("min2="+min2);
		int diff=min2-min1;
//		System.out.println("diff="+diff);
		if(diff>0)
		{
			for(int i=0;i<101-diff;i++)
			{
				if(count2[i] > count1[i+diff])
					System.out.print((i+min2)+" ");
			}
		}
		else
		{
			for(int i=diff;i<101;i++)
			{
//				System.out.println(count2[i]+" "+count1[i+diff]);
				if(count2[i] > count1[i+diff])
					System.out.print((i+min2)+" ");
			}
		}
			
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		correctMethod();
	}

}
