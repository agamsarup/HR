package graphTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class SubsetComponent {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static int numSubsets = 1048576;

	static int countZeroBits(long n){
		int count=0;
		for(int i=0;i<64;i++){
			if(((n>>>i) & 1) ==0)
				count++;
		}
		
		return count;
	}
	
	static void solve() {

		int n = ni();

		long[] arr = nal(n);
		long allOR=0;
		
		for(int i=0;i<n;i++){
			allOR=allOR|arr[i];
		}
		
		numSubsets=1<<n;
		if(allOR==0){
			out.println(numSubsets*64);
			return;
		}
		
		List<Long> prevComponents,newComponents;// =new LinkedList<Integer>();
		int totalConnectedComp = 0;
		for (int i = 0; i < numSubsets; i++) {
			prevComponents = new ArrayList<Long>();
			allOR=0;
//			System.out.print("{ ");
			for (int j = 0; j < n; j++) {
			
				if (((i >>> j) & 1) == 1) {
//					System.out.print(arr[j]+" ");
					allOR=arr[j];
					newComponents = new ArrayList<Long>();
					for (int k = 0; k < prevComponents.size(); k++) {
						long bitwiseAnd = prevComponents.get(k) & arr[j];
						if (bitwiseAnd!=0) {
							allOR=allOR|prevComponents.get(k);
						}
						else{
							newComponents.add(prevComponents.get(k));
						}
					}

					newComponents.add(allOR);
					prevComponents=newComponents;
				}
			}

//			System.out.print("} ");
			long compOR = 0;
			for (int k = 0; k < prevComponents.size(); k++) {
				compOR=compOR|prevComponents.get(k);
			}
			
			totalConnectedComp += prevComponents.size()+countZeroBits(compOR);
//			System.out.println(totalConnectedComp);
		}
		
		out.println(totalConnectedComp);
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
