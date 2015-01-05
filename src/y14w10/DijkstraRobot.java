package y14w10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

class DirectedEdge
{

	int destVertex;
	int weight;
	public DirectedEdge(int dest, int w)
	{
		this.destVertex=dest;
		this.weight=w;
	}
	
	int getVertex()
	{
		return destVertex;
	}
	
	int getWeight()
	{
		return weight;
	}
}

class Node implements Comparable {
	int vertex;
	long weight;

	public Node(int v, long w) {
		vertex = v;
		weight = w;
	}

	@Override
	public int compareTo(Object arg0) {

		long diff=weight - ((Node) arg0).weight;
		if(diff > 0)
			return 1;
		else if(diff == 0)
			return 0;
		else
			return -1;
//		return (int) (weight - ((Node) arg0).weight);
	}
}

public class DijkstraRobot {
	
	public static int edges=0;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int step = 0;
		String[] vp;
		int[] v = new int[n + 1];
		int[] p = new int[n + 1];
		long score = 0;

		while (++step <= n) {
			vp = br.readLine().split(" ");
			v[step] = Integer.parseInt(vp[0]);
			p[step] = Integer.parseInt(vp[1]);
			score+=v[step];
		}
		
		List<List<DirectedEdge>> adjList = new ArrayList<List<DirectedEdge>>(n);

		for (int i = 0; i < n; i++) {
			adjList.add(new LinkedList<DirectedEdge>());
		}

		makeGraph2(adjList,v,p,n);
		
		int endNode = n;
		int curNode = 1;

//		int[] distance = new int[n+1];
		long[] distance = new long[n+1];
		
		boolean[] visited = new boolean[n+1];
		distance[1] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		for (int i = 2; i <= n; i++) {
			pq.add(new Node(i, 1000000001));
			distance[i] = 1000000001;
		}

		List<DirectedEdge> edgeVerts;
		Iterator<DirectedEdge> itr;

		DirectedEdge nextEdge;
		int next;
		int weight;
//		long weight;
		Node node;
		while (!visited[endNode]) {
//			System.out.println("curNode="+curNode);
			edgeVerts = adjList.get(curNode-1);

			itr = edgeVerts.iterator();

			//boolean allVisited
			while (itr.hasNext()) {
				nextEdge=itr.next();
				next = nextEdge.destVertex;
				weight=nextEdge.weight;
				if (!visited[next]) {
					if (distance[curNode] + weight < distance[next]) {
						distance[next] = distance[curNode] + weight;
						pq.add(new Node(next, distance[next]));
//						System.out.println("distance["+(next-1)+"]="+distance[next-1]);
					}
				}
			}
			visited[curNode] = true;
//			if (pq.isEmpty())
//				break;
			node = pq.poll();
			curNode = node.vertex;
//			if (node.weight == 1000000001)
//				break;
//			System.out.println("curNode="+curNode);
		}

		if (distance[endNode] != 1000000001) {
			System.out.println(score-distance[endNode]);
		} else
			System.out.println("-1");
		
		
	}

	public static void makeGraph2(List<List<DirectedEdge>> adjList, int[] v, int[] p, int n)
	{
		int endJ;
		for(int i=1;i<n;i++)
		{
			endJ=i+p[i];
			if(n<endJ)
				endJ=n;
			for(int j=i+1;j<=endJ;j++)
			{
//				if(p[i]>=j-i)
//				{
					adjList.get(i-1).add(new DirectedEdge(j,v[i]));
//					edges++;
//					System.out.println("Adding edge "+i+" "+j+" weight="+v[i]+" edge no. "+edges);
//				}
			}
		}
	}
	
	public static void makeGraph(List<List<DirectedEdge>> adjList, int[] v, int[] p, int n) {
		if(n<=1)
			return;
		for(int i=n-1;i>0;i--)
		{
			if(p[i]>=n-i)
			{
				adjList.get(i-1).add(new DirectedEdge(n,v[i]));
				edges++;
//				System.out.println("Adding edge "+i+" "+n+" weight="+v[i]+" edge no. "+edges);
				makeGraph(adjList, v, p, i);
			}
		}
	}

}
