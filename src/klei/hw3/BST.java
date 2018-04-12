package klei.hw3;

/**
 * You will modify this class to support Q4 on the homework assignment.
 * 
 * @author heineman
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

	int tmpCount;
	int compareCount;
	Node root;               // root of the tree
	
	class Node {
		Key    key;          
		Value  val;         
		Node   left, right;  // left and right subtrees
		int    N;            // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	public boolean isEmpty() { return size() == 0; }

	/** Return number of key-value pairs in ST. */
	public int size()                { return size(root); }

	// Helper method that deals with "empty nodes"
	private int size(Node node) {
        if (node == null) return 0;
        
        return node.N;
    }

	// One-line method for containment. 
	public boolean contains(Key key) { return get(key) != null; }

	/** Search parent. */
	public Value get(Key key)        { tmpCount = 0; return get(root, key); }
	
	private Value get(Node parent, Key key) {
		if (parent == null) {compareCount = tmpCount; return null;}
		
		int cmp = key.compareTo(parent.key);
		tmpCount ++;
		
		if      (cmp < 0) return get(parent.left, key);
		else if (cmp > 0) return get(parent.right, key);
		else              {compareCount = tmpCount; return parent.val;}
	}

	/** Invoke put on parent, should it exist. */
	public void put(Key key, Value val) {
		root = put(root, key, val);
	}

	private Node put(Node parent, Key key, Value val) {
		if (parent == null) return new Node(key, val, 1);
		
		int cmp = key.compareTo(parent.key);
		if      (cmp < 0) parent.left  = put(parent.left,  key, val);
		else if (cmp > 0) parent.right = put(parent.right, key, val);
		else              parent.val   = val;
		
		parent.N = 1 + size(parent.left) + size(parent.right);
		return parent;
	}
	
	public int calculateCN(){
		return calculateCN(root);
	}
	
	private int calculateCN(Node parent) {
		int total = parent.N;
		if (parent.left != null) total += calculateCN(parent.left);
		if (parent.right != null) total += calculateCN(parent.right);
		return total;
	}
}
