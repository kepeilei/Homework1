package klei.hw3;

import edu.princeton.cs.algs4.StdRandom;

public class BSTAnalysis {
	public static final int minSize = 64;
	public static final int maxSize = 16384;
	public static void main(String[] args) {
		System.out.println("N\t" + "Cn\t" + "H-Ave\t" + "M-Ave\t" + "Model");
		for (int size = minSize; size <= maxSize; size *=2) {
			double[] values = generateValues (size);
			BST<Double,Boolean> b = new BST<Double,Boolean>();
			for (double value: values) {
				b.put(value, true);
			}
			double hitCompare = 0;
			for (double value: values) {
				b.get(value);
				hitCompare += b.compareCount;
			}
			hitCompare = hitCompare / size;
			values = generateValues (size);
			double missCompare = 0;
			for (double value: values) {
				b.get(value);
				missCompare += b.compareCount;
			}
			missCompare = missCompare / size;
			int cN = b.calculateCN();
			double model = 1 + (double) cN / size;
			System.out.println(size + "\t" + cN + "\t" + hitCompare + "\t" + missCompare + "\t" + model);
		}
	}
	
	public static double log2 (double x) {
		return (Math.log(x)/Math.log(2));
	}
	
	public static double[] generateValues (int size) {
		double[] values = new double[size];
		for (int i = 0; i < size; i++) {
			values[i] = StdRandom.uniform();
		}
		return values;
	}

}
