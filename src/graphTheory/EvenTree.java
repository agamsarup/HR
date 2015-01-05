package graphTheory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EvenTree {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void dfs(int[][] adjMat, int curNode, int[] visited,
			int[] subtreeSize) {
		int node;
		for (int i = 1; i < adjMat.length; i++) {
			if (i != curNode && visited[i] != 1 && adjMat[curNode][i] == 1) {
				visited[i] = 1;
				dfs(adjMat, i, visited, subtreeSize);
				// if(visited[i]==0)
				subtreeSize[curNode] += subtreeSize[i];
				System.out.println("subtreeSize[" + curNode + "]="
						+ subtreeSize[curNode]);
				if (subtreeSize[curNode] % 2 == 0 && subtreeSize[curNode] >= 2)
					System.out.println(i + " " + curNode);
			}
		}
		subtreeSize[curNode]++;
	}

	static int recCalcWeight(List<LinkedList<Integer>> adjList,
			int[] subtreeWeight, int curNode, int parent) {
		List<Integer> edgeList = adjList.get(curNode);

		Iterator<Integer> itr = edgeList.iterator();
		int v2;
		int weight = 1;
		while (itr.hasNext()) {
			v2 = itr.next();

			if (v2 != parent)
				weight += recCalcWeight(adjList, subtreeWeight, v2, curNode);
		}

		subtreeWeight[curNode] = weight;
		return weight;
	}

	static void solve() {
		int n, m, v1, v2;
		n = ni();
		m = ni();

		int rootNode = 1;

		List<LinkedList<Integer>> adjList = new ArrayList<LinkedList<Integer>>();

		for (int i = 0; i <= n; i++)
			adjList.add(new LinkedList<Integer>());

		for (int i = 0; i < m; i++) {
			v1 = ni();
			v2 = ni();

			adjList.get(v1).push(v2);
			adjList.get(v2).push(v1);
		}
		
		int[] subtreeWeight = new int[n+1];

		recCalcWeight(adjList, subtreeWeight, 1, -1);
		
		int count=0;
		for(int i=2;i<=n;i++){
			if(subtreeWeight[i]%2==0)
				count++;
		}
		
		out.println(count);
	}

	static void solve1() {
		int n, m;
		n = ni();
		m = ni();

		int v1, v2;

		int[][] adjMat = new int[n + 1][n + 1];

		// List<List<Integer>> adjList=new ArrayList();

		// for(int i=0;i<n;i++)
		// {
		// adjList.add(new LinkedList<Integer>());
		// }

		for (int i = 0; i < m; i++) {
			v1 = ni();
			v2 = ni();

			adjMat[v1][v2] = 1;
			adjMat[v2][v1] = 1;
		}

		int[] leaves = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (adjMat[i][j] == 1)
					leaves[i]++;
			}
		}
		int[] subtreeSize = new int[n + 1];

		Queue<Integer> queue = new LinkedList<Integer>();
		int[] visited = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			if (leaves[i] > 1) {
				leaves[i] = 1;
				subtreeSize[i] = 1;
				queue.add(i);
				visited[i] = 1;
			} else
				leaves[i] = 0;
		}
		int node;
		while (!queue.isEmpty()) {
			node = queue.remove();
			for (int i = 1; i <= n; i++) {

			}
		}
		// dfs(adjMat, startNode, visited,subtreeSize);

		visited[1] = 1;
		for (int i = 1; i <= n; i++)
			System.out.println("i=" + i + " " + subtreeSize[i]);
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