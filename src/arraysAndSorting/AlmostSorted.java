package arraysAndSorting;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class AlmostSorted {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int isSorted(int[] arr) {
		int i = 0, n = arr.length;

		while (i < n - 1) {
			if (arr[i] > arr[i + 1])
				return i;
			i++;
		}

		return -1;
	}

	static void reverse(int[] arr, int i, int j){
		int t;
		while(i<j){
			t=arr[i];
			arr[i]=arr[j];
			arr[j]=t;
			i++;
			j--;
		}
	}
	
	static void solve() throws IOException {
		int n = ni();
		int[] arr = na(n);

		int i;
		int sorted = isSorted(arr);
		int a, b, temp;
		if (sorted == -1)
			out.println("yes");
		else {
			// find a number which is less than arr[i+1] and gt arr[i-1] after
			// [i]
			i = sorted;
			int j = n-1;
			for (; j > i; j--) {
				if (arr[j] < arr[i]) {
//					System.out.println(j+" "+i);
					break;
				}
			}
			if (j < n) {
				// swap i & j
				a = i;
				b = j;
				temp = arr[a];
				arr[a] = arr[b];
				arr[b] = temp;
				// sorted=isSorted(arr);
				if (isSorted(arr) == -1) {
					out.println("yes");
					out.println("swap " + (i + 1) + " " + (j + 1));
					return;
				}
				temp = arr[a];
				arr[a] = arr[b];
				arr[b] = temp;

			}
			// try reverse
			i = sorted + 1;
			while (i + 1 < n && arr[i] > arr[i + 1])
				i++;
			if (i == n - 1 || ( i+1 >= n || arr[sorted] < arr[i + 1] ) || ( arr[sorted] < arr[i])) {
				reverse(arr, sorted, i);
				if(isSorted(arr)!=-1){
					out.println("no");
					return;
				}
				out.println("yes");
				out.println("reverse " + (sorted+1) + " " + (i+1));
				System.out.println(arr[sorted-1]+" "+arr[sorted]+" "+arr[i]+" "+arr[i+1] );
				return;
			}
		}
		out.println("no");
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
