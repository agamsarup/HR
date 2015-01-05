package graphTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class GridWalking {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static int mod = 1000000007;

	static long powMod(long n, long k, long m) {
		if(n==1)
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

	static void solve() {

		int t = ni(), n, m;
		int[] x, d;
		long[][][] W;// number of ways to stay in a dimension with K moves
		long[][] T;
		while (--t >= 0) {
			n = ni();
			m = ni();
			x = na(n);
			d = na(n);

			W = new long[n][101][m + 1];
			T = new long[n][m + 1];
			for (int i = 0; i < n; i++) {
				for (int j = 1; j <= d[i]; j++) {
					W[i][j][0] = 1;
				}
			}
			for (int k = 1; k <= m; k++) {
				for (int i = 0; i < n; i++) {
					for (int j = 1; j <= d[i]; j++) {
						if (j > 1)
							W[i][j][k] = (W[i][j][k] + W[i][j - 1][k - 1])
									% mod;
						if (j < d[i])
							W[i][j][k] = (W[i][j][k] + W[i][j + 1][k - 1])
									% mod;

						 System.out.println(i+" "+j+" "+k+" "+W[i][j][k]);
					}
				}
			}
			// System.out.println("W="+W[n-1][x[0]][m]);
			for (int k = 0; k <= m; k++) {
				T[0][k] = W[0][x[0]][k];
			}
			long temp;
			for (int k = 1; k <= m; k++) {
				for (int i = 1; i < n; i++) {
					long combinations = 1;
					T[i][k]=T[i-1][k];
					for (int j = 1; j <= k; j++) {
						combinations = (((combinations * (k + 1 - j)) % mod) * (powMod(
								j, mod - 2, mod))) % mod;
						temp = (combinations * W[i][x[i]][j]) % mod;
						temp = (temp * T[i - 1][k - j]) % mod;
						T[i][k] = (T[i][k] + temp) % mod;
					}
				}
			}
			out.println(T[n - 1][m]);
		}

	}

	public static void main(String[] args) throws Exception {
		long S = System.currentTimeMillis();
		is = INPUT.isEmpty() ? System.in : new ByteArrayInputStream(
				INPUT.getBytes());
		out = new PrintWriter(System.out);

		solve();
		out.flush();
		long G = System.currentTimeMillis();
		tr(G - S + "ms");
	}

	private static boolean eof() {
		if (lenbuf == -1)
			return true;
		int lptr = ptrbuf;
		while (lptr < lenbuf)
			if (!isSpaceChar(inbuf[lptr++]))
				return false;

		try {
			is.mark(1000);
			while (true) {
				int b = is.read();
				if (b == -1) {
					is.reset();
					return true;
				} else if (!isSpaceChar(b)) {
					is.reset();
					return false;
				}
			}
		} catch (IOException e) {
			return true;
		}
	}

	private static byte[] inbuf = new byte[1024];
	static int lenbuf = 0, ptrbuf = 0;

	private static int readByte() {
		if (lenbuf == -1)
			throw new InputMismatchException();
		if (ptrbuf >= lenbuf) {
			ptrbuf = 0;
			try {
				lenbuf = is.read(inbuf);
			} catch (IOException e) {
				throw new InputMismatchException();
			}
			if (lenbuf <= 0)
				return -1;
		}
		return inbuf[ptrbuf++];
	}

	private static boolean isSpaceChar(int c) {
		return !(c >= 33 && c <= 126);
	}

	private static int skip() {
		int b;
		while ((b = readByte()) != -1 && isSpaceChar(b))
			;
		return b;
	}

	private static double nd() {
		return Double.parseDouble(ns());
	}

	private static char nc() {
		return (char) skip();
	}

	private static String ns() {
		int b = skip();
		StringBuilder sb = new StringBuilder();
		while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b !=
									// ' ')
			sb.appendCodePoint(b);
			b = readByte();
		}
		return sb.toString();
	}

	private static char[] ns(int n) {
		char[] buf = new char[n];
		int b = skip(), p = 0;
		while (p < n && !(isSpaceChar(b))) {
			buf[p++] = (char) b;
			b = readByte();
		}
		return n == p ? buf : Arrays.copyOf(buf, p);
	}

	private static char[][] nm(int n, int m) {
		char[][] map = new char[n][];
		for (int i = 0; i < n; i++)
			map[i] = ns(m);
		return map;
	}

	private static long[] nal(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
		return a;
	}

	private static int[] na(int n) {
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = ni();
		return a;
	}

	private static int ni() {
		int num = 0, b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static long nl() {
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = readByte();
		}

		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = readByte();
		}
	}

	private static void tr(Object... o) {
		if (INPUT.length() != 0)
			System.out.println(Arrays.deepToString(o));
	}
}
