package ds;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

class PQComparator implements Comparator<TreeSet<Integer>> {
	int first1, first2, size1, size2;

	public int compare(TreeSet<Integer> pq1, TreeSet<Integer> pq2) {

		first1 = pq1.first();
		first2 = pq2.first();
		if (first1 < first2)
			return -1;
		else if (first1 > first2)
			return 1;
		else {
			size1 = pq1.size();
			size2 = pq2.size();
			if (size1 == size2)
				return 0;
			else if (size1 < size2)
				return -1;
			else
				return 1;
		}
	}
}

class ListPQComparator implements Comparator<LinkedList<Integer>> {
	int size1, size2;

	public int compare(LinkedList<Integer> arg1, LinkedList<Integer> arg2) {

		size1 = arg1.size();
		size2 = arg2.size();
		if (size1 == size2)
			return 0;
		else if (size1 < size2)
			return -1;
		else
			return 1;
	}
}

public class TeamFormation {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static int findNext(int cur, int[] arr, boolean[] chosen) {
		int pos = cur + 1;
		int n = arr.length;
		while (pos < n && arr[cur] == arr[pos]) {
			pos++;
		}
		while (pos < n && chosen[pos]) {
			pos++;
		}
		if (pos >= n)
			return -1;
		if (arr[pos] != arr[cur] + 1)
			return -1;

		return pos;
	}

	static void solve() {

		int t = ni(), n;
		int[] arr;
		while (--t >= 0) {
			n = ni();
			if (n == 0) {
				out.println("0");
				continue;
			}
			arr = na(n);

			if (n == 1) {
				out.println("1");
				continue;
			}

			Arrays.sort(arr);
//			System.out.println(Arrays.toString(arr));

			Map<Integer, PriorityQueue<Integer>> hashmap = new HashMap<Integer, PriorityQueue<Integer>>();
			// TODO Can use TreeMap<Integer,Integer> also instead of PriorityQueue<Integer>

			PriorityQueue<Integer> tempPQ = new PriorityQueue();
			int tempListSize = 0;
//			tempList.add(arr[n - 1]);
			tempListSize++;
			tempPQ.add(tempListSize);
			hashmap.put(arr[n - 1], tempPQ);

			for (int i = n - 2; i >= 0; i--) {
				if (arr[i] == arr[i + 1] - 1) {

					tempPQ = hashmap.get(arr[i + 1]);
					tempListSize = tempPQ.poll();
					if (tempPQ.isEmpty()) {
						hashmap.remove(arr[i + 1]);
					}
					tempListSize++;
					tempPQ = new PriorityQueue();
					tempPQ.add(tempListSize);
					hashmap.put(arr[i], tempPQ);

				} else if (arr[i] == arr[i + 1]) {

					if (hashmap.containsKey(arr[i] + 1)) {
						tempPQ = hashmap.get(arr[i] + 1);
						tempListSize = tempPQ.poll();
						if (tempPQ.isEmpty()) {
							hashmap.remove(arr[i] + 1);
						}
						tempListSize++;
						if (hashmap.containsKey(arr[i])) {
							tempPQ=hashmap.get(arr[i]);
							tempPQ.add(tempListSize);
						} else {
							tempPQ = new PriorityQueue();
							tempPQ.add(tempListSize);
							hashmap.put(arr[i], tempPQ);
						}
					} else {
						tempListSize = 0;
						tempListSize++;
						if (hashmap.containsKey(arr[i])) {
							tempPQ=hashmap.get(arr[i]);
							tempPQ.add(tempListSize);
						} else {
							tempPQ = new PriorityQueue();
							tempPQ.add(tempListSize);
							hashmap.put(arr[i], tempPQ);
						}
					}
				} else {
					tempListSize = 0;
					tempListSize++;
					tempPQ = new PriorityQueue();
					tempPQ.add(tempListSize);
					hashmap.put(arr[i], tempPQ);
				}
//				System.out.println(hashmap.toString());
//				System.out.println();
			}

			int minSize = n;
			for (Map.Entry entry : hashmap.entrySet()) {
				tempPQ = (PriorityQueue<Integer>) entry.getValue();
				if (tempPQ.peek() < minSize) {
					minSize = tempPQ.peek();
					if (minSize == 1)
						break;
				}
			}
			out.println(minSize);
		}

	}
	
	static void solve1() {

		int t = ni(), n;
		int[] arr;
		while (--t >= 0) {
			n = ni();
			if (n == 0) {
				out.println("0");
				continue;
			}
			arr = na(n);

			if (n == 1) {
				out.println("1");
				continue;
			}

			Arrays.sort(arr);
//			System.out.println(Arrays.toString(arr));

			Map<Integer, PriorityQueue<LinkedList<Integer>>> hashmap = new HashMap<Integer, PriorityQueue<LinkedList<Integer>>>();

			PriorityQueue<LinkedList<Integer>> tempPQ = new PriorityQueue<LinkedList<Integer>>(
					1, new ListPQComparator());
			LinkedList<Integer> tempList = new LinkedList<Integer>();
			tempList.add(arr[n - 1]);
			tempPQ.add(tempList);
			hashmap.put(arr[n - 1], tempPQ);

			for (int i = n - 2; i >= 0; i--) {
//				System.out.println(arr[i]);
//				System.out.println(hashmap.toString());
				if (arr[i] == arr[i + 1] - 1) {

					tempPQ = hashmap.get(arr[i + 1]);
					tempList = tempPQ.poll();
					if (tempPQ.isEmpty()) {
						hashmap.remove(arr[i + 1]);
					}
					tempList.addFirst(arr[i]);
					tempPQ = new PriorityQueue<LinkedList<Integer>>(1,
							new ListPQComparator());
					tempPQ.add(tempList);
					hashmap.put(arr[i], tempPQ);

				} else if (arr[i] == arr[i + 1]) {

					if (hashmap.containsKey(arr[i] + 1)) {
						tempPQ = hashmap.get(arr[i] + 1);
						tempList = tempPQ.poll();
						if (tempPQ.isEmpty()) {
							hashmap.remove(arr[i] + 1);
						}
						tempList.addFirst(arr[i]);
						if (hashmap.containsKey(arr[i])) {
							tempPQ=hashmap.get(arr[i]);
							tempPQ.add(tempList);
						} else {
							tempPQ = new PriorityQueue<LinkedList<Integer>>(1,
									new ListPQComparator());
							tempPQ.add(tempList);
							hashmap.put(arr[i], tempPQ);
						}
					} else {
						tempList = new LinkedList<Integer>();
						tempList.add(arr[i]);
						if (hashmap.containsKey(arr[i])) {
							tempPQ=hashmap.get(arr[i]);
							tempPQ.add(tempList);
						} else {
							tempPQ = new PriorityQueue<LinkedList<Integer>>(1,
									new ListPQComparator());
							tempPQ.add(tempList);
							hashmap.put(arr[i], tempPQ);
						}
					}
				} else {
					tempList = new LinkedList<Integer>();
					tempList.add(arr[i]);
					tempPQ = new PriorityQueue<LinkedList<Integer>>(1,
							new ListPQComparator());
					tempPQ.add(tempList);
					hashmap.put(arr[i], tempPQ);
				}
//				System.out.println(hashmap.toString());
//				System.out.println();
			}

			int minSize = n;
			for (Map.Entry entry : hashmap.entrySet()) {
				tempPQ = (PriorityQueue<LinkedList<Integer>>) entry.getValue();
				if (tempPQ.peek().size() < minSize) {
					minSize = tempPQ.peek().size();
					if (minSize == 1)
						break;
				}

			}
			out.println(minSize);
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
