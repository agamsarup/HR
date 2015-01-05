package csIndia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ArraySimplification {

	public static long powMod(long n, long k, long m) {
		if (n == 1)
			return 1;
		if (k == 0)
			return 1;
		if (k == 1)
			return n % m;
		if (k % 2 == 0) {
			long temp = powMod(n, k / 2, m);
			return (temp * temp) % m;
		} else {
			long temp = powMod(n, k / 2, m);
			temp = (temp * temp) % m;
			return (temp * n) % m;
		}
	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		int n;
		String[] s;
		long mod = 1000000007;
		long[] numbers;
		long[] nCr;
		long res;
		while (--t >= 0) {
			n = Integer.parseInt(br.readLine());

			s = br.readLine().split(" ");
			numbers = new long[n];
			nCr = new long[n];
			nCr[0] = 1;
			if (n > 1) {
				if (n % 2 == 0)
					nCr[n - 1] = -1;
				else
					nCr[n - 1] = 1;
			}
			for (int i = 0; i < n; i++) {
				numbers[i] = Long.parseLong(s[i]);
			}
			for (int i = 1; i <= (n - 1) / 2; i++) {
				nCr[i] = (nCr[i - 1] * (n - i)) % mod;
				nCr[i] = (nCr[i] * powMod(i, mod - 2, mod)) % mod;
				nCr[i] = (nCr[i] * (mod - 1)) % mod;
				nCr[n - 1 - i] = nCr[i];
				// System.out.println("2nCr["+i+"]="+nCr[i]);
				// System.out.println("2nCr["+(n - 1 - i)+"]="+nCr[n - 1 - i]);
				if (n % 2 == 0) {
					nCr[n - 1 - i] = (nCr[n - 1 - i] * (mod - 1)) % mod;
				}
				System.out.println("2nCr[" + i + "]=" + nCr[i]);
				System.out.println("2nCr[" + (n - 1 - i) + "]="
						+ nCr[n - 1 - i]);
			}
			res = 0;
			for (int i = 0; i < n; i++) {
				res = (res + (numbers[i] * nCr[i]) % mod) % mod;
			}

			if (res < 0)
				res += mod;
			System.out.println(res);
		}
	}

}
