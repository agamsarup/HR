package ds;

public class BinaryIndexedTree {

	// long[] data;
	long[] tree;

	static int MaxVal;
	public BinaryIndexedTree(int n) {
		tree = new long[n+1];
		MaxVal=n;
	}

	public long read(int idx) {
		long sum = 0;
		while (idx > 0) {
			sum += tree[idx];
			idx -= (idx & -idx);
		}
		return sum;
	}

	void update(int idx, long val) {
		while (idx <= MaxVal) {
			System.out.println("idx="+idx);
			tree[idx] += val;
			System.out.println("t[idx]="+tree[idx]);
			idx += (idx & -idx);
		}
	}
}
