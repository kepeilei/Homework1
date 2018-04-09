package klei.hw3;

import edu.princeton.cs.algs4.StdRandom;

public class HeapAnalysis {
	public static final int minSize = 16;
	public static final int maxSize = 2048;
	public static final int trial = 10;
	
	public static void main (String[] arg) {
		System.out.println("Size\t" + "Exchanges\t" + "Comparisons");
		for (int size = minSize; size <= maxSize; size *= 2) {
			int[] compCount = new int[trial];
			int[] exchCount = new int[trial];
			for (int t = 0; t < trial; t ++) {
				Double[] array = generateArray(size);
				Heap hp = new Heap();
				hp.sort(array);
				compCount[t] = hp.comparisons;
				exchCount[t] = hp.exchanges;
			}
			double compAvg = average(compCount);
			double exchAvg = average(exchCount);
			System.out.println(size + "\t" + compAvg + "\t\t" + exchAvg);
		}
	}
	
	private static double average (int[] array){
		double total = 0;
		double count = trial;
		for (int num: array) {
			total += num;
		}
		double avg = total / count;
		return avg;
	}
	
	private static Double[] generateArray (int size) {
		Double[] array = new Double[size];
		for (int i = 0; i < size; i++) {
			array[i] = StdRandom.uniform();
		}
		return array;
	}
}
