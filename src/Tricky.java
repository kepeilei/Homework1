
public class Tricky {
	
	public static void main(String[] arg) {
		System.out.println(t(-4));
	}

	public static void tricky3 (int n) {
		if (n>=0) {
			System.out.print(n);
			tricky3(n-2);
			System.out.print(n);
		}
	}
	
	public static int t(int n) {
		if (n == 1 || n == 2) {
			return 2*n;
		} else {
				return t(n-1) - t(n-2);
			}
		}
	}
