package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SSDiff {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		long startTime = System.currentTimeMillis();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String line = reader.readLine();
		int T = Integer.parseInt(line.trim());
		String[] lines = new String[T];
		for (int i = 0; i < T; i++) {
			line = reader.readLine();
			lines[i] = line;
		}

		for (int i = 0; i < T; i++) {
			String[] spl = lines[i].split(" ");
			int k = Integer.parseInt(spl[0]);
			System.out.println(solve(spl[1], spl[2], k));
		}
		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime / 1000.0);

	}

	public static int solve(String a, String b, int k) {
		int n = a.length();
		int currDiff = 0;
		int max = Integer.MIN_VALUE;
		int i = 0;
		int j = 0;
		int L = 0;
		while (true) {
			if (i + L == n) {
				if (L > max && currDiff <= k) {
					max = L;
				}
				break;
			}

			if (j + L == n) {
				if (L > max && currDiff <= k) {
					max = L;
				}
				i = i + 1;
				j = 0;
				L = 0;
				currDiff = 0;
				continue;
			}
			if (a.charAt(i + L) == b.charAt(j + L)) {
				L++;
				continue;
			}
			currDiff++;
			if (currDiff > k) {
				if (L > max) {
					max = L;
				}
				L = 0;
				currDiff = 0;
				j = j + 1;
			} else {
				L++;
			}

		}
		return max;
	}

}