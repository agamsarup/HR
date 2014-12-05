package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MiceV1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int[] micePos;
		int[] holePos;
		int n;
		int m;
		String[] s;
		int time;
		while(--t>=0)
		{
			n = Integer.parseInt(br.readLine());
			micePos=new int[n];
			holePos=new int[n];
			s=br.readLine().split(" ");
			
			for(int i=0;i<n;i++)
			{
				micePos[i]=Integer.parseInt(s[i]);
			}
			s=br.readLine().split(" ");
			
			for(int i=0;i<n;i++)
			{
				holePos[i]=Integer.parseInt(s[i]);
			}
			
			Arrays.sort(micePos);
			Arrays.sort(holePos);
			
			time=0;
			for(int i=0;i<n;i++)
			{
				if(Math.abs(holePos[i]-micePos[i]) > time)
					time=Math.abs(holePos[i]-micePos[i]);
			}
			
			System.out.println(time);
		}
	}

}
