package mod.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SpecialMultiple {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t=Integer.parseInt(br.readLine().trim());
		
		for(int tc=0;tc<t;tc++)
		{
			int n=Integer.parseInt(br.readLine().trim());
			
			int i=1;
			while(true)
			{
				String binS = Integer.toBinaryString(i);
				
				String nBinS = binS.replace('1', '9');
				
				long j = Long.parseLong(nBinS);
				
				if(j%n==0)
				{
					System.out.println(nBinS);
					break;
				}
				
				i++;
			}
		}
	}

}
