package graphTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class Node {
	int pos;
	int steps;

	public Node(int pos, int steps) {
		this.pos = pos;
		this.steps = steps;
	}

}

public class SnakesAndLadders {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void solve() {

		int t = ni();

		int numLadders, numSnakes;
		int l1, l2, s1, s2;
		while (--t >= 0) {
			numLadders = ni();
			// nc();
			numSnakes = ni();
//			System.out.println("numLadders=" + numLadders + " numSnakes="
//					+ numSnakes);
			Map<Integer, Integer> ladderMap = new HashMap<Integer, Integer>();
			for (int i = 0; i < numLadders; i++) {
				l1 = ni();
				// nc();
				l2 = ni();
				// System.out.println("l1="+l1+" l2="+l2);
				ladderMap.put(l1, l2);
			}
			Map<Integer, Integer> snakeMap = new HashMap<Integer, Integer>();
			for (int i = 0; i < numSnakes; i++) {
				s1 = ni();
				// nc();
				s2 = ni();
				snakeMap.put(s1, s2);
			}

			Queue<Node> que = new LinkedList<Node>();
			que.add(new Node(0, 0));
			int steps = bfs(ladderMap, snakeMap, que);
			out.println(steps);
		}
	}

	private static int bfs(Map<Integer, Integer> ladderMap,
			Map<Integer, Integer> snakeMap, Queue<Node> que) {

		Node cur;
		int[] visited = new int[101];
		while (!que.isEmpty()) {

			cur = que.remove();
			visited[cur.pos] = 1;
//			System.out
//					.println("Visiting " + cur.pos + " in steps " + cur.steps);
			if (cur.pos == 100)
				return cur.steps;
			else if (ladderMap.containsKey(cur.pos)) {
//				System.out.println(cur.pos + " in ladderMap");
				cur.pos = ladderMap.get(cur.pos);
				if (visited[cur.pos] == 0) {
					visited[cur.pos] = 1;
					for (int i = 1; i <= 6; i++) {
						if (cur.pos + i <= 100 && visited[cur.pos + i] == 0) {
							que.add(new Node(cur.pos + i, cur.steps + 1));
//							System.out.println("Adding node " + (cur.pos + i)
//									+ " " + (cur.steps + 1));
						}
					}
				}
			} else if (snakeMap.containsKey(cur.pos)) {
//				System.out.println(cur.pos + " in snakeMap");
				cur.pos = snakeMap.get(cur.pos);
				if (visited[cur.pos] == 0) {
					visited[cur.pos] = 1;
					for (int i = 1; i <= 6; i++) {
						if (cur.pos + i <= 100 && visited[cur.pos + i] == 0) {
							que.add(new Node(cur.pos + i, cur.steps + 1));
						}
					}
				}
			} else {
				// System.out.println(cur.pos+" in ladderMap");
				visited[cur.pos] = 1;
				for (int i = 1; i <= 6; i++) {
					if (cur.pos + i <= 100 && visited[cur.pos + i] == 0) {
						que.add(new Node(cur.pos + i, cur.steps + 1));
					}
				}

			}
		}

		return 0;
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
