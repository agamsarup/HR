package string;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class RevShuffMerge {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() throws IOException {
		String s = ns();

		int length = s.length();
		int[] freq = new int[26];

		for (int i = 0; i < length; i++) {
			freq[s.charAt(i) - 'a']++;
		}
		System.out.println(Arrays.toString(freq));
		int[] freqCur = new int[26];
		int[] freqStored = new int[26];
		String ansStr = "";
		int pos;
		for (int i = 0; i < length; i++) {
			// for (int i = length - 1; i >= 0; i--) {
			pos = s.charAt(i) - 'a';
			freqCur[pos]++;
			if (freq[pos] / 2 < freqCur[pos] - freqStored[pos]) {
//				for (int j = 25; j > pos; j--) {
//					// for (int j = 0; j < pos; j++) {
//					while (freqStored[j] < freqCur[j]
//							&& freqStored[j] < freq[j] / 2) {
//						ansStr = (char) ('a' + j) + ansStr;
//						// ansStr = ansStr + (char) ('a' + j);
//						freqStored[j]++;
//						System.out.println(i+"i "+"j "+(char) ('a' + j));
//						System.out.println(Arrays.toString(freqCur));
//						System.out.println(Arrays.toString(freqStored));
//					}
//				}
				System.out.println(i+"i "+s.charAt(i));
				ansStr = s.charAt(i) + ansStr;
				// ansStr = ansStr + s.charAt(i);
				freqStored[pos]++;
				System.out.println(Arrays.toString(freqCur));
				System.out.println(Arrays.toString(freqStored));
			}
		}

		out.println(ansStr);
		// out.println((new StringBuilder(ansStr)).reverse().toString());
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
