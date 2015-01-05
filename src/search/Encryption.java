package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Encryption {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();

		int width=(int) Math.ceil(Math.sqrt(s.length()));
		int height=(int) Math.floor(Math.sqrt(s.length()));
		int length=s.length();
		
		if(width*height >= length)
			height++;
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				if(i+width*j < length)
					System.out.print(s.charAt(i+width*j));
				else
					break;
			}
			System.out.print(" ");
		}
	}

}
