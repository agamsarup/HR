package util.ds.bst;

public class AVLNode<T> {

	public int key;
	int balance;
	int height;
	public AVLNode left;
	public AVLNode right;
	AVLNode parent;
	public T data;
	int subTreeSize;
	
	public AVLNode(int key,T data)
	{
		left=right=parent=null;
		this.key=key;
		this.data=data;
		balance=0;
		height=0;
		subTreeSize=1;
	}
	
	@Override
	public String toString() {
		return key+"";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
