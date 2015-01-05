package gameTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VerticalRooks {

	int[] A;
	int[] B;
	
	public VerticalRooks(int size) {
		A = new int[size];
		B = new int[size];
	}
	
	public void calc() throws NumberFormatException, IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		
		for(int tc=0;tc<t;tc++)
		{
			int n = Integer.parseInt(br.readLine());
			for(int i=0;i<n;i++)
				A[i]=Integer.parseInt(br.readLine().trim());
			for(int i=0;i<n;i++)
				B[i]=Integer.parseInt(br.readLine().trim());
			
			int nimsum=0;
			
			for(int i=0;i<n;i++)
			{
				nimsum^=Math.abs(A[i]-B[i]);
//				if(Math.abs(A[i] - B[i]) > 1) {
//	                nimsum ^= 1;
//	            }
			}
			
			if(nimsum > 0)
			{
				System.out.println("player-2");
//				System.out.println("player-1");
			}
			else
			{
				System.out.println("player-1");
//				System.out.println("player-2");
			}
		}

		

	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		
		VerticalRooks vr = new VerticalRooks( 2000);
		vr.calc();
	}

}
