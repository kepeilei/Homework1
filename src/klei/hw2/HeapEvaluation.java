package klei.hw2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class HeapEvaluation {
	public static final int low = 4;
	public static final int high = 8192;
	public static final int iterations = 1000;
	
	
	public static void main(String[] args) {
		int trial = 10;
		StdOut.println("N\tmaxComparisons");
		for (int n = low; n <= high; n *= 2) {
			MaxPQ<Double> experiment = generateRandomHeap(n);
			int maxComparisons = 0;
			for (int i = 0; i < trial; i++) {
				int currentComparisons = 0;
				for (int j = 0; j < iterations; j++){
					experiment.lessCount = 0;
					experiment.delMin();
					currentComparisons += experiment.lessCount;
					experiment.insert(StdRandom.uniform());
				}
				maxComparisons = Math.max(maxComparisons, currentComparisons);
			}
			StdOut.println(n + "\t" + maxComparisons);
		}

	}
	
	private static MaxPQ<Double> generateRandomHeap(int size) {
		MaxPQ<Double> experiment = new MaxPQ<Double>(size);
		for (int i = 0; i < size; i ++) {
			experiment.insert(StdRandom.uniform());
		}
		return experiment;
	}

}
