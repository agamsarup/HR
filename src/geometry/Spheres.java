package geometry;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Spheres {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() throws IOException {

		int t = ni();

		int x1, y1, z1, x2, y2, z2, ax1, ay1, az1, ax2, ay2, az2;

		int r1, r2;

		double tx1, tx2, ty1, ty2, tz1, tz2;
		while (--t >= 0) {
			r1 = ni();
			r2 = ni();

			x1 = ni();
			y1 = ni();
			z1 = ni();
			ax1 = ni();
			ay1 = ni();
			az1 = ni();

			x2 = ni();
			y2 = ni();
			z2 = ni();
			ax2 = ni();
			ay2 = ni();
			az2 = ni();

			double max = Integer.MAX_VALUE, min = Integer.MIN_VALUE;
			double temp1;// = 2.0 * (r1 + r2 + x2 - x1) / (ax1 - ax2);
			double temp2;// = 2.0 * (-r1 - r2 + x2 - x1) / (ax1 - ax2);

			if (ax1 - ax2 != 0) {
				temp1 = 2.0 * (r1 + r2 + x2 - x1) / (ax1 - ax2);
				temp2 = 2.0 * (-r1 - r2 + x2 - x1) / (ax1 - ax2);
				System.out.println("t1="+temp1);
				System.out.println("t2="+temp2);
				max = Math.min(max, Math.max(temp1, temp2));
				min = Math.max(min, Math.min(temp1, temp2));
			} else {
				if (x1 - x2 > r1 + r2) {
					min = Integer.MAX_VALUE;
					max = Integer.MIN_VALUE;
				}
			}
			System.out.println(min);
			System.out.println(max);
			if (ay1 - ay2 != 0) {
				temp1 = 2.0 * (r1 + r2 + y2 - y1) / (ay1 - ay2);
				temp2 = 2.0 * (-r1 - r2 + y2 - y1) / (ay1 - ay2);
				System.out.println("t1="+temp1);
				System.out.println("t2="+temp2);
				max = Math.min(max, Math.max(temp1, temp2));
				min = Math.max(min, Math.min(temp1, temp2));
			} else {
				if (y1 - y2 > r1 + r2) {
					min = Integer.MAX_VALUE;
					max = Integer.MIN_VALUE;
				}
			}
			System.out.println(min);
			System.out.println(max);
			if (az1 - az2 != 0) {
				temp1 = 2.0 * (r1 + r2 + z2 - z1) / (az1 - az2);
				temp2 = 2.0 * (-r1 - r2 + z2 - z1) / (az1 - az2);
				System.out.println("t1="+temp1);
				System.out.println("t2="+temp2);
				max = Math.min(max, Math.max(temp1, temp2));
				min = Math.max(min, Math.min(temp1, temp2));
			} else {
				if (z1 - z2 > r1 + r2) {
					min = Integer.MAX_VALUE;
					max = Integer.MIN_VALUE;
				}
			}

			System.out.println(min);
			System.out.println(max);
			if (min <= max && max >= 0) {
				out.println("YES");
			} else {
				out.println("NO");
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
