package y14w11;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

class SubTreeWeight implements Comparable {
	int index;
	long weight;

	public SubTreeWeight(int i, long weight) {
		this.index = i;
		this.weight = weight;
	}

	@Override
	public int compareTo(Object o) {
		if (weight > ((SubTreeWeight) o).weight)
			return 1;
		else if (weight < ((SubTreeWeight) o).weight)
			return -1;
		else
			return 0;
	}
}

public class TreePruning {
	static InputStream is;
	static PrintWriter out;
	static String INPUT = "";

	static void buildAncestorList(int n,
			ArrayList<LinkedList<Integer>> adjListDirectional,
			ArrayList<HashSet<Integer>> ancestorList) {
		int v2;
		for (int i = 1; i <= n; i++) {
			Deque<Integer> deque = new LinkedList<Integer>();
			deque.addAll(adjListDirectional.get(i));
			// System.out.println(deque.size());
			while (!deque.isEmpty()) {
				v2 = deque.poll();
				// System.out.println(deq.size());
				// System.out.println("v2="+v2);
				// System.out.println("Adding"+adjListDirectional.get(v2));
				ancestorList.get(v2).add(i);
				deque.addAll(adjListDirectional.get(v2));
				// System.out.println(deq.size());
			}
		}
	}

	static void solve() {

		int n = ni(), k = ni();

		long[] weight = nal(n);

		 long[] subTreeWeight = new long[n];
		SubTreeWeight[] subTreeWeights = new SubTreeWeight[n];
		ArrayList<LinkedList<Integer>> adjList = new ArrayList<LinkedList<Integer>>(
				n + 1);
		ArrayList<HashSet<Integer>> ancestorList = new ArrayList<HashSet<Integer>>(
				n + 1);
		ArrayList<LinkedList<Integer>> adjListDirectional = new ArrayList<LinkedList<Integer>>(
				n + 1);
		adjList.add(new LinkedList<Integer>());
		ancestorList.add(new HashSet<Integer>());
		adjListDirectional.add(new LinkedList<Integer>());
		for (int i = 0; i < n; i++) {
			// subTreeWeight[i] = Long.MAX_VALUE;
			subTreeWeights[i] = new SubTreeWeight(i, Long.MAX_VALUE);
			adjList.add(new LinkedList<Integer>());
			adjListDirectional.add(new LinkedList<Integer>());
			ancestorList.add(new HashSet<Integer>());
		}

		int v1, v2;
		for (int i = 1; i < n; i++) {
			v1 = ni();
			v2 = ni();
			adjList.get(v1).add(v2);
			adjList.get(v2).add(v1);
		}

		Deque<Integer> deq = new LinkedList<Integer>();
		int weightCalcCount = 0;
		for (int i = 1; i <= n; i++) {
			if (adjList.get(i).size() == 1) {
				// subTreeWeight[i - 1] = weight[i - 1];
				subTreeWeights[i - 1].weight = weight[i - 1];
				deq.addAll(adjList.get(i));
				// adjListDirectional.get(i).addAll(adjList.get(i));
				// ancestorList.get(i).addAll(adjList.get(i));
				weightCalcCount++;
			}
		}

		int cur;
		LinkedList<Integer> edgeList;
		Iterator<Integer> itr;
		while (weightCalcCount < n && !deq.isEmpty()) {
			cur = deq.poll();
			// out.println("cur="+cur);
			// if (subTreeWeight[cur - 1] == Long.MAX_VALUE) {
			if (subTreeWeights[cur - 1].weight == Long.MAX_VALUE) {
				// subTreeWeight[cur - 1] = weight[cur - 1];
				subTreeWeights[cur - 1].weight = weight[cur - 1];
				edgeList = adjList.get(cur);
				itr = edgeList.iterator();
				// out.println("b1");

				while (itr.hasNext()) {
					v2 = itr.next();
					// out.println("v2="+v2);
					// if (subTreeWeight[v2 - 1] == Long.MAX_VALUE)
					if (subTreeWeights[v2 - 1].weight == Long.MAX_VALUE) {
						if (v2 != 1)
							deq.add(v2);
					} else {
						// subTreeWeight[cur - 1] += subTreeWeight[v2 - 1];
						subTreeWeights[cur - 1].weight += subTreeWeights[v2 - 1].weight;
						adjListDirectional.get(cur).add(v2);
//						System.out.println("Adding edge between " + cur + " "
//								+ v2);
					}
				}
				weightCalcCount++;
			}
		}
		edgeList = adjList.get(1);
		itr = edgeList.iterator();
		// out.println("b1");

		subTreeWeights[0].weight = weight[0];
		while (itr.hasNext()) {
			v2 = itr.next();
			subTreeWeights[0].weight += subTreeWeights[v2 - 1].weight;
			adjListDirectional.get(1).add(v2);
//			System.out.println("Adding edge between 1 " + v2);
		}

		// subTreeWeight[0]+=weight[0];
		// out.println(weightCalcCount);
		// long rootWeight = subTreeWeight[0];
		long rootWeight = subTreeWeights[0].weight;
//		out.println("weightCalcCount=" + weightCalcCount);
//		out.println("rootWeight=" + rootWeight);

		// Arrays.sort(subTreeWeight);
		for(int i=0;i<n;i++)
			subTreeWeight[i]=subTreeWeights[i].weight;
		Arrays.sort(subTreeWeights);

		// System.out.println(Arrays.toString(subTreeWeight));
		// System.out.println(subTreeWeights);

		buildAncestorList(n, adjListDirectional, ancestorList);

		long weightToRemove = 0;
		int max = Math.min(n, k);
		int i = -1;
		Set<Integer> selected = new HashSet<Integer>();
		boolean selectCur;
		while (++i < n && k > 0) {
			if (subTreeWeights[i].weight < 0) {
				selectCur = true;
				// edgeList = adjList.get(i + 1);
				// itr = edgeList.iterator();
				//
				// while (itr.hasNext()) {
				// v2 = itr.next();
				// if (selected.contains(v2)) {
				// selectCur=false;
				// break;
				// }
				// }

				HashSet<Integer> ancestors = ancestorList
						.get(subTreeWeights[i].index + 1);
				// System.out.println(ancestors);
				Set<Integer> selectedAncestors = new HashSet<Integer>(selected);
				// System.out.println(selectedAncestors);
				selectedAncestors.retainAll(ancestors);
				// System.out.println(selectedAncestors);
				// System.out.println();
				if (selectedAncestors.size() > 0)
					selectCur = false;

				// Also check if subTreeWeights[i].index+1 is an ancestor of
				// already selected subtrees

				if (selectCur) {
					Set<Integer> set = new HashSet<Integer>();
					
					long combinedWeight = getAlreadySelectedChildren(
							subTreeWeights[i].index + 1, selected,
							adjListDirectional, set, subTreeWeights, subTreeWeight);

//					System.out.println("For i="+(subTreeWeights[i].index + 1));
//					System.out.println("combinedWeight="+combinedWeight);
					if (set.isEmpty()) {
						k--;
						weightToRemove += subTreeWeights[i].weight;
//						System.out.println("Selecting1 " + (subTreeWeights[i].index + 1));
						selected.add(subTreeWeights[i].index + 1);
					}
					else
					{
						if(combinedWeight > subTreeWeights[i].weight)
						{
							k--;
							weightToRemove += subTreeWeights[i].weight-combinedWeight;
//							System.out.println("Selecting2 " + (subTreeWeights[i].index + 1));
							selected.add(subTreeWeights[i].index + 1);
							selected.removeAll(set);
						}
					}
				}
			} else
				break;
		}

//		out.println("weightToRemove=" + weightToRemove);
		out.println(rootWeight - weightToRemove);
	}

	private static long getAlreadySelectedChildren(int i,
			Set<Integer> selected,
			ArrayList<LinkedList<Integer>> adjListDirectional,
			Set<Integer> set, SubTreeWeight[] subTreeWeights, long[] subTreeWeight) {

		long combinedWeight = 0;

		LinkedList<Integer> edgeList;

		Deque<Integer> deque = new LinkedList<Integer>();
		deque.addAll(adjListDirectional.get(i));
//		System.out.println(deque);
		int v2;
		while (!deque.isEmpty()) {
			v2 = deque.poll();
//			System.out.println("v2="+v2);
			if (selected.contains(v2)) {
//				System.out.println("v2 selected="+v2);
				combinedWeight += subTreeWeight[v2 - 1];
				set.add(v2);
			}
			deque.addAll(adjListDirectional.get(v2));
		}

		return combinedWeight;
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

	private static long[] nal(int n) {
		long[] a = new long[n];
		for (int i = 0; i < n; i++)
			a[i] = nl();
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
