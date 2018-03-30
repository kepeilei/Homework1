package klei.hw2;

import java.util.Arrays;
import java.util.Iterator;

/**
 * This data type offers Bag-like behavior with the added constraint that it tries
 * to minimize space by keeping track of the count of each item in the bag.
 *
 * Find the definition of MultiSet on Wikipedia (https://en.wikipedia.org/wiki/Multiset)
 *
 * In all of the performance specifications, N refers to the total number of items 
 * in the MultiSet while U refers to the total number of unique items.
 * 
 * Note that you will only have U nodes, one for each distinct item, and so U <= N; however, 
 * you can't know in advance HOW many duplicates will exist, so in the worst case, 
 * some computations will still depend upon N. 
 * 
 * @param <Item>
 */
public class MultiSet<Item extends Comparable<Item>> implements Iterable {

	Node first;
	private int N;
	private int U;


	/** You must use this Node class as part of a LinkedList to store the MultiSet items. Do not modify this class. */
	class Node {
		private Item   item;
		private int    count;
		private Node   next;

		public Node (Item it) {
			item = it;
			count = 1;
			next = null;
		}

		boolean equals(Node newNode) {
			return (item.equals(newNode.item) && (count == newNode.count));
		}
	}

	/** Create an empty MultiSet. */
	public MultiSet () { 
		N = 0;
		U = 0;
	}

	/**
	 * Initialize the MultiSet to contain the unique elements found in the initial list.
	 * 
	 * Performance is allowed to be dependent on N*N, where N is the number of total items in initial.
	 */
	public MultiSet(Item [] initial) { 
		N = 0;
		U = 0;
		for (int i = 0; i < initial.length; i++) {
			add(initial[i]);
		}
	}

	/** 
	 * Return the number of items in the MultiSet.
	 * 
	 * Performance must be independent of the number of items in the MultiSet, or ~ 1.
	 */
	public int size() {
		return N;
	}

	/**
	 * @return the number of unique items in the MultiSet.
	 */
	public int uniqueSize() {
		return U;
	}

	/** 
	 * Determines equality with another MultiSet objects.
	 * 
	 * Assume U=number of unique items in self while UO=number of unique items in other.
	 * 
	 * Performance must be linearly dependent upon min(U1,U2)
	 */
	public boolean identical (MultiSet<Item> other) { 
		if (other == null) {
			return false;
		}
		if (U != other.uniqueSize() || N != other.size()) {
			return false;
		} else {
			Node tmp1 = first;
			Node tmp2 = other.first;
			while (tmp1 != null && tmp2 != null) {
				if (!tmp1.equals(tmp2)) {
					return false;
				}
				tmp1 = tmp1.next;
				tmp2 = tmp2.next;
			}
			return true;
		}
	}

	/** 
	 * Return an array that contains the items from the MultiSet.
	 * 
	 * Performance must be linearly dependent on N.
	 */
	public Item[] toArray() {
		@SuppressWarnings("unchecked")
		Item[] array = (Item[]) new Comparable[N];
		int idx = 0;
		Node current = first;
		while (current != null) {
			for (int i = 0; i < current.count; i ++) {
				array[idx] = current.item;
				idx ++;
			}
			current = current.next;
		}
		return array;
	}

	/**
	 * Find the node that has the passed in parameter
	 * @param it the passed parameter to find
	 * @return null if there is no such element, else the node that contains such element
	 */
	public Node findNode (Item it) {
		Node current = first;
		while (current != null) {
			if (current.item.compareTo(it) == 0) {
				return current;
			}
			current = current.next;
		}
		return null;
	}

	public int debug = 0;
	/** 
	 * Add an item to the MultiSet.
	 * 
	 * Performance must be no worse than linearly dependent on N.
	 */
	public boolean add(Item it) {
		N++;
		Node existingNode = findNode(it);
		if (existingNode != null) {
			existingNode.count++;
			//			debug ++;
		} else if (first == null) {
			U++;
			first = new Node(it);
		} else {
			U++;
			Node node = new Node(it);
			Node tmp = first;
			Node prev = null;
			while (tmp != null && it.compareTo(tmp.item) > 0) {
				prev = tmp;
				tmp = tmp.next;
			}
			if (tmp != null) {
				node.next = tmp;
				if (tmp.equals(first)) {
					first = node;
				}
			}
			if (prev != null) {
				prev.next = node;
			}
		}
		return true;
	}

