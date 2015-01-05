package arraysAndSorting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SherlockAndMinimax {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() throws IOException {

		int n = ni();

		long[] a = nal(n);
		long p = nl(), q = nl();

		Arrays.sort(a);
		// System.out.println(Arrays.toString(a));
		int maxa, maxb;
		long maxDiff = 0;

		long maxNum = 0;
		if (n == 1) {
			if (Math.abs(p - a[0]) >= Math.abs(q - a[0]))
				out.println(p);
			else
				out.println(q);
		} else {
			if (a[0] > q)
				out.println(p);
			else if (a[n - 1] < p)
				out.println(q);
			else {
				maxa = -1;
				while (maxa < n - 1 && a[++maxa] <= p)
					;
				if (maxa > 0) {
					maxDiff = (a[maxa] - a[maxa - 1]) / 2;
					maxNum = (a[maxa] + a[maxa - 1]) / 2;
				}
				else{
					maxNum=p;
					maxDiff=a[0]-p;
				}
				// if (maxa > 0)
				// maxDiff = Math.min(maxDiff, p - a[maxa - 1]);
				// maxNum = p;
				while (maxa < n - 1 && a[++maxa] < q) {
//					System.out.println("a[maxa - 1]=" + a[maxa - 1]);
//					System.out.println("a[maxa]=" + a[maxa]);
					if ((a[maxa] - a[maxa - 1]) / 2 > maxDiff) {
						maxDiff = (a[maxa] - a[maxa - 1]) / 2;
						maxNum = (a[maxa] + a[maxa - 1]) / 2;
						// System.out.println("maxDiff="+maxDiff);
						// System.out.println("maxNum="+maxNum);
					}
				}
				// System.out.println(maxa);
				// System.out.println(a[maxa]);
				if (q > a[n - 1]) {
					if (q - a[n - 1] > maxDiff)
						maxNum = q;

				} else {
					long t = (a[maxa] + a[maxa - 1]) / 2;
					if (t > q)
						t = q;
					if (t - a[maxa - 1] > maxDiff && a[maxa] - t > maxDiff) {
						maxNum = t;
						maxDiff = Math.min(t - a[maxa - 1], a[maxa] - t);
					}
					// if ((a[maxa - 1] + a[maxa]) / 2 < maxNum
					// && maxNum-a[maxa-1] > maxDiff) {
					// maxNum = (a[maxa - 1] + a[maxa]) / 2;
					//
					// }
				}
				out.println(maxNum);
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
