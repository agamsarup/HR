package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Robot {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long n=Long.parseLong(br.readLine());
		int i=0;
		long energy=0;
		long step=0;
		String[] vp;
		long v;
		long p;
		long score=0;
		while(++step<n)
		{
			vp=br.readLine().split(" ");
			v=Long.parseLong(vp[0]);
			p=Long.parseLong(vp[1]);
			if(energy==0)
			{
				energy=p;
			}
			else if(v==0)
			{
				if(p > energy)
					energy=p;
			}
			else if(p<energy)
			{
				score+=v;
			}
			else if(p > energy && energy < n-step)
			{
				
			}
			else// if(energy >= n-step)
			{
				score+=v;
			}
			energy--;
		}
		vp=br.readLine().split(" ");
		v=Long.parseLong(vp[0]);
		//p=Long.parseLong(vp[1]);
		score+=v;
		
		System.out.println(score);
	}

}
