package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RoyRect {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long t=Long.parseLong(br.readLine());
	
		String[] s;
		int x,y,x1,y1,x2,y2;
		int minPath;
		while(--t>=0)
		{
			minPath=Integer.MAX_VALUE;
			s=br.readLine().split(" ");
			x=Integer.parseInt(s[0]);
			y=Integer.parseInt(s[1]);
			x1=Integer.parseInt(s[2]);
			y1=Integer.parseInt(s[3]);
			x2=Integer.parseInt(s[4]);
			y2=Integer.parseInt(s[5]);
			
			if(x2-x<minPath)
				minPath=x2-x;
			if(x-x1<minPath)
				minPath=x-x1;
			if(y2-y<minPath)
				minPath=y2-y;
			if(y-y1<minPath)
				minPath=y-y1;
			
			System.out.println(minPath);
		}
	}

}
