package klei.hw2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key> {
	private Key[] pq;                    // store items at indices 1 to N (pq[0] is unused)
	private int N;                       // number of items on priority queue
	int lessCount;                       // Number of method less called for analysis purposes

	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
		N = 0;
	}

	public boolean isEmpty() { return N == 0;  }
	public int size() { return N; }

	public void insert(Key x) {
		if (N + 1 >= pq.length) {
			Key[] newPq = (Key[]) new Object[pq.length * 2];
			for (int i = 0; i < pq.length; i++) {
				newPq[i] = pq[i];
			}
			pq = newPq;
		}
		pq[++N] = x;
		swim(N);
	}

	public Key delMax() {
		Key max = pq[1];
		exch(1, N--);
		pq[N+1] = null;     // to avoid loitering and help with garbage collection
		sink(1);
		if (N * 3 < pq.length) {
			Key[] newPq = (Key[]) new Object[pq.length / 2];
			for (int i = 0; i < newPq.length; i ++) {
				newPq[i] = pq[i];
			}
		}
		return max;
	}
	
	public Key delMin() {
		int minIdx = N;
		Key min = pq[N];
		for (int i = N-1; i * 2 > N; i --) {
			if (less(i, minIdx)) {
				minIdx = i;
				min = pq[i];
			}
		}
		exch(N, minIdx);
		swim(minIdx);
		N--;
		if (N * 3 < pq.length) {
			Key[] newPq = (Key[]) new Object[pq.length / 2];
			for (int i = 0; i < newPq.length; i ++) {
				newPq[i] = pq[i];
			}
		}
		return min;
	}

	/***************************************************************************
	 * Helper functions to restore the heap invariant.
	 ***************************************************************************/
	private void swim(int k) {
		while (k > 1 && less(k/2, k)) {
			exch(k, k/2);
			k = k/2;
		}
	}

	private void sink(int k) {
		while (2*k <= N) {
			int j = 2*k;
			if (j < N && less(j, j+1)) j++;
			if (!less(k, j)) break;
			exch(k, j);
			k = j;
		}
	}

	/***************************************************************************
	 * Helper functions for compares and swaps.
	 ***************************************************************************/
	private boolean less(int i, int j) {
		lessCount ++;
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	// is pq[1..N] a max heap?
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	// is subtree of pq[1..N] rooted at k a max heap?
	private boolean isMaxHeap(int k) {
		if (k > N) return true;
		int left = 2*k, right = 2*k + 1;
		if (left  <= N && less(k, left))  return false;
		if (right <= N && less(k, right)) return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}
	
	/****************************
	 *  Sample main
	 ****************************/
	public static void main(String[] args) {
		MaxPQ<String> pq = new MaxPQ<String>(16);
		String[] a = StdIn.readAllStrings();
		for (String s : a) {
			pq.insert(s);
		}
		
		while (!pq.isEmpty()) {
			StdOut.println(pq.delMax());
		}
	}
}
