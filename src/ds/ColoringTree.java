package ds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ColoringTree {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int recHeight(List<List<Integer>> adjList, int curNode,
			int parentNode) {

		List<Integer> edges = adjList.get(curNode);
		int maxHeight = 0;
		int curHeight;
		for (int v : edges) {
			if (v != parentNode) {
				curHeight = recHeight(adjList, v, curNode) + 1;
				if (curHeight > maxHeight)
					maxHeight = curHeight;
			}
		}

		return maxHeight;
	}

	static void solve() {

		int n = ni(), m = ni(), root = ni();

		int v1, v2;
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(
				(int) (n + 1));
		for (int i = 0; i <= n; i++) {
			adjList.add(new LinkedList<Integer>());
		}
		int[] color = new int[n + 1];
		for (int i = 1; i < n; i++) {
			v1 = ni();
			v2 = ni();

			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
		}
//		System.out.println(adjList.toString());
		for (int i = 1; i <= n; i++)
			color[i] = ni();

		int maxHeight = recHeight(adjList, root, 0);
		List<Set<Integer>> heightList = new ArrayList<Set<Integer>>(
				maxHeight + 1);

		for (int i = 0; i <= maxHeight; i++) {
			heightList.add(new HashSet<Integer>());
		}

//		System.out.println("maxHeight=" + maxHeight);

		fillHeightList(adjList, heightList, root, 0, maxHeight);

		List<Set<Integer>> uniqueColors = new ArrayList<Set<Integer>>(n + 1);
		for (int i = 0; i <= n; i++) {
			uniqueColors.add(new HashSet<Integer>());
		}

		Set<Integer> levelNodes = heightList.get(0);
		for (int v : levelNodes) {
			uniqueColors.get(v).add(color[v]);
		}
		for (int i = 1; i <= maxHeight; i++) {
			levelNodes = heightList.get(i);
			for (int v : levelNodes) {
				// take union of sets of the children
				List<Integer> edges = adjList.get(v);
				for (int u : edges) {
					if (heightList.get(i - 1).contains(u)) {
						uniqueColors.get(v).addAll(uniqueColors.get(u));
					}
				}
				uniqueColors.get(v).add(color[v]);
			}
		}

		int query;
		for (int i = 1; i <= m; i++) {
			query = ni();

			out.println(uniqueColors.get(query).size());
		}
	}

	private static void fillHeightList(List<List<Integer>> adjList,
			List<Set<Integer>> heightList, int node, int parent, int curHeight) {

		heightList.get(curHeight).add(node);
		List<Integer> edges = adjList.get(node);
		for (int v : edges) {
			if (v != parent) {
				fillHeightList(adjList, heightList, v, node, curHeight - 1);
			}
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
