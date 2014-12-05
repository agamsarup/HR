import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MiceV3 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int[] micePos;
		int[] holePos;
		int n;
		int m;
		int diff;
		
		String[] s;
		String[] nm;
		int time;
		while (--t >= 0) {
			nm = br.readLine().split(" ");
			n = Integer.parseInt(nm[0]);
			m = Integer.parseInt(nm[1]);
			diff=m-n;
			micePos = new int[n];
			holePos = new int[m];
			s = br.readLine().split(" ");

			for (int i = 0; i < n; i++) {
				micePos[i] = Integer.parseInt(s[i]);
			}
			s = br.readLine().split(" ");

			for (int i = 0; i < m; i++) {
				holePos[i] = Integer.parseInt(s[i]);
			}

			Arrays.sort(micePos);
			Arrays.sort(holePos);

//			int[][] timeDp=new int[n][3];
			
			time=0;
			int i=0;
			while(i < n && micePos[i]<=holePos[i])
			{
				if (holePos[i] - micePos[i] > time)
					time = holePos[i] - micePos[i];
			}
			
			int j=n-1;
			int k=m-1;
			while(j >= 0 && micePos[j]>=holePos[k])
			{
				if ( micePos[j] - holePos[k]> time)
					time = micePos[j] - holePos[k];
			}
			
			
			
//			System.out.println(Math.min(Math.min(timeDp[n-1][0], timeDp[n-1][1]),timeDp[n-1][2]));
		}
	}

}
