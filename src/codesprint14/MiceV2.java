package codesprint14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MiceV2 {

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		int[] micePos;
		int[] holePos;
		int n;
		int m;
		String[] s;
		String[] nm;
		int time;
		while (--t >= 0) {
			nm = br.readLine().split(" ");
			n = Integer.parseInt(nm[0]);
			m = Integer.parseInt(nm[1]);
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

			time = 0;
			if (micePos[0] <= holePos[0]) {
				for (int i = 0; i < n; i++) {
					if (Math.abs(holePos[i] - micePos[i]) > time)
						time = Math.abs(holePos[i] - micePos[i]);
				}
			}
			else if(micePos[n-1] >= holePos[m-1])
			{
				int i=n-1;
				int j=m-1;
				for (; i >= 0; i--,j--) {
					if (Math.abs(holePos[j] - micePos[i]) > time)
						time = Math.abs(holePos[j] - micePos[i]);
				}
			}
			System.out.println(time);
		}
	}

}