	/** 
	 * Add several same items to the MultiSet.
	 * 
	 * Performance must be no worse than linearly dependent on N.
	 */
	public boolean add(Item it, int amount) {
		N += amount;
		Node existingNode = findNode(it);
		if (existingNode != null) {
			existingNode.count += amount;
		} else if (first == null) {
			U++;
			first = new Node(it);
			first.count = amount;
		} else {	
			U++;
			Node node = new Node(it);
			node.count = amount;
			Node tmp = first;
			Node prev = null;
			while (tmp != null && it.compareTo(tmp.item) > 0) {
				prev = tmp;
				tmp = tmp.next;
			}
			if (tmp != null) {
				node.next = tmp;
				if (tmp.equals(first)) {
					first = node;
				}
			}
			if (prev != null) {
				prev.next = node;
			}
		}
	return true;
}

/** 
 * Remove an item from the MultiSet; return false if not in the MultiSet to
 * begin with, otherwise returns true on success.
 *  
 * Performance must be no worse than linearly dependent on N.
 */
public boolean remove (Item it) {
	Node tmp = first;
	Node prev = null;
	while (tmp != null) {
		if(tmp.item.equals(it)){
			tmp.count--;
			N--;
			if (tmp.count == 0) {
				U--;
				if (prev == null) {
					first = tmp.next;
				} else {
					prev.next = tmp.next;
				}
			}
			return true;
		}

		prev = tmp;
		tmp = tmp.next;
	}
	return false;
}

/** 
 * Returns the number of times item appears in the MultiSet.
 * 
 * If returns 0, then the item is not contained within this MultiSet.
 * 
 * Performance must be no worse than linearly dependent on U.
 */
public int multiplicity (Item it) {
	Node existingNode = findNode(it);
	if (existingNode == null) {
		return 0;
	} else {
		return existingNode.count;
	}
}

/** 
 * Determine whether this MultiSet includes other MultiSet.
 * 
 * A MultiSet A includes a MultiSet B when: for all elements x in B with multiplicity mB(x), the
 * multiplicity mA(x) in A is >= mB(x).
 * 
 * In degenerate case:
 *   1. If this is empty, false is always returned.
 *   2. If this is non-empty and other is empty, true is returned.
 * 
 * Performance must be linearly dependent on U + UO where U is the number of unique items in this
 * and UO is the number of unique items in other.
 */
public boolean includes(MultiSet<Item> other) {
	if (first == null) return false;
	if (other.first == null) return true;
	if (U < other.uniqueSize() || N < other.N) {return false;}
	else {
		Node bigTemp = findNode(other.first.item);
		Node smallTemp = other.first;
		while (smallTemp != null) {
			if (bigTemp == null) {return false;}
			if (!smallTemp.item.equals(bigTemp.item)) {
				bigTemp = bigTemp.next;
			} else if (bigTemp.count < smallTemp.count) {return false;}
			else {
				bigTemp = bigTemp.next;
				smallTemp = smallTemp.next;
			}
		}
		return true;
	}
}

/** 
 * Return a MultiSet which represents intersection with existing MultiSet.
 * 
 * Performance must be linearly dependent on the number of unique items in both MultiSet 
 * objects, or in other words ~ U + UO where U is the number of items in this and MO
 * is the number of items in other.
 * 
 * Consider definition of intersect on wikipedia page as to be the correct logic:
 * 
 * This is challenging.
 */
public MultiSet<Item> intersects(MultiSet<Item> other) {
	MultiSet<Item> intersectionSet = new MultiSet<Item>();
	Node tempA = first;
	Node tempB = other.first;
	while (tempA != null && tempB != null) {
		if (less(tempA, tempB)) {
			tempA = tempA.next;
		} else if (less(tempB, tempA)) {
			tempB = tempB.next;
		} else {
			intersectionSet.add(tempA.item, Math.min(tempA.count, tempB.count));
			tempA = tempA.next;
			tempB = tempB.next;
		}
	}
	return intersectionSet;
}

/** 
 * Return a MultiSet which represents union with existing MultiSet.
 * 
 * Performance must be linearly dependent on the number of items in both MultiSet 
 * objects, or in other words ~ UO + U where UO is the number of unique items in other and U
 * is the number of unique items in this MultiSet.
 * 
 * Consider definition of intersect on wikipedia page as to be the correct logic:
 * 
 * This is challenging.
 */
public MultiSet<Item> union(MultiSet<Item> other) {
	MultiSet<Item> unionSet = new MultiSet<Item>();
	Node tempA = first;
	Node tempB = other.first;
	while (tempA != null || tempB != null) {
		if (tempA == null) {
			unionSet.add(tempB.item, tempB.count);
			tempB = tempB.next;
		} else if (tempB == null) {
			unionSet.add(tempA.item, tempA.count);
			tempA = tempA.next;
		} else {
			if (less(tempA, tempB)) {
				unionSet.add(tempA.item, tempA.count);
				tempA = tempA.next;
			} else if (less(tempB, tempA)) {
				unionSet.add(tempB.item, tempB.count);
				tempB = tempB.next;
			} else {
				unionSet.add(tempA.item, Math.max(tempA.count, tempB.count));
				tempA = tempA.next;
				tempB = tempB.next;
			}
		}
	}
	return unionSet;
}

/**
 * A helper method to make comparison easier
 * @param nodeOne
 * @param nodeTwo
 * @return if nodeOne is less than nodeTwo
 */
private boolean less (Node nodeOne, Node nodeTwo) {
	return (nodeOne.item.compareTo(nodeTwo.item) < 0);
}

@Override
public Iterator<Item> iterator() {
	return new multiIt();
}

private class multiIt implements Iterator<Item> {
	Node current, next;
	int amountLeft;

	multiIt () {
		next = first;
		current = null;
		amountLeft = 0;
	}

	@Override
	public boolean hasNext() {
		return (next != null || (amountLeft > 0));
	}

	@Override
	public Item next() {
		if (amountLeft == 0) {
			current = next;
			amountLeft = current.count - 1;
			next = current.next;
		} else {
			amountLeft --;
		}
		return current.item;
	}

}
}
