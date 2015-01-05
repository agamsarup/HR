package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SubstringDiff {

	public static int method1(String p, String q, int k) {
		int length = p.length();
		int m = 0;
		int max = -1;
		int i = 0;
		int j = 0;
		int l = 0;
		while (true) {
			if (i + l == length) {
				if (l > max && m <= k) {
					max = l;
				}
				break;
			}

			if (j + l == length) {
				if (l > max && m <= k) {
					max = l;
				}
				i = i + 1;
				j = 0;
				l = 0;
				m = 0;
				continue;
			}
			if (p.charAt(i + l) == q.charAt(j + l)) {
				l++;
				continue;
			}
			m++;
			if (m > k) {
				if (l > max) {
					max = l;
				}
				l = 0;
				m = 0;
				j = j + 1;
			} else {
				l++;
			}

		}
		return max;
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine().trim());

		for (int tc = 0; tc < t; tc++) {
			String[] str = br.readLine().trim().split(" ");

			int k = Integer.parseInt(str[0]);

			System.out.println(method1(str[1], str[2], k));
		}
	}
}
