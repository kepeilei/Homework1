package klei.hw2;

/**
 * Your goal is to output a table that looks like this:

N	Avg.	Inspectk
1	1.00	1
2	1.50	1,2
4	2.00	2,1,2,3
8	2.63	3,2,3,1,3,2,3,4
16	3.38	4,3,4,2,4,3,4,1,4,3,4,2,4,3,4,5
32	4.22	5,4,5,3,5,4,5,2,5,4,5,3,5,4,5,1,5,4,5,3,5,4,5,2,5,4,5,3,5,4,5,6
64	5.13	6,5,6,4,6,5,6,3,6,5,6,4,6,5,6,2,6,5,6,4,6,5,6,3,6,5,6,4,6,5,6,1,6,5,6,4,6,5,6,3,6,5,6,4,6,5,6,2,6,5,6,4,6,5,6,3,6,5,6,4,6,5,6,7


Bonus Question (1pt.)

See homework. Come up with a mathematical formula that computes this average value.

 */

import edu.princeton.cs.algs4.StdIn;

public class Analysis {
	
	/**
	 * Generate an array of size N with no duplicate values
	 * @param N the size of array
	 * @return an array of size N with no duplicate values
	 */
	private static int[] generateArray (int N) {
		int[] array = new int[N];
		for (int i = 0; i < N; i++) {
			array[i] = i;
		}
		return array;
	}
	
	/**
	 * Calculate the average of an int array
	 * @param values the int array
	 * @return average of the values
	 */
	private static double calculateAvg(int[] values) {
		double sum = 0;
		for (int value: values) {
			sum += value;
		}
		return (sum / values.length);
	}
	
	/**
	 * Convert the given array into a string in a a format (x,y,z...)
	 * @param array the array to be converted
	 * @return the converted String
	 */
	private static String arrayToString (int[] array) {
		String str = "";
		for (int value: array) {
			str = str + value + ",";
		}
		str = str.substring(0, str.length()-1);
		return str;
	}
	
	public static void main(String[] args) {
		int max = StdIn.readInt();
		System.out.println("N" + "\t" + "Avg." + "\t" + "Insepctk");
		for (int i = 1; i <= Math.pow(2, max); i = i * 2){
			int[] array = generateArray (i);
			int[] inspections = new int[array.length];
			for (int j = 0; j < array.length; j++) {
				BinaryIntSearch bis = new BinaryIntSearch();
				bis.contains(array, j);
				inspections[j] = bis.getInspection();
			}
			double average = calculateAvg (inspections);
			String inspectionStr = arrayToString (inspections);
			System.out.println(i + "\t" + average + "\t" + inspectionStr);
		}
	}
}
