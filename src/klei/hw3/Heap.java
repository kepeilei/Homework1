package klei.hw3;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 *  The {@code Heap} class provides a static methods for heapsorting
 *  an array.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class Heap {
	static int comparisons;
	static int exchanges;
	static int compCount;
	static int exchCount;
	/**
	  * Rearranges the array in ascending order, using the natural order.
	  * @param a the array to be sorted
	  */
	 public static void sort(Comparable[] a) {
		 int n = a.length;
		 
		 compCount = 0;
		 exchCount = 0;
		 // construct heap from the raw array of which we know nothing.
		 for (int k = n/2; k >= 1; k--) {
			 sink(a, k, n);
		 }
		 comparisons = compCount;
		 exchanges = exchCount;
		 
		 // at this point, a has been turned into a heap.
		 
		 while (n > 1) {
			 exch(a, 1, n--);
			 sink(a, 1, n);
		 }
	 }

	 /***************************************************************************
	  * Helper functions to restore the heap invariant.
	  ***************************************************************************/

	 private static void sink(Comparable[] pq, int k, int n) {
		 while (2*k <= n) {
			 int j = 2*k;
			 compCount ++;
			 if (j < n && less(pq, j, j+1)) j++;
			 compCount ++;
			 if (!less(pq, k, j)) break;
			 exchCount ++;
			 exch(pq, k, j);
			 k = j;
		 }
	 }

	 /***************************************************************************
	  * Helper functions for comparisons and swaps.
	  * Indices are "off-by-one" to support 1-based indexing.
	  ***************************************************************************/
	 private static boolean less(Comparable[] pq, int i, int j) {
		 return pq[i-1].compareTo(pq[j-1]) < 0;
	 }

	 private static void exch(Object[] pq, int i, int j) {
		 Object swap = pq[i-1];
		 pq[i-1] = pq[j-1];
		 pq[j-1] = swap;
	 }

	 // print array to standard output
	 private static void show(Comparable[] a) {
		 for (int i = 0; i < a.length; i++) {
			 StdOut.println(a[i]);
		 }
	 }

	 /**
	  * Reads in a sequence of strings from standard input; heapsorts them; 
	  * and prints them to standard output in ascending order. 
	  *
	  * @param args the command-line arguments
	  */
	 public static void main(String[] args) {
		 String[] a = StdIn.readAllStrings();
		 Heap.sort(a);
		 show(a);
	 }
 }
