package graphTheory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class Edge {
	int dest;
	long weight;

	public Edge(int dest, long c) {
		this.dest = dest;
		this.weight = c;
	}
}

class Pair implements Comparable, Comparator {
	int vertex;
	long distance;

	public Pair(int vertex, long distance) {
		this.vertex = vertex;
		this.distance = distance;
	}

	@Override
	public int compare(Object p0, Object p1) {
		return (int) (((Pair) p0).distance - ((Pair) p1).distance);
	}

	@Override
	public int compareTo(Object p1) {
		return (int) (this.distance - ((Pair) p1).distance);
	}
}

public class SavFriends {

	public static long[] dijkstra(List<List<Edge>> adjList, int src, int a) {
		long[] dst = new long[adjList.size() + 1];

		// System.out.println("dstLength=" + dst.length);
		boolean[] visited = new boolean[dst.length];

		for (int i = 0; i < dst.length; i++)
			dst[i] = Long.MAX_VALUE;

		dst[src] = 0;

		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();

		pq.add(new Pair(src, 0));

		int visitCount = 0;

		while (visitCount < adjList.size() && !pq.isEmpty()) {
			Pair p = pq.poll();
			int v = p.vertex;
			long d = p.distance;
			dst[v] = d;
			if (visited[v] == true) {
				continue;
			}
			visited[v] = true;

			visitCount++;

			List<Edge> edgeList = adjList.get(v - 1);

			Iterator<Edge> itr = edgeList.iterator();

			while (itr.hasNext()) {
				Edge e = itr.next();

				int v1 = e.dest;
				long w = e.weight;

//				 if (!((v == src && v1 == a) || (v == a && v1 == src))) {
				// if (!visited[v1]) {
				if (dst[v1] > dst[v] + w) {
					dst[v1] = dst[v] + w;
					pq.add(new Pair(v1, dst[v1]));
				}
				// }
//				 }
			}
		}

		// int i=1;
		//
		// for(;i<dst.length;i++)
		// System.out.print(dst[i]+" ");
		//
		// System.out.println();
		return dst;

	}

	public static void main(String[] args) throws NumberFormatException,
			IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.parseInt(br.readLine());
		int n, m, k;
		int a, b;
		long c;
		int meetingA = 0;
		int meetingB = 0;
		long AB = 0;
		List<List<Edge>> adjList;
		while (testCases-- > 0) {
			// System.out.println("test="+testCases);
			String[] nmk = br.readLine().trim().split(" ");
			n = Integer.parseInt(nmk[0]);
			m = Integer.parseInt(nmk[1]);
			k = Integer.parseInt(nmk[2]);

			// System.out.println("n="+n+" m="+m+" k="+k);

			adjList = new ArrayList<List<Edge>>(n);
			for (int i = 0; i < n; i++) {
				adjList.add(new LinkedList<Edge>());
			}

			if (m == 1) {
				String[] abc = br.readLine().trim().split(" ");
				c = Long.parseLong(abc[2]);
				System.out.println(String.format("%.5f", c / 2.0) + " "
						+ String.format("%.5f", c / 2.0));
				continue;
			}

			for (int i = 1; i <= m; i++) {
				String[] abc = br.readLine().trim().split(" ");
				a = Integer.parseInt(abc[0]);
				b = Integer.parseInt(abc[1]);
				c = Long.parseLong(abc[2]);

				List<Edge> edgeList = adjList.get((int) (a - 1));
				edgeList.add(new Edge(b, c));

				edgeList = adjList.get((int) (b - 1));
				edgeList.add(new Edge(a, c));

				if (i == k) {
					meetingA = a;
					meetingB = b;
					AB = c;
				}
			}

			long[] dstA = dijkstra(adjList, meetingA, meetingB);
			long[] dstB = dijkstra(adjList, meetingB, meetingA);
			long maxA = -1;
			long maxB = -1;
			long maxA1 = -1;
			long maxB1 = -1;
			for (int i = 1; i < dstA.length; i++) {
				if (dstA[i] > maxA && dstA[i] < dstB[i]) // TODO
					maxA = dstA[i];
				if (dstB[i] > maxB && dstA[i] > dstB[i])
					maxB = dstB[i];
			}
			for (int i = 1; i < dstA.length; i++) {
				if (dstA[i] > maxA1)// && dstA[i] < dstB[i]) // TODO
					maxA1 = dstA[i];
				if (dstB[i] > maxB1)// && dstA[i] > dstB[i])
					maxB1 = dstB[i];
			}

			System.out.println("testCases=" + testCases);
			// System.out.println("n="+n+" m="+m+" k="+k);
			// System.out.println("meetingA="+meetingA+" meetingB="+meetingB+" AB="+c);
			System.out.println(maxA + " " + maxB + " " + AB);
			System.out.println(maxA1 + " " + maxB1 + " " + AB);
			System.out.println(Arrays.toString(dstA));
			System.out.println(Arrays.toString(dstB));
			// System.out.println(dstA[meetingB]+" "+dstB[meetingA]);
			double half = (maxA + maxB + AB) / 2.0;
			System.out.println("half=" + half);
			double x = (maxB - maxA + AB) / 2.0;
			if (x > AB) {
				half = maxB;
				x = AB;
			} else if (x < 0) {
				half = maxA;
				x = 0;
			}

			if (half < Math.min(maxA1, maxB1)) {
				System.out.println(String.format("%.5f", x) + " "
						+ String.format("%.5f", half));
			} else {
				if (maxA1 < maxB1) {
					System.out.println(String.format("%.5f", 0.0) + " "
							+ String.format("%.5f", 1.0 * maxA1));
				} else {
					System.out.println(String.format("%.5f", AB * 1.0) + " "
							+ String.format("%.5f", 1.0 * maxB1));
				}
			}
		}
	}

}
