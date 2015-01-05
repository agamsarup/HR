package quoraHaqathon;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

class Task implements Comparable {
	int t;
	double p;

	public Task(int t, double p) {
		this.t = t;
		this.p = p;
	}

	@Override
	public int compareTo(Object o) {
		if (p < ((Task) o).p)
			return -1;
		else if (p > ((Task) o).p)
			return 1;
		else {
			if (t < ((Task) o).t) {
				return -1;
			} else if (t > ((Task) o).t) {
				return 1;
			}
		}
		return 0;
	}

}

public class Schedule {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {
		int n = ni();

		// int[] t = new int[n];
		// double[] p = new double[n];

		Task[] task = new Task[n];
		for (int i = 0; i < n; i++) {
			// t[i] = ni();
			// p[i] = nd();

			task[i] = new Task(ni(), nd());
//			System.out.println(task[i].t +" "+task[i]);
		}

//		Arrays.sort(task);

		double prob = 0;
		int timeSum = 0;
		double prod = 1;
		boolean[] chosen = new boolean[n];
		int minIndex;// =0;
		double minValue;// =;
		for (int i = 0; i < n - 1; i++) {
			int j = -1;
			while (chosen[++j])
				;
			minValue = (timeSum + task[j].t) * prod * (1 - task[j].p);
			minIndex = j;
			j++;

			for (; j < n; j++) {
				if (!chosen[j]) {
					if ((timeSum + task[j].t) * prod * (1 - task[j].p) < minValue) {
						minIndex = j;
						minValue = (timeSum + task[j].t) * prod
								* (1 - task[j].p);
					}
				}
			}

			System.out.println("chosen=" + minIndex);
			System.out.println("minValue=" + minValue);
			chosen[minIndex] = true;
			prob += minValue;
			System.out.println(prob);
			timeSum += task[minIndex].t;
			prod *= task[minIndex].p;
			System.out.println("ts=" + timeSum);
			System.out.println("prod=" + prod);
			System.out.println();
		}

		int j = 0;
		for (; j < n; j++) {
			if (!chosen[j]) {
				minIndex = j;
				break;
			}
		}
		timeSum += task[j].t;
		prob += timeSum * prod;

		out.println(prob);
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

	private static double[] nad(int n) {
		double[] a = new double[n];
		for (int i = 0; i < n; i++)
			a[i] = nd();
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
