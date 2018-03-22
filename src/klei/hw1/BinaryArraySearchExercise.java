package klei.hw1;

import edu.princeton.cs.algs4.StdOut;

/**
 * This demonstration of the algorithm assumes the values in the 2D arrays are all
 * positive integers
 * @author Kepei Lei
 * The algorithm goes through the first two rows of the list with the same idea from merge sort
 * to find the special value, then use binary search to look for the special value in the rest of the
 * 2D arrays
 * 
 * See the worst case analysis along with bonus questions in WrittenQuestions.txt
 */

public class BinaryArraySearchExercise {
	
	public static int arrayInspections;
	
	private static final int TESTING_ARRAYS[][] = new int[][] {{5, 12, 18, 22},
															   {2, 10, 12, 70},
															   {1, 3, 9, 12},
															   {12, 17, 24, 76},
															   {8, 11, 12, 19},
															   {7, 12, 49, 51}
															   };
	
	public static void main (String args[]) {
		
		int specialValue = findSpecialValue(TESTING_ARRAYS[0], TESTING_ARRAYS[1], 0, 1);
		if (specialValue == -1) {
			throw new IllegalStateException("There is no special value found");
		}
		for (int i = 2; i < TESTING_ARRAYS.length; i++){
			int index = rank (TESTING_ARRAYS[i], specialValue);
			if (index < 0) {
				throw new IllegalStateException("There is no special value in the current row");
			}
			StdOut.print(" (" + i + "," + index + ")");
		}
	}
	
	/**
	 * Copy and pasted the binary search from algs.days.day01.BinaryIntSearch.java
	 * @param collection
	 * @param target
	 * @return
	 */
	public static int rank (int[] collection, int target) {
		int low = 0;
		int high = collection.length-1;
		int arrayInspection = 0;

		while (low <= high) {
			int mid = (low+high)/2;

			int rc = collection[mid] - target;
			arrayInspection ++;
			if (rc < 0) {
				low = mid+1;
			} else if (rc > 0) {
				high = mid-1;
			} else {
				arrayInspections = arrayInspection;
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * Find the special value that repeats
	 * @param firstInts one row in the 2D array
	 * @param secondInts another row in the 2D array
	 * @param firstRow the index of the first row passed in
	 * @param secondRow the index of the second row passed in
	 * @return the special value as an integer
	 */
	public static int findSpecialValue (int[] firstInts, int[] secondInts, int firstRow, int secondRow){
		int firstIndex = 0;
		int secondIndex = 0;
		int arrayInspection = 0;
		while (firstIndex < firstInts.length && secondIndex < secondInts.length) {
			int rowOne = firstInts[firstIndex];
			int rowTow = secondInts[secondIndex];
			arrayInspection += 2;
			if (rowOne < rowTow) {
				firstIndex ++;
			} else if (rowOne > rowTow) {
				secondIndex ++;
			} else {
				// Comment out the following 3 lines when running AverageCase.java
				StdOut.println("Special value is " + firstInts[firstIndex]);
				StdOut.print("Located: (" + firstRow + "," + firstIndex + ")");
				StdOut.print(" (" + secondRow + "," + secondIndex + ")");
				arrayInspections = arrayInspection;
				return rowOne;
			}
		}
		return -1;
	}

}
