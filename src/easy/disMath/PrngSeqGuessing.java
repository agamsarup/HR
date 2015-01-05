package easy.disMath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class PrngSeqGuessing {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] val = new int[10];
		int tc=Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			String[] stamps=br.readLine().trim().split(" ");
			long stamp1=Long.parseLong(stamps[0])-1;
			long stamp2=Long.parseLong(stamps[1]);
			
			int i=11;
			while(--i>0)
			{
				val[10-i]=Integer.parseInt(br.readLine());
			}
			Random random = null;
			while(++stamp1<=stamp2)
			{
				random = new Random(stamp1);
				i=11;
				while(--i>0)
				{
					if(random.nextInt(1000)!=val[10-i])
						break;
				}
				if(i==0)
					break;
			}
			
			System.out.print(stamp1);
			i=11;
			while(--i>0)
			{
				System.out.print(" "+random.nextInt(1000));
			}
			System.out.println();
		}
	}

}
