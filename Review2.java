// Review2
/*
 * import Scanner
 * String, int, double, boolean, char, 
 * if()
 * for()
 * while() {do}
 */
import java.util.Scanner;
class Review2 {
	public static void character() {
		Scanner scan = new Scanner(System.in);
		System.out.print("True or False? (t/f): ");
		char c = scan.next().charAt(0);
		if (c == 't') {
			System.out.println("true");
		}
		else if (c == 'f') {
			System.out.println("false");
		}
		else {
			System.out.println("Error: please try again");
			character();
		}
	}
	public boolean booleanC() {
		Scanner scan = new Scanner(System.in);
		System.out.print("True or False? (t/f): ");
		char c = scan.next().charAt(0);
		if (c == 't') {
			return true;
		}
		else if (c == 'f') {
			return false;
		}
		else {
			return false;
		}
	}
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		Review2 rev = new Review2();
		System.out.print("Input a word: ");
		String s = scan.next();
		System.out.println(s);
		System.out.print("Input an integer: ");
		int i = scan.nextInt();
		System.out.println(i);
		character();
		System.out.println(rev.booleanC());
		for (int j = 0; j < 3; j++) {
			System.out.println(j);
		}
		String password;
		do {
			System.out.print("Enter password: ");
			password = scan.next();
			if (!(password.equals("asdf"))) {
				System.out.println("Please try again.");
			}
		} while (!(password.equals("asdf")));
	}
}