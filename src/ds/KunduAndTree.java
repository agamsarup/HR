package ds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KunduAndTree {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";
	static long mod = 1000000007;

	private static int bfs(int i, int[] visited, List<List<Integer>> adjList) {

		Queue<Integer> que = new LinkedList<Integer>();

		que.add(i);
		visited[i] = 1;
		int curNode;
		List<Integer> edges;
		int compSize = 1;
		while (!que.isEmpty()) {
			curNode = que.poll();
			edges = adjList.get(curNode);

			for (int v2 : edges) {
				if (visited[v2] == 0) {
					que.add(v2);
					visited[v2] = 1;
					compSize++;
				}
			}
		}

		// System.out.println(compSize);
		return compSize;
	}

	static void solve() {

		long n = nl();

		int v1, v2;
		List<List<Integer>> adjList = new ArrayList<List<Integer>>(
				(int) (n + 1));
		for (int i = 0; i <= n; i++) {
			adjList.add(new LinkedList<Integer>());
		}
		for (int i = 1; i < n; i++) {
			v1 = ni();
			v2 = ni();
			if (nc() == 'b') {
				adjList.get(v1).add(v2);
				adjList.get(v2).add(v1);
			}

		}

		int[] visited = new int[(int) (n + 1)];

		int i = 1;
		List<Integer> compSizes = new ArrayList<Integer>();
		long compSize;
		// int size1ConnectedComponents = 0;
		// int totalSize = 0;
		int numComponents = 0;
		while (true) {
			while (i <= n && visited[i] == 1)
				i++;
			if (i >= n)
				break;
			compSize = bfs(i, visited, adjList);

			numComponents++;
			compSizes.add((int) compSize);
			i++;
		}

		long triplets = 0;

		for (i = 0; i < numComponents; i++) {
			compSize = compSizes.get(i);

			// triplets = (triplets + (compSize * (compSize - 1) * (compSize -
			// 2))
			// / 6 + (compSize * (compSize - 1) * (n - compSize)) / 2)
			// % mod;
			triplets = (triplets + (compSize * (compSize - 1) * (3 * n - 2
					* compSize - 2)) / 6)
					% mod;
		}

		triplets = ((n * (n - 1) * (n - 2)) / 6 - triplets + mod) % mod;

		out.println(triplets);
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
