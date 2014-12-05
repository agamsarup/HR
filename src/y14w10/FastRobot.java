package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FastRobot {

	public static boolean takeEnergy(int curEnergy, long[] v, int[] p, int pos, int n)
	{
		//if(v[pos+curEnergy] <= v[pos] && p[pos+curEnergy] >= n-pos-curEnergy)
		//	return false;
		//int cheapestPosition=pos;
		
		int i=pos+1;
		for(;i<=pos+curEnergy;i++)
		{
			if(v[i] <= v[pos] && p[pos]-(i-pos) < p[i])
				return false;
		}
		
		
		
		return true;
	}
	
	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int i = 0;
		int energy = 0;
		int step = 0;
		String[] vp;
		long[] v = new long[n + 1];
		int[] p = new int[n + 1];
		long score = 0;

		while (++step <= n) {
			vp = br.readLine().split(" ");
			v[step] = Long.parseLong(vp[0]);
			p[step] = Integer.parseInt(vp[1]);
		}
		step=0;
		while (++step < n) {
			if (energy == 0) {
				energy = p[step];
			} else if (v[step] == 0) {
				if (p[step] > energy)
					energy = p[step];
			} else if (p[step] < energy) {
				score += v[step];
			} else if (p[step] > energy && energy < n - step) {
				if(takeEnergy(energy, v, p, step, n))
					energy = p[step];
				else
					score += v[step];
			} else// if(energy >= n-step)
			{
				score += v[step];
			}
			energy--;
		}
		score += v[step];

		System.out.println(score);
	}

}
