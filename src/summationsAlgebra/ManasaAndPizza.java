package summationsAlgebra;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ManasaAndPizza {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static int mod = 1000000007;

	public static long[][] addMatrixMod(long[][] A, long[][] B, long mod) {
		int rows = A.length;
		int cols = B[0].length;
		int Acols = A[0].length;
//		System.out.println("rows=" + rows + "cols=" + cols);
		long[][] result = new long[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = (A[i][j] + B[i][j]) % mod;
			}
		}
		return result;
	}

	public static long[][] multMatrixMod(long[][] A, long[][] B, long mod) {
		int rows = A.length;
		int cols = B[0].length;
		int Acols = A[0].length;
		// System.out.println("rows="+rows+"cols="+cols);
		long[][] result = new long[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result[i][j] = 0;
				for (int k = 0; k < Acols; k++) {
					result[i][j] = (result[i][j] + A[i][k] * B[k][j]) % mod;
				}
			}
		}
		return result;
	}

	public static long[][] matrixPowMod(long[][] A, long n, long mod) {
		if (n == 0)
			return new long[][] { { 1, 0 }, { 0, 1 } };
		if (n == 1)
			return A;
		if (n % 2 == 0) {
			long[][] temp = matrixPowMod(A, n / 2, mod);
			return multMatrixMod(temp, temp, mod);
		} else {
			long[][] temp = matrixPowMod(A, n / 2, mod);
			temp = multMatrixMod(temp, temp, mod);
			return multMatrixMod(temp, A, mod);
		}
	}

	static void solve() throws IOException {
		int n = ni();
		long[] a = nal(n);

		long[][] multiplier = { { 6, mod - 1 }, { 1, 0 } };
		long sum = 0;
		for (int i = 0; i < n; i++)
			sum += a[i];

		long[][] initFn = { { 3 }, { 1 } };

		long[][] temp = matrixPowMod(multiplier, sum - 1, mod);
		long[][] result = multMatrixMod(temp, initFn, mod);

		long v0 = result[0][0];
		long v1 = result[1][0];

//		System.out.println(v0 + " " + v1);
		long[][] curMatrix = { { 1, 0 }, { 0, 1 } };

		for (int i = 0; i < n; i++) {
			curMatrix = addMatrixMod(
					curMatrix,
					multMatrixMod(curMatrix,
							matrixPowMod(multiplier, 2 * a[i], mod), mod), mod);
//			System.out.println(Arrays.toString(curMatrix[0]));
//			System.out.println(Arrays.toString(curMatrix[1]));
//			System.out.println();
		}

		long[][] mat = { { v1 }, { v0 } };

		result = multMatrixMod(curMatrix, mat, mod);

		out.println(result[1][0]);

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
