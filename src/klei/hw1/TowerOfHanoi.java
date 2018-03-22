package klei.hw1;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Standard Tower of Hanoi disk problem (https://en.wikipedia.org/wiki/Tower_of_Hanoi).
 */
public class TowerOfHanoi {

	/** Three separate stacks, each to hold up to four disks. Leave this alone. */
	static FixedCapacityStackOfInts stacks[] = new FixedCapacityStackOfInts[] {
			new FixedCapacityStackOfInts(4), new FixedCapacityStackOfInts(4), new FixedCapacityStackOfInts(4)
	};

	/** 
	 * Output the state in visible form. Modify this method.
	 * 
	 * You need to modify this method so it outputs state as follows:
	 * 
	 * Stack1: 4321
	 * Stack2:
	 * Stack3:
	 * 
	 * If, given this above state, you make a move from stack1 to stack3, then the output would be:
	 * 
	 * Stack1: 432
	 * Stack2:
	 * Stack3: 1
	 */
	public static void outputState() {
		for (int i = 0; i < stacks.length; i ++) {
			String str = stackToString(stacks[i]);
			StdOut.println("Stack" + (i + 1) + ": " + str);
		}
	}

	/**
	 * Convert the intStack to a String in an order of bottom to top
	 * @param intStack the FixedCapacityStackOfInts that needs to be converted
	 * @return the String representing the Stack from bottom to top
	 */
	private static String stackToString (FixedCapacityStackOfInts intStack) {
		String str = "";
		for (int i : intStack) {
			str = Integer.toString(i) + str;
		}
		return str;
	}

	/**
	 * You must write this method. A move is allowed if the disk from the top of
	 * stack 'from' is smaller in size than the topmost disk on the top of stack 'to'.
	 * 
	 * @param from   stack containing the topmost disk to move
	 * @param to     stack representing the destination disk
	 */
	public static boolean move(int from, int to) {
		if (from == to || (from < 1 || from > 3) || (to < 1 || to > 3)) 
			return false;
		from--;
		to--; //convert the input to array indexes
		FixedCapacityStackOfInts fromStack = stacks[from];
		if (fromStack.isEmpty()) {
			return false;
		} else {
			int fromTop = fromStack.pop();
			FixedCapacityStackOfInts toStack = stacks[to];
			if (toStack.isEmpty()) {
				toStack.push(fromTop);
				return true;
			} else {
				int toTop = toStack.pop();
				if (fromTop < toTop) {
					toStack.push(toTop);
					toStack.push(fromTop);
					return true;
				} else {
					toStack.push(toTop);
					fromStack.push(fromTop);
					return false;
				}
			}
		}
	}

	/** You do not need to modify this method. */
	public static void main(String[] args) {
		/* Load up four disks on stack 1, so disk-4 is at the bottom and disk-1 is at the top. */
		stacks[0].push(4);
		stacks[0].push(3);
		stacks[0].push(2);
		stacks[0].push(1);

		int numMoves = 0;
		while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
			outputState();
			StdOut.println ("Enter two stack numbers A B to move top disk on A to B. You win when all disks are on Stack 3.");
			int from = StdIn.readInt();
			int to = StdIn.readInt();

			System.out.println("Moving top disk from stack " + from + " to stack " + to);
			if (!move(from, to)) {
				StdOut.println ("That move is not allowed!");
			} else {
				numMoves++;
			}
		}

		StdOut.println ("Congratulations! You completed the puzzle in " + numMoves + " moves.");
	}
}
