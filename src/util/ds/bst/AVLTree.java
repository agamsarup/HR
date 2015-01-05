package util.ds.bst;

import java.util.ArrayList;

public class AVLTree<T> {

	protected AVLNode<T> root;
	
	public AVLTree()
	{
		root=null;
	}
	
	public AVLNode<T> getRoot()
	{
		return root;
	}
	
	public T insert(int key, T data)
	{
		AVLNode<T> node = new AVLNode<T>(key, data);
		return insertNode(root, node);
	}
	
	private void recStateUpdate(AVLNode<T> n)
	{
		recHeightUpdate(n);
		recSubTreeSizeUpdate(n);
	}
	
	private void recSubTreeSizeUpdate(AVLNode<T> n)
	{
		if(n==null)
			return;

		n.subTreeSize=subtreeSize(n);
		recSubTreeSizeUpdate(n.parent);
	}
	
	private void recHeightUpdate(AVLNode<T> n)
	{
		if(n==null)
			return;
		
		n.height = height(n);
		recHeightUpdate(n.parent);
	}
	
	private T insertNode(AVLNode<T> n1, AVLNode<T> n2) {
		
		if(n1 == null)
			root=n2;
		else
		{
			if(n2.key<n1.key)
			{
				if(n1.left==null)
				{
					n1.left=n2;
					n2.parent=n1;
					
					recHeightUpdate(n1);
					recBalance(n1);
				}
				else
					insertNode(n1.left, n2);
			}
			else if(n2.key>n1.key)
			{
				if(n1.right==null)
				{
					n1.right=n2;
					n2.parent=n1;
					
					recHeightUpdate(n1);
					recBalance(n1);
				}
				else
					insertNode(n1.right, n2);
			}
			else
			{
				// node already exists
				return n1.data;
			}
		}
		return null;
	}

	
	public static int subtreeSize(AVLNode n)
	{
		if(n==null)
			return 0;
		if(n.left==null && n.right==null)
			return 1;
		else if(n.left==null)
		{
			return 1+n.right.subTreeSize;//height(n.right);
		}
		else if(n.right==null)
		{
			return 1+n.left.subTreeSize;//height(n.left);
		}
		else
		{
//			return 1+Math.max(height(n.left), height(n.right));
			return 1+n.left.subTreeSize+n.right.subTreeSize;
		}
	}
	
	private int height(AVLNode<T> n)
	{
		if(n==null)
			return -1;
		if(n.left==null && n.right==null)
			return 0;
		else if(n.left==null)
		{
			return 1+n.right.height;//height(n.right);
		}
		else if(n.right==null)
		{
			return 1+n.left.height;//height(n.left);
		}
		else
		{
//			return 1+Math.max(height(n.left), height(n.right));
			return 1+Math.max(n.left.height,n.right.height);
		}
	}
	
	private void recBalance(AVLNode<T> n) {
		
		setBalance(n);
		int balance = n.balance;
		
		if(balance==-2)
		{
			if(height(n.left.left) >= height(n.left.right))
			{
				// Left Left Case
				n=rotateRight(n);
			}
			else
			{
				// Left Right Case
				n=rotateLeftRight(n);
			}
		}
		else if(balance==2)
		{
			if(height(n.right.right) >= height(n.right.left))
			{
				//Right Right Case
				n=rotateLeft(n);
			}
			else
			{
				// Right Left Case
				n=rotateRightLeft(n);
			}
		}
		
		if(n.parent!=null)
		{
			recBalance(n.parent);
		}
		else
			root=n;
		
	}

	private AVLNode<T> rotateRightLeft(AVLNode<T> n) {
		n.right = rotateRight(n.right);
		return rotateLeft(n);
	}

	private AVLNode<T> rotateLeft(AVLNode<T> n) {
		
		AVLNode<T> v = n.right; // new root
		v.parent = n.parent;

		n.right = v.left;

		if (n.right != null) {
			n.right.parent = n;
		}

		v.left = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

//		recHeightUpdate(n);
		recStateUpdate(n);
		setBalance(n);
		setBalance(v);

		return v;
	}

	private AVLNode<T> rotateLeftRight(AVLNode<T> n) {
		n.left = rotateLeft(n.left);
		return rotateRight(n);
	}
	
	private AVLNode<T> rotateRight(AVLNode<T> n) {
		AVLNode<T> v = n.left;
		v.parent = n.parent;

		n.left = v.right;

		if (n.left != null) {
			n.left.parent = n;
		}

		v.right = n;
		n.parent = v;

		if (v.parent != null) {
			if (v.parent.right == n) {
				v.parent.right = v;
			} else if (v.parent.left == n) {
				v.parent.left = v;
			}
		}

//		recHeightUpdate(n);
		recStateUpdate(n);
		setBalance(n);
		setBalance(v);

		return v;
	}

