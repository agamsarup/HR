package y14w12;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class FavoriteSequence {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {
		int n = ni();
		int k;

		Set<Integer> tempSet;
		Map<Integer, ArrayList<Integer>> edgeList = new HashMap<Integer, ArrayList<Integer>>();
		Map<Integer, HashSet<Integer>> incomingEdgeList = new HashMap<Integer, HashSet<Integer>>();

		int v1, v2;

		for (int i = 0; i < n; i++) {
			k = ni();
			v1 = ni();
			for (int j = 0; j < k - 1; j++) {
				v2 = ni();
				if (!edgeList.containsKey(v1))
					edgeList.put(v1, new ArrayList<Integer>());
				edgeList.get(v1).add(v2);
				if (!incomingEdgeList.containsKey(v2))
					incomingEdgeList.put(v2, new HashSet<Integer>());
				incomingEdgeList.get(v2).add(v1);
				v1 = v2;
			}
		}

		Queue<Integer> contenderList = new PriorityQueue<Integer>();

		int key;
		for (Map.Entry entry : edgeList.entrySet()) {
			key = (int) entry.getKey();
			if (!incomingEdgeList.containsKey(key))
				contenderList.add(key);
		}

//		List<Integer> sortedResult = new LinkedList<Integer>();

		int curNode;
		while (!contenderList.isEmpty()) {

			curNode = contenderList.poll();
//			sortedResult.add(curNode);
			out.print(curNode + " ");
			if (edgeList.containsKey(curNode)) {
				ArrayList<Integer> EdgesToRemove = edgeList.get(curNode);
				for (int m : EdgesToRemove) {
					incomingEdgeList.get(m).remove(curNode);
					if (incomingEdgeList.get(m).isEmpty())
						contenderList.add(m);
				}
			}
		}
//		for (int v : sortedResult) {
//			out.print(v + " ");
//		}
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
