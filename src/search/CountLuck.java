package search;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	int x, y;
	Node parent;

	int numPaths;

	public Node(int x, int y, Node parent) {
		this.x = x;
		this.y = y;

		this.parent = parent;
	}
}

public class CountLuck {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int numPaths(int x, int y, String[] forest, int n, int m) {
		int numPaths = 0;
		// System.out.println("x="+x+" y="+y);
		if (y != m - 1 && isValidPos(x, y + 1, forest))
			numPaths++;
		if (y != 0 && isValidPos(x, y - 1, forest))
			numPaths++;
		if (x != n - 1 && isValidPos(x + 1, y, forest))
			numPaths++;
		if (x != 0 && isValidPos(x - 1, y, forest))
			numPaths++;

		return numPaths;

	}

	static boolean isValidPos(int x, int y, String[] forest) {

		return (forest[x].charAt(y) == '.' || forest[x].charAt(y) == '*' || forest[x].charAt(y) == 'M');

	}

	static void solve() {

		int t = ni();
		int n, m, k;
		String[] forest;
		int sx = 0, sy = 0, ex = 0, ey = 0;
		int tx, ty;
		while (--t >= 0) {

			n = ni();
			m = ni();

			forest = new String[n];
			for (int i = 0; i < n; i++) {
				forest[i] = ns();
			}

			k = ni();

			for (int i = 0; i < n; i++) {
				// System.out.println(forest[i]);
				for (int j = 0; j < m; j++) {
					if (forest[i].charAt(j) == 'M') {
						sx = i;
						sy = j;
					}
					if (forest[i].charAt(j) == '*') {
						ex = i;
						ey = j;
					}
				}
			}

			// System.out.println("sx="+sx+" sy="+sy);
			// System.out.println("ex="+ex+" ey="+ey);

			Queue<Node> que = new LinkedList<Node>();

			int[][] visited = new int[n][m];

			que.add(new Node(sx, sy, null));

			visited[sx][sy] = 1;
			Node curNode = null;

			while (!que.isEmpty()) {
				curNode = que.remove();
				tx = curNode.x;
				ty = curNode.y;
				if (tx == ex && ty == ey)
					break;
				else {
					if (ty != m - 1 && isValidPos(tx, ty + 1, forest)
							&& visited[tx][ty + 1] == 0) {
						que.add(new Node(tx, ty + 1, curNode));
						visited[tx][ty + 1] = 1;
					}
					if (ty != 0 && isValidPos(tx, ty - 1, forest)
							&& visited[tx][ty - 1] == 0) {
						que.add(new Node(tx, ty - 1, curNode));
						visited[tx][ty - 1] = 1;
					}
					if (tx != n - 1 && isValidPos(tx + 1, ty, forest)
							&& visited[tx + 1][ty] == 0) {
						que.add(new Node(tx + 1, ty, curNode));
						visited[tx + 1][ty] = 1;
					}
					if (tx != 0 && isValidPos(tx - 1, ty, forest)
							&& visited[tx - 1][ty] == 0) {
						que.add(new Node(tx - 1, ty, curNode));
						visited[tx - 1][ty] = 1;
					}
				}

			}

			// Node parent;
			int numWaves = 0;
			// if(numPaths(curNode.x, curNode.y, forest, n, m)>1)
			// numWaves++;
			curNode = curNode.parent;
			while (curNode != null) {
//				System.out.println("At node " + " " + curNode.x + " "
//						+ curNode.y);
				if (curNode.parent == null
						&& numPaths(curNode.x, curNode.y, forest, n, m) > 1) {
					numWaves++;
//					System.out.println("Wave at " + " " + curNode.x + " "
//							+ curNode.y);
				}
				if (curNode.parent != null
						&& numPaths(curNode.x, curNode.y, forest, n, m) > 2) {
					numWaves++;
					// System.out.println("Wave at " + " " + curNode.x + " "
					// + curNode.y);
				}
				curNode = curNode.parent;
			}

//			System.out.println("numWaves=" + numWaves);
			if (numWaves == k)
				out.println("Impressed");
			else
				out.println("Oops!");
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
