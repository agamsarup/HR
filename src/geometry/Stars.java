package geometry;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Stars {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() throws IOException {

		int n = ni();

		long[] x = new long[n + 1];
		long[] y = new long[n + 1];
		long[] v = new long[n + 1];

		// long totalWt = 0;
		for (int i = 1; i <= n; i++) {
			x[i] = nl();
			y[i] = nl();
			v[i] = nl();
			// totalWt += v[i];
		}

		// System.out.println(totalWt);

		long x1, y1, x2, y2, v1, v2, tx, ty, tv;
		long minWtDiff = Long.MAX_VALUE, wtDiff;
		long maxMinWt = 0, wt1, wt2;
		double slope;
		for (int i = 1; i < n; i++) {
			x1 = x[i];
			y1 = y[i];
			v1 = v[i];
			for (int j = i + 1; j <= n; j++) {
				x2 = x[j];
				y2 = y[j];
				v2 = v[j];
				wtDiff = 0;
				wt1 = 0;
				wt2 = 0;

				slope = (1.0 * (y2 - y1)) / (x2 - x1);
				if (x1 == x2) {
					// slope = infinity
					// line perpendicular to x axis passing through x1 and
					// x2

					for (int p = 1; p <= n; p++) {
						if (p != i && p != j) {
							tx = x[p];
							if (tx > x1)
								wt1 += v[p];
							else if (tx < x1)
								wt2 += v[p];
						}
					}
					if (Math.min(wt1 + v1, wt2 + v2) > maxMinWt
							&& Math.abs(wt1 + v1 - wt2 - v2) <= minWtDiff) {
						maxMinWt = Math.min(wt1 + v1, wt2 + v2);
						minWtDiff = Math.abs(wt1 + v1 - wt2 - v2);
					}

					if (Math.min(wt1 + v1 + v2, wt2) > maxMinWt
							&& Math.abs(wt1 + v2 - wt2 + v1) <= minWtDiff) {
						maxMinWt = Math.min(wt1 + v1 + v2, wt2);
						minWtDiff = Math.abs(wt1 + v2 - wt2 + v1);
					}
					if (Math.min(wt2 + v1, wt1 + v2) > maxMinWt
							&& Math.abs(wt2 + v1 - wt1 - v2) <= minWtDiff) {
						maxMinWt = Math.min(wt2 + v1, wt1 + v2);
						minWtDiff = Math.abs(wt2 + v1 - wt1 - v2);
					}

					if (Math.min(wt2 + v1 + v2, wt1) > maxMinWt
							&& Math.abs(wt2 + v2 - wt1 + v1) <= minWtDiff) {
						maxMinWt = Math.min(wt2 + v1 + v2, wt1);
						minWtDiff = Math.abs(wt2 + v2 - wt1 + v1);
					}
				} else {
					for (int p = 1; p <= n; p++) {
						if (p != i && p != j) {
							tx = x[p];
							ty = y[p];
							double side = ty - slope * tx + slope * x1 - y1;
							if (side > 0)
								wt1 += v[p];
							else if (side < 0)
								wt2 += v[p];
						}
					}

					if (Math.min(wt1 + v1, wt2 + v2) > maxMinWt
							&& Math.abs(wt1 + v1 - wt2 - v2) <= minWtDiff) {
						maxMinWt = Math.min(wt1 + v1, wt2 + v2);
						minWtDiff = Math.abs(wt1 + v1 - wt2 - v2);
					}

					if (Math.min(wt1 + v1 + v2, wt2) > maxMinWt
							&& Math.abs(wt1 + v2 - wt2 + v1) <= minWtDiff) {
						maxMinWt = Math.min(wt1 + v1 + v2, wt2);
						minWtDiff = Math.abs(wt1 + v2 - wt2 + v1);
					}
					if (Math.min(wt2 + v1, wt1 + v2) > maxMinWt
							&& Math.abs(wt2 + v1 - wt1 - v2) <= minWtDiff) {
						maxMinWt = Math.min(wt2 + v1, wt1 + v2);
						minWtDiff = Math.abs(wt2 + v1 - wt1 - v2);
					}

					if (Math.min(wt2 + v1 + v2, wt1) > maxMinWt
							&& Math.abs(wt2 + v2 - wt1 + v1) <= minWtDiff) {
						maxMinWt = Math.min(wt2 + v1 + v2, wt1);
						minWtDiff = Math.abs(wt2 + v2 - wt1 + v1);
					}
				}
			}
		}

		out.println(maxMinWt);
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