	private void setBalance(AVLNode<T> n) {
		n.balance = height(n.right) - height(n.left);
	}

	public void remove(int k) {
		// First we must find the node, after this we can delete it.
		removeAVL(this.root, k);
	}
	
	private void removeAVL(AVLNode<T> p, int q) {
		if (p == null) {
			return;
		} else {
			if (p.key > q) {
				removeAVL(p.left, q);
			} else if (p.key < q) {
				removeAVL(p.right, q);
			} else if (p.key == q) {
				// we found the node in the tree.. now lets go on!
				removeFoundNode(p);
			}
		}
	}

	private void removeNode(AVLNode<T> x)
	{
		AVLNode<T> z = null,y;
		// check if the node is a leaf or has 1 child
		if(x.left == null || x.right == null)
		{
			z=x;
		}
		else
		{
			// find y=successor of x i.e.
			// the largest node in node X's left sub tree (in-order predecessor) 
			// or 
			// the smallest in its right sub tree (in-order successor).
			y=successor(x);
			x.key=y.key;
			x.data=y.data;
			z=y;
		}
		
		// From step 5
		AVLNode<T> zSubTree=null;
		if(z.left == null)
			zSubTree=z.right;
		if(z.right==null)
			zSubTree=z.left;
		
		if(z.parent==null)
			root=zSubTree;
		else
		{
			if(z.parent.left==z)
				z.parent.left=zSubTree;
			else
				z.parent.right=zSubTree;

			zSubTree.parent=z.parent;
			recBalance(z.parent);
		}

		z=null;
	}
	
	
	private void removeFoundNode(AVLNode<T> q) {
		AVLNode<T> r;
		// at least one child of q, q will be removed directly
		if (q.left == null || q.right == null) {
			// the root is deleted
			if (q.parent == null) {
				this.root = null;
				q = null;
				return;
			}
			r = q;
		} else {
			// q has two children --> will be replaced by successor
			r = successor(q);
			q.key = r.key;
		}

		AVLNode<T> p;
		if (r.left != null) {
			p = r.left;
		} else {
			p = r.right;
		}

		if (p != null) {
			p.parent = r.parent;
		}

		if (r.parent == null) {
			this.root = p;
		} else {
			if (r == r.parent.left) {
				r.parent.left = p;
			} else {
				r.parent.right = p;
			}
			// balancing must be done until the root is reached.
			recBalance(r.parent);
		}
		r = null;
		
	}

	private AVLNode<T> successor(AVLNode<T> q) {
		if (q.right != null) {
			AVLNode<T> r = q.right;
			while (r.left != null) {
				r = r.left;
			}
			return r;
		} else {
			AVLNode<T> p = q.parent;
			while (p != null && q == p.right) {
				q = p;
				p = q.parent;
			}
			return p;
		}
	}

	final protected ArrayList<AVLNode<T>> inorder() {
		ArrayList<AVLNode<T>> ret = new ArrayList<AVLNode<T>>();
		inorder(root, ret);
		return ret;
	}

	final protected void inorder(AVLNode<T> n, ArrayList<AVLNode<T>> io) {
		if (n == null) {
			return;
		}
		inorder(n.left, io);
		io.add(n);
		inorder(n.right, io);
	}

	/**
	  * Only for debugging purposes. Gives all information about a node.
	 
	  * @param n The node to write information about.
	  */
	public void debug(AVLNode<T> n) {
		int l = -1;
		int r = -1;
		int p = -1;
		if (n.left != null) {
			l = n.left.key;
		}
		if (n.right != null) {
			r = n.right.key;
		}
		if (n.parent != null) {
			p = n.parent.key;
		}

		System.out.println("Left: " + l + " Key: " + n + " Right: " + r
				+ " Parent: " + p + " Balance: " + n.balance+" height: "+n.height);

		if (n.left != null) {
			debug(n.left);
		}
		if (n.right != null) {
			debug(n.right);
		}
	}
	 
	public static void main(String[] args) {
		AVLTree<Integer> avlTree = new AVLTree<Integer>();
		for(int i=0;i<6;i++)
			avlTree.insert(i, i);
		
		avlTree.debug(avlTree.root);
		for(int i=0;i<6;i++)
		{
			System.out.println();
			avlTree.remove(i);
			avlTree.debug(avlTree.root);
			avlTree.insert(i, i);
			System.out.println();
			avlTree.debug(avlTree.root);
		}
		
	}

}
