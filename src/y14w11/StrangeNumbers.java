package y14w11;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class StrangeNumbers {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {

		Set<Long> sortedSet = new TreeSet<Long>();
		ArrayList<Set<Long>> listOfSet = new ArrayList<Set<Long>>(18);

		Set<Long> set = new HashSet<Long>();
		for (long i = 1; i < 10; i++)
			set.add(i);
		listOfSet.add(0, set);
		sortedSet.addAll(set);
		sortedSet.add((long) 0);

		Set<Long> prevSet, curSet, nextSet;
		long low=1, high=10;
		
		for (int i = 1; i < 10; i++) {
			prevSet = listOfSet.get(i - 1);
			curSet = new HashSet<Long>();
			Iterator<Long> itr = prevSet.iterator();
			low = high;
			high *=10; 
			while (itr.hasNext()) {
				long l = itr.next() * (i + 1);
				if (l < high && l >= low)
					curSet.add(l);
			}

			prevSet = curSet;
			do {
				nextSet = new HashSet<Long>();
				itr = prevSet.iterator();
				while (itr.hasNext()) {
					long l = itr.next() * (i + 1);
					if (l < high && l >= low)
						nextSet.add(l);
				}
				curSet.addAll(nextSet);
				prevSet = nextSet;
			} while (nextSet.size() > 0);

			listOfSet.add(curSet);
			sortedSet.addAll(curSet);
		}

		for (int i = 10; i < 18; i++) {
			prevSet = new HashSet<Long>(listOfSet.get(i - 1));
			prevSet.addAll(listOfSet.get(i - 2));
			curSet = new HashSet<Long>();
			Iterator<Long> itr = prevSet.iterator();
			low = high;
			high *= 10;
//			System.out.println(high);
			while (itr.hasNext()) {
				long l = itr.next() * (i + 1);
				if (l < high && l >= low)
					curSet.add(l);
			}

			prevSet = curSet;
			do {
				nextSet = new HashSet<Long>();
				itr = prevSet.iterator();
				while (itr.hasNext()) {
					long l = itr.next() * (i + 1);
					if (l < high && l >= low)
						nextSet.add(l);
				}
				curSet.addAll(nextSet);
				prevSet = nextSet;
			} while (nextSet.size() > 0);

			listOfSet.add(curSet);
			sortedSet.addAll(curSet);
//			System.out.println(Arrays.toString(curSet.toArray()));
//			System.out.println(curSet.size());
		}

		Object[] array = sortedSet.toArray();
		
//		System.out.println(array[array.length-1]);
		
		for (int T = ni(); T >= 1; T--) {
			long l = nl(), r = nl();

			low=Arrays.binarySearch(array, l);
			high=Arrays.binarySearch(array,r);

			if(low<0)
				low=-low-1;
			if(high<0)
				high=-high-2;
			
			out.println(high-low+1);
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
