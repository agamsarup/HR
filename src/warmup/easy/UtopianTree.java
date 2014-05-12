package warmup.easy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class UtopianTree {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int[] Ns = new int[t];
//		int max=Integer.MIN_VALUE
		for(int i=0;i<t;i++)
		{
			Ns[i] = Integer.parseInt(br.readLine());
			long ans=1;
			for(int j=0;j<Ns[i]/2;j++)
				ans=2*ans+1;
			if(Ns[i]%2==1)
				ans*=2;
			System.out.println(ans);
		}

	}

}
