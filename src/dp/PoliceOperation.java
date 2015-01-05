package dp;
// Credits
// I/O code taken from uwi

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Queue;

class Line{
	long A,B;
	public Line(long a, long b)
	{
		A=a;
		B=b;
	}
	public long eval(long x)
	{
		return A*x+B;
	}
}

public class PoliceOperation {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	public void addToQueue(Deque<Line> que, int i, long[] pos, long[] dp)
	{
		Line cur = new Line(-2*pos[i], dp[i]+pos[i]*pos[i]);
		
		while(!que.isEmpty() && que.)
	}
	
	static void solve() {
		int n = ni(), h = ni();
		if (n == 0) {
			out.println(0);
			return;
		}
		int[] pos = na(n);

		long[] dp = new long[n + 1];
		dp[0] = 0;
		for(int i=1;i<=n;i++)
			dp[i]=Long.MAX_VALUE;
		long ed;
		Deque<Line> deq = new ArrayDeque<Line>();
		
		
		
		for(int i=1;i<=n;i++)
		{/*
			int l=0;
			int r=i-1,mid;
			long vl, vr, edl,edr,vmid,edmid;
			edl=pos[i-1]-pos[l];
			edr=pos[i-1]-pos[r];
			vl=dp[l]+h+edl*edl;
			vr=dp[r]+h+edr*edr;
			while(l<r)
			{
				mid=(l+r)/2;
				edmid=pos[i-1]-pos[mid];
				vmid=dp[mid]+h+edmid*edmid;
				if()
			}
			*/for(int j=i-1;j>=0;j--)
			{
				ed=pos[i-1]-pos[j];
				if(ed*ed > h)
					break;
				dp[i]=Math.min(dp[i],dp[j]+h+ed*ed);
			}
		}

		out.println(dp[n]);
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
