package dp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class MaxRectangleInHist {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {

		int n = ni();
		int[] arr = na(n);

		Deque<Integer> stack = new LinkedList<Integer>();

		int maxArea = 0;
		int top;
		int i = 0;
		for (; i < n; i++) {
			if (stack.isEmpty() || arr[i] >= arr[stack.peekFirst()])
				stack.addFirst(i);
			else if (arr[i] < arr[stack.peekFirst()]) {
				while (!stack.isEmpty() && arr[i] < arr[stack.peekFirst()]) {
					top = stack.pollFirst();
					maxArea = Math
							.max(maxArea,
									arr[top]
											* (stack.isEmpty() ? i : i
													- stack.peekFirst() - 1));
//					System.out.println(maxArea+" "+stack.toString());
				}
				stack.addFirst(i);
			}
			System.out.println(maxArea+" "+stack.toString());
		}
		while (!stack.isEmpty()) {
			top = stack.pollFirst();
			maxArea = Math.max(maxArea, arr[top]
					* (stack.isEmpty() ? i : i - stack.peekFirst() - 1));

		}

		System.out.println(maxArea);
	}

	static void solve1() {

		int n = ni();
		int[] arr = na(n);

		Deque<Integer> stack = new LinkedList<Integer>();

		int maxArea = 0;
		int top;
		int i = 0;
		for (; i < n; i++) {
			if (stack.isEmpty())
				stack.addFirst(i);
			else {
				top = stack.peekFirst();
				if (arr[top] < arr[i])
					stack.addFirst(i);
				else if (arr[top] > arr[i]) {
					do {
						top = stack.pollFirst();
						if (stack.isEmpty()) {
							maxArea = Math.max(maxArea, arr[top] * (top + 1));
							System.out
									.println(maxArea + " " + stack.toString());
						} else {
							int start = stack.peekFirst();
							if (arr[start] == arr[top])
								maxArea = Math.max(maxArea, arr[top]
										* (i - 1 - start + 1));
							else
								maxArea = Math.max(maxArea, arr[top]
										* (i - 1 - start));
							System.out
									.println(maxArea + " " + stack.toString());
						}
					} while (!stack.isEmpty()
							&& arr[stack.peekFirst()] > arr[i]);
					if (stack.isEmpty())
						stack.addFirst(i);
					else if (arr[stack.peekFirst()] != arr[i])
						stack.addFirst(i);
					System.out.println(stack.toString());
				}
			}

		}
		System.out.println("fin");
		do {
			top = stack.pollFirst();
			if (stack.isEmpty()) {
				maxArea = Math.max(maxArea, arr[top] * (top + 1));
				System.out.println(maxArea + " " + stack.toString());
			} else {
				int start = stack.peekFirst();
				if (arr[start] == arr[top])
					maxArea = Math.max(maxArea, arr[top] * (i - 1 - start + 1));
				else
					maxArea = Math.max(maxArea, arr[top] * (i - 1 - start));
				System.out.println(maxArea + " " + stack.toString());
			}
		} while (!stack.isEmpty());

		System.out.println(maxArea + " " + stack.toString());
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
