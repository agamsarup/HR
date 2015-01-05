package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Candies {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine().trim());
		
		int[] rating = new int[n];
		int[] candies = new int[n];
		for(int i=0;i< n;i++)
		{
			rating[i]=Integer.parseInt(br.readLine().trim());
		}
		candies[0]=1;
		
		for(int i=1;i<n;i++)
		{
			if(rating[i] > rating[i-1])
				candies[i]=candies[i-1]+1;
			else if(rating[i]==rating[i-1])
				candies[i]=1;
			else
			{
				candies[i] = 1;

                if (candies[i - 1] <= 1)
                {
                    for (int j = i - 1; j >= 0; j--)
                    {
                        if ((rating[j] > rating[j + 1] && candies[j] > candies[j + 1]) ||
                            (rating[j] < rating[j + 1] && candies[j] < candies[j + 1]) ||
                            (rating[j] == rating[j + 1]))
                        {
                            break;
                        }
                        
                        candies[j] = candies[j + 1] + 1;
                    }
                }
			}
		}
		
		long candySum=0;
		for(int i=0;i<n;i++)
		{
			candySum+=candies[i];
		}
		System.out.println(candySum);
	}

}
