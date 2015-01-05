package ds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

class Ship implements Comparable<Ship> {
	int y;
	int f;

	public Ship(int y, int f) {
		this.y = y;
		this.f = f;
	}

	public String toString(){
		return y+" "+f;
	}
	
	@Override
	public int compareTo(Ship o) {
		if (y < o.y)
			return -1;
		else if (y > o.y)
			return 1;

		return 0;
	}

}

public class Starfleet {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {

		int n = ni(), q = ni(), v = ni();

		Ship[] ships = new Ship[n];
		int numQ;
		for (int i = 0; i < n; i++) {
			ni();
			ships[i] = new Ship(ni(), ni());
		}

		Arrays.sort(ships);
//		System.out.println(Arrays.toString(ships));
//		numQ = ni();
//		System.out.println("q="+q);
		
		int y1, y2;
		int p1, p2,diff;
		for (int i = 0; i < q; i++) {
			y1 = ni();
			y2 = ni();
			ni();

			p1 = Arrays.binarySearch(ships, new Ship(y1, 0));
			p2 = Arrays.binarySearch(ships, new Ship(y2, 0));
			if (p1 < 0) {
				p1 = -1 - p1;

			} else {
				while (p1 < n && ships[p1].y == y1)
					p1++;
			}
			if (p2 < 0) {
				p2 = -1 - p2 - 1;
			} else {
				while (p2 >= 0 && ships[p2].y == y2)
					p2--;
			}
			
			ArrayList<Integer> list=new ArrayList<Integer>();
			for(int j=p2+1;j<p1;j++){
				list.add(ships[j].f);
			}
			
			Collections.sort(list);
			
//			System.out.println(list.toString());
			int maxGroup=0;
			int size=list.size();
			int curGp=1;
			for(int j=1;j<size;j++){
				if(list.get(j)==list.get(j-1))
					curGp++;
				else{
					if(curGp>maxGroup)
						maxGroup=curGp;
					curGp=1;
				}
			}
			
			if(curGp > maxGroup)
				maxGroup=curGp;
			out.println(maxGroup);
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
