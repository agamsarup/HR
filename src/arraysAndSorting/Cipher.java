package arraysAndSorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Cipher {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String nk[] = br.readLine().trim().split(" ");
		int n, k;
		n = Integer.parseInt(nk[0]);
		k = Integer.parseInt(nk[1]);

		String s = br.readLine();

		StringBuilder ans = new StringBuilder(s);
		ans.setCharAt(0, s.charAt(0));
		char lastXor = s.charAt(0);
		for (int i = 1; i < n; i++) {
			{
				if (i >= k) {
					char removeFromXor = ans.charAt(i - k);
					if (removeFromXor == '1') {
						if (lastXor == '1')
							lastXor = '0';
						else
							lastXor = '1';
					}

				}
				// System.out.println("lx = "+lastXor);
				if (s.charAt(i) == lastXor) {
					if (lastXor == '1') {
						ans.setCharAt(i, '0');
					} else {
						ans.setCharAt(i, '0');
						lastXor = '0';
					}
				} else {
					if (lastXor == '1') {
						ans.setCharAt(i, '1');
						lastXor = '0';
					} else {
						ans.setCharAt(i, '1');
						lastXor = '1';
					}
				}
			}
		}

		System.out.println(ans.substring(0, n));
	}
}
