package klei.hw1;

import algs.hw1.TwiceSorted;

/**
 * Copy this class into your package, which must be USERID.hw1
 */
public class TwiceSorted_Solution extends TwiceSorted {

	/** Construct problem solution for given array. Do not modify this method. */
	public TwiceSorted_Solution(int[][] a) {
		super(a);
	}

	/** Construct problem solution using default sample array. Do not modify this method. */
	public TwiceSorted_Solution() {
		super();
	}
	
	/** 
	 * For this homework assignment, you need to complete the implementation of this
	 * method.
	 */
	@Override
	public int[] locate(int target) {
		int N = length();
		if (target < inspect(0,0)) return null;
		if (target > inspect(N-1,N-1)) return null;
		for (int i = 0; i < N; i++) {
			if (target >= inspect(i, 0) && target <= inspect (i, N-1)){
				int low = 0;
				int high = N - 1;
				while (low <= high) {
					int mid = (low + high) / 2;
					int rc = inspect (i,mid) - target;
					if (rc < 0){
						low = mid + 1;
					} else if (rc > 0) {
						high = mid - 1;
					} else {
						return new int[] {i, mid};
					}
				}
			}
		}
		
		// if target value is not found in array, then return null 
		return null;
	}
	


	/** Be sure that you call your class constructor. Do not modify this method. */ 
	public static void main (String args[]) {
		System.out.println("Number of inspections:" + new TwiceSorted_Solution().trial(512));
	}

}
