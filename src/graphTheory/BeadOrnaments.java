package graphTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class BeadOrnaments {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int mod = 1000000007;

	public static long powMod(long n, long k, long m) {
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

		int t = ni();
		int n;
		int[] numBeads;

		long treeProduct;
		int[] numTrees = new int[11];
		int totalBeads;
		long beadProduct;
		while (--t >= 0) {
			n = ni();
			numBeads = na(n);

			treeProduct = 1;
			beadProduct=1;
			totalBeads=0;
			for (int i = 0; i < n; i++) {
				if (numBeads[i] > 2)
					numTrees[i] = (int) powMod(numBeads[i], numBeads[i] - 2,
							mod);
				else
					numTrees[i] = 1;
				totalBeads+=numBeads[i];
			}
			// System.out.println(Arrays.toString(numBeads));
			// System.out.println(Arrays.toString(numTrees));
			
			for (int i = 0; i < n; i++) {
				treeProduct = (treeProduct * numTrees[i]) % mod;
				beadProduct = (beadProduct * numBeads[i]) % mod;
			}
			if(n==1)
				out.println(treeProduct);
			else if(n==2)
				out.println((beadProduct*treeProduct)%mod);
			else
			{
				long res=(beadProduct*treeProduct)%mod;
				res=res*powMod(totalBeads, n - 2, mod);
				out.println(res%mod);
			}

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
