/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 3.0 1/11/15 16:49:42
 *
 *  @author TODO
 *
 *************************************************************************/

import java.util.NoSuchElementException;
import java.util.*;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root; // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key; // sorted by key
		private Value val; // associated data
		private Node left, right; // left and right subtrees
		private int N; // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		else
			return x.N;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public Value get(Key key) {
		return get(root, key);
	}

	private Value get(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			return get(x.left, key);
		else if (cmp > 0)
			return get(x.right, key);
		else
			return x.val;
	}
	public void put(Key key, Value val) {
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = put(x.left, key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	public int height() {
		return height(root);
	}

	public int height(Node node) {
		if (node == null) {
			return -1;
		} else {
			int leftH = height(node.left);
			int rightH = height(node.right);
			return 1 + Math.max(leftH, rightH);

		}
	}

	public Key median() {
		return median(root);
	}

	public Key median(Node node) {
		if(node==null) return null;
		return select(root, (root.N +1)/2).key;
	}

	
	private Node select(Node x, int pos) {
			int t = size(x.left)+1;
			if(t>pos) return select(x.left, pos);
			else if (t<pos) return select(x.right, pos-t);
			else return x;
	}
	
	public String printKeysInOrder() {
		if (isEmpty())
			return "()";
		else {
			return printKeysInOrder(root);
		}
	}

	public String printKeysInOrder(Node x) {
		if (x == null) {
			return "()";
		} else {
			String leftSub = printKeysInOrder(x.left);
			String rightSub = printKeysInOrder(x.right);
			return "(" + leftSub + x.key + rightSub + ")";

		}
	}

	
	public String prettyPrintKeys() {
		return prettyPrintKeys(root, "");
	}

	private String prettyPrintKeys(Node node, String prefix) {
		if (node == null)
			return prefix + "-null\n";
		else {
			String l = prefix + "-" + node.key + "\n" + prettyPrintKeys(node.left, prefix + " |");
			String r = prettyPrintKeys(node.right, prefix + "  ");
			return l + r;

		}

	}

	public void delete(Key k) {
		root = delete(root, k);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int temp = key.compareTo(x.key);
		if (temp < 0)
			x.left = delete(x.left, key);
		else if (temp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;
			Node t = x;
			x = getMax(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}

	private Node deleteMax(Node x) {
		Node temp = getMax(x);
		return delete(x, temp.key);
	}

	private Node getMax(Node x) {
		if (x.right == null) {
			return x;
		} else {
			return getMax(x.right);
		}
	}
}