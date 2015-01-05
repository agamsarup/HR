package geometry;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class JimBeam {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() throws IOException {

		int t = ni();

		long x1, y1, x2, y2, xm, ym;
		while (--t >= 0) {
			x1 = nl();
			y1 = nl();

			x2 = nl();
			y2 = nl();

			xm = nl();
			ym = nl();

			double m1, m2;
			if (xm == 0) {
				if (x2 == x1) {
					if (x1 != xm) {
						out.println("YES");
						continue;
					} else {
						if ((ym > y2 && ym > y1 && ym > 0 && (y1 >= 0 || y2 >= 0))
								|| (ym < y2 && ym < y1 && ym < 0 && (y1 <= 0 || y2 <= 0))) {
							out.println("NO");
							continue;
						} else {
							out.println("YES");
							continue;
						}
					}
				} else {
					m2 = 1.0 * (y2 - y1) / (x2 - x1);
					double interY = y1 - m2 * x1;
					if (interY >= 0 && interY <= ym) {
						out.println("NO");
						continue;
					} else {
						out.println("YES");
						continue;
					}
				}
			} else {
				m1 = 1.0 * ym / xm;
				double interY, interX;
				if (x2 == x1) {
					interY = m1 * x1;
				} else {
					m2 = 1.0 * (y2 - y1) / (x2 - x1);
//					System.out.println("m1="+m1+" m2="+m2);
					if (m1 == m2) {
						if(y1!=m1*x1){
							out.println("YES");
							continue;
						}
						if ((ym > y2 && ym > y1 && ym > 0 && (y1 >= 0 || y2 >= 0))
								|| (ym < y2 && ym < y1 && ym < 0 && (y1 <= 0 || y2 <= 0))) {
							out.println("NO");
							continue;
						} else {
							out.println("YES");
							continue;
						}
					}
					interX = (y1 - m2 * x1) / (m1 - m2);
					interY = m1 * interX;

				}
				// System.out.println("interY=" + interY);
				// System.out.println(y1 + " " + y2);
				if (interY >= Math.min(y1, y2) && interY <= Math.max(y1, y2)
						&& interY >= Math.min(0, ym)
						&& interY <= Math.max(0, ym)) {
					out.println("NO");
					continue;
				} else {
					out.println("YES");
					continue;
				}
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
