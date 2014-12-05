package y14w12;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ChiefHop {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int findMaxPos(int[] heights) {
		int n = heights.length;
		int maxPos = 0;
		for (int i = 1; i < n; i++) {
			if (heights[maxPos] < heights[i])
				maxPos = i;
		}

		return maxPos;
	}

	static BigInteger calcMinEnergyBI(int[] heights, BigInteger startEnergy) {
		BigInteger curEnergy = startEnergy;
		BigInteger minEnergy = curEnergy;
		int n = heights.length;

		int comparison;
		for (int i = 0; i < n; i++) {
			BigInteger height = new BigInteger(Integer.toString(heights[i]));
//			System.out.println("height="+height.toString());
//			System.out.println("curEnergy="+curEnergy.toString());
			comparison = curEnergy.compareTo(height);
			if (comparison == -1) {
				curEnergy = curEnergy.add(curEnergy).subtract(height);
				// curEnergy -= (heights[i] - curEnergy);
				// minEnergy = Math.min(minEnergy, curEnergy);
			} else {
				// curEnergy += curEnergy - heights[i];
				curEnergy = curEnergy.add(curEnergy).subtract(height);
				// minEnergy = Math.min(minEnergy, curEnergy);
			}
			minEnergy = minEnergy.min(curEnergy);
			comparison = minEnergy.compareTo(BigInteger.ZERO);
			if (comparison == -1)
				return minEnergy;
		}
		return minEnergy;
	}

	static long calcMinEnergy(int[] heights, long startEnergy) {
		long curEnergy = startEnergy;
		long minEnergy = curEnergy;
		int n = heights.length;

		for (int i = 0; i < n; i++) {
			if (heights[i] > curEnergy) {
				curEnergy -= (heights[i] - curEnergy);
				minEnergy = Math.min(minEnergy, curEnergy);
			} else {
				curEnergy += curEnergy - heights[i];
				minEnergy = Math.min(minEnergy, curEnergy);
			}
			if (minEnergy < 0)
				return minEnergy;
		}
		return minEnergy;
	}

	static void solve1() {
		int n = ni();

		int[] heights = na(n);

		int maxPos = findMaxPos(heights);

	}

	static void solve2() {
		int n = ni();

		int[] heights = na(n);

		long minEnergy;
		long left = 0;
		long right = (Long.MAX_VALUE - 1) / 2;
		long mid;
		long res = 0;
		while (left < right) {
			mid = (left + right) / 2;
			minEnergy = calcMinEnergy(heights, mid);
			System.out.println("mid=" + mid);
			System.out.println("minEnergy=" + minEnergy);
			if (minEnergy < 0)
				left = mid + 1;
			else if (minEnergy > 0)
				right = mid;
			else {
				res = mid;
				break;
			}

		}
		System.out.println(left);
		System.out.println(right);
		if (left >= right) {
			res = right;
			while (calcMinEnergy(heights, res) < 0)
				res++;
		}
		out.println(res);
	}

	static void solve() {
		int n = ni();

		int[] heights = na(n);

		BigInteger left=BigInteger.ZERO;
		BigInteger right=new BigInteger(Long.MAX_VALUE+"");
		BigInteger minEnergy,mid,res = null;

		int comparison;
		while (left.compareTo(right) == -1) {
			mid=left.add(right).divide(new BigInteger("2"));
			minEnergy = calcMinEnergyBI(heights, mid);
//			System.out.println("mid="+mid.toString());
//			System.out.println("minEnergy="+minEnergy.toString());
			comparison=minEnergy.compareTo(BigInteger.ZERO);
			if (comparison ==-1 )
				left = mid.add(BigInteger.ONE);
			else if (minEnergy.compareTo(BigInteger.ZERO) == 1)
				right = mid;
			else {
				res = mid;
				break;
			}

		}
//		 System.out.println("left="+left.toString());
//		 System.out.println("right="+right.toString());
		 comparison=left.compareTo(right);
		if (comparison!=-1) {
			res = right;
			
			while (calcMinEnergyBI(heights, res).compareTo(BigInteger.ZERO) == -1)
				res=res.add(BigInteger.ONE);
		}
		out.println(res.toString());
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

