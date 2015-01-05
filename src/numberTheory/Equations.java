package numberTheory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Equations {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		long prod = 1;
		int primes[] = new int[N + 1];// 0 => prime
		for (int i = 2; i < N + 1; i++) {
			if (primes[i] == 0) {
				for (int j = 2 * i; j <= N; j += i) {
					primes[j] = 1;// not prime
				}
			}
		}

		for (int p = 2; p <= N; p++) {
			if (primes[p] == 0) {
				int temp = N;
				int count = 0;
				while (temp >= p) {
					count += temp / p;
					temp /= p;
				}
				prod *= (2 * count + 1);
				prod %= 1000007;
			}
		}

		System.out.println(prod);
	}
}