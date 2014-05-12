package dp.moderate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StockMaximize {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(br.readLine());
		
		for(int i=0;i<t;i++)
		{
			int n=Integer.parseInt(br.readLine());
			String[] price=br.readLine().split(" ");
			int length=price.length;
			int[] iPrice=new int[length];
			int max=Integer.MIN_VALUE;
			boolean[] sell = new boolean[length];
//			for(int j=0;j<length;j++)
//				System.out.println(sell[j]);
			for(int j=length-1;j>=0;j--)
			{
				iPrice[j]=Integer.parseInt(price[j]);
				if(iPrice[j] > max)
				{
					sell[j]=true;
					max=iPrice[j];
				}
			}
//			if(sell[0])
//			{
//				System.out.println(0);
//			}
//			else
//			{
				int start=0;
				while(start < n && sell[start])
					start++;
				if(start==n)
				{
					System.out.println(0);
					continue;
				}
				long curStocks=1;
				long moneySpent=iPrice[start];
				long profitSum=0;
				for(int j=start+1;j<length;j++)
				{
					if(sell[j])
					{
						profitSum+=curStocks*iPrice[j]-moneySpent;
						curStocks=0;
						moneySpent=0;
					}
					else
					{
						curStocks++;
						moneySpent+=iPrice[j];
					}
				}
				System.out.println(profitSum);
//			}
		}
	}

}
