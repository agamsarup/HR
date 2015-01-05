package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Flowers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] nk = br.readLine().trim().split(" ");
		int n=Integer.parseInt(nk[0]);
		int k=Integer.parseInt(nk[1]);
		
		String[] cost=br.readLine().trim().split(" ");
		
		int[] iCost = new int[n];
		
		for(int i=0;i<n;i++)
			iCost[i]=Integer.parseInt(cost[i]);
//		Comparator<Integer> comparator = new Comparator<Integer>() {
//
//	        @Override
//	        public int compare(Integer o1, Integer o2) {
//	            return o2.compareTo(o1);
//	        }
//	    };
//	    
//		Arrays.sort(iCost,comparator);
		Arrays.sort(iCost);
		int temp;
		int length=iCost.length;
		for( int i = 0; i < length/2; ++i ) 
	    { 
	      temp = iCost[i]; 
	      iCost[i] = iCost[length - i - 1]; 
	      iCost[length - i - 1] = temp; 
	    }
		
//		System.out.println(Arrays.toString(iCost));
		long costSum = 0;
		for(int i=0;i<n;i++)
		{
//			System.out.println(i/k);
			costSum = costSum + iCost[i] * (i/k + 1);
		}
		
		System.out.println(costSum);
	}
}
