package ds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

class WeightedElement implements Comparable {
	long val;
	long weight;
	int idx;

	public WeightedElement(int i, long v, long w) {
		this.idx = i;
		this.val = v;
		this.weight = w;
	}

	public String toString(){
		return idx+" "+val+" "+weight;
		
	}
	
	@Override
	public int compareTo(Object o) {
		
		if(val < ((WeightedElement)o).val)
			return -1;
		
		else if(val > ((WeightedElement)o).val)
			return 1;
		else {
			if(idx > ((WeightedElement)o).idx)
				return -1;
			else
				return 1;
		}
	}
}

public class SubsequenceWeighting {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {

		int t = ni(), n;

		BinaryIndexedTree bit;
		
		long[] a;
		long[] w;
		WeightedElement el;
		long tVal;
		long maxWeight;
		while (--t >= 0) {
			maxWeight=0;
			n = ni();
			a=nal(n);
			w=nal(n);
			ArrayList<WeightedElement> list=new ArrayList<WeightedElement>(n);
			for (int i = 0; i < n; i++) {
				list.add(new WeightedElement(i+1, a[i], w[i]));
			}
			
			Collections.sort(list);
			
//			for()
			System.out.println(list.toString());
			
			bit=new BinaryIndexedTree(n);
			for(int i=0;i<n;i++){
				el=list.get(i);
				bit.update(el.idx, el.weight);
//				System.out.println("update done");
				tVal=bit.read(el.idx);
//				System.out.println("read done");
				if(tVal > maxWeight)
					maxWeight=tVal;
			}
			
			out.println(maxWeight);
		}
	}

	static void solve1() {

		int t = ni(), n;
		int[] a;
		long[] w;
		int[] p, m;
		int length, l, r, mid;

		while (--t >= 0) {
			long maxWeight = 0;
			n = ni();
			a = new int[n];
			w = new long[n];
			p = new int[n];
			m = new int[n + 1];
			long[] wtSum = new long[n + 1];
			for (int i = 0; i < n; i++)
				a[i] = ni();
			for (int i = 0; i < n; i++)
				w[i] = nl();

			length = 0;
			for (int i = 0; i < n; i++) {
				l = 1;
				r = length;
				while (l <= r) {
					mid = (l + r + 1) / 2;

					if (a[m[mid]] < a[i])
						l = mid + 1;
					else
						r = mid - 1;
				}
				// System.out.println(l);
				p[i] = m[l - 1];
				m[l] = i;
				// length = l;
				if (wtSum[p[i]] + w[i] > wtSum[i]) {
					wtSum[i] = wtSum[p[i]] + w[i];
					if (wtSum[i] > maxWeight)
						maxWeight = wtSum[i];
				}
				// if (wtSum[l - 1] + w[i] > wtSum[l]) {
				// wtSum[l] = wtSum[l - 1] + w[i];
				// if (wtSum[l] > maxWeight)
				// maxWeight = wtSum[l];
				// }
				// if(l>length)
				// length=l;
			}

			System.out.println(Arrays.toString(p));
			System.out.println(Arrays.toString(m));
			System.out.println(Arrays.toString(wtSum));
			out.println(maxWeight);
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
