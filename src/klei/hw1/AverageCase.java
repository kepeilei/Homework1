package klei.hw1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StopwatchCPU;
import klei.hw1.Queue;

/**
 * This average case calculator only works for 6x4 2D arrays
 * @author Kepei Lei
 *
 */

public class AverageCase {
	
	public static void main (String[] args) {
		StdOut.println("Generating values. This may take a few seconds...");
		Queue<int[][]> arraysToTime = generateArrays (50);
		int arrayInspections = 0;
		for (int[][] arrays: arraysToTime) {
			int specialValue = BinaryArraySearchExercise.findSpecialValue(arrays[0], arrays[1], 0, 1);
			arrayInspections += BinaryArraySearchExercise.arrayInspections;
			if (specialValue == -1) {
				throw new IllegalStateException("There is no special value found");
			}
			for (int i = 2; i < arrays.length; i++){
				int index = BinaryArraySearchExercise.rank (arrays[i], specialValue);
				arrayInspections += BinaryArraySearchExercise.arrayInspections;
				if (index < 0) {
					throw new IllegalStateException("There is no special value in the current row");
				}
			}
		}
		int amount = 4096;

		double averageInspections = arrayInspections / amount;
		StdOut.println("Amount = " + amount);
		StdOut.println("Average inspections = " + averageInspections);
	}

	private static Queue<int[][]> generateArrays (int specialValue) {
		Queue<int[][]> arraysToTime = new Queue<int[][]>();
		for (int a = 0; a < 4; a++) {
			for (int b = 0; b < 4; b++) {
				for (int c = 0; c < 4; c++) {
					for (int d = 0; d < 4; d ++) {
						for (int e = 0; e < 4; e ++) {
							for (int f = 0; f < 4; f ++) {
								int arrays[][] = new int[6][4];
								Queue<Integer> biggerQueue = numberQueue(specialValue, "bigger");
								Queue<Integer> smallerQueue = numberQueue(specialValue, "smaller");
								arrays[0][a] = specialValue;
								arrays[1][b] = specialValue;
								arrays[2][c] = specialValue;
								arrays[3][d] = specialValue;
								arrays[4][e] = specialValue;
								arrays[5][f] = specialValue;
								for (int i = 0; i < 4; i++) {
									for (int j = 0; j < 6; j++) {
										switch (j) {
										case 0 : if (i < a) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > a) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										case 1 : if (i < b) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > b) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										case 2 : if (i < c) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > c) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										case 3 : if (i < d) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > d) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										case 4 : if (i < e) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > e) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										case 5 : if (i < f) {
											arrays[j][i] = smallerQueue.dequeue();
										} else if (i > f) {
											arrays[j][i] = biggerQueue.dequeue();
										}
										break;
										}
									}
								}
								arraysToTime.enqueue(arrays);
							}
						}
					}
				}
			}
		}
		return arraysToTime;
	}

	private static Queue<Integer> numberQueue (int specialValue, String bigOrSmall){
		Queue<Integer> numbers = new Queue<Integer>();
		for (int i = 0; i < 18; i ++){
			if (bigOrSmall.equals("smaller")) {
				specialValue--;
				numbers.enqueue(specialValue);
			} else if (bigOrSmall.equals("bigger")){
				specialValue++;
				numbers.enqueue(specialValue);
			}
		}
		return numbers;
	}
}
