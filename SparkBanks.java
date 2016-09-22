// SparkBanks
import java.util.*;
/**
 * @author Seung Hoon Park <parkseunghoon@gpa.ac.kr>
 * @version 1.0 (beta)
 * @since 1.0
 * @see SparkBanks_Documentation.txt
 */
class SparkBanks {
	public static void main(String args[]) {
		Integer a1; // first option
		// the following datas are used in the generation of a new account.
		String newName;
		String newPassword;
		Double initDeposit;
		Integer iD;
		Character confirmation;
		// the following datas are used for the second option
		Integer iDen;
		Boolean accessed = false;
		String userPassword;
		String confirmUserPassword;
		Integer a2;
		Double change;
		Integer a3;
		String setName;
		String setPassword;
		Scanner scan = new Scanner(System.in);
		BankInfo basis = new BankInfo();
		List<BankInfo> list = new ArrayList<BankInfo>();
		boolean loggedInExit = true;
		boolean exit = true;
		System.out.println("\n\nWelcome to SparkBanks 1.0 (beta).\nThere are currently " + basis.getAccounterNo() + " user(s) in the server. ");
		do {
			System.out.println("\nEnter:\n 1 : To create an account.\n 2 : To log in.\n 3 : To check how many registered for this server.\n 4 : To exit program.\n 0 : To list registered users.");
			System.out.print(" ");
			a1 = scan.nextInt();
			scan.nextLine();
			if(a1 == 1) {
				System.out.println("\n\nPLEASE NOTE THAT YOUR ACCOUNT NUMBER, OR ACCOUNT ID, IS TO BE RANDOMLY GENERATED. ONCE AN ACCOUNT HAS BEEN CREATED, ALL INFORMATION THAT APPEARS ON THE SUMMARY SCREEN AFTERWARDS MUST BE KNOWN IN ORDER TO USE THE ACCOUNT IN THE FUTURE.\n\n");
				System.out.print("Enter your name: ");
				newName = scan.nextLine();
				System.out.print("Enter your password: ");
				newPassword = scan.nextLine();
				System.out.print("Enter your initial deposit: ");
				initDeposit = scan.nextDouble();
				iD = (int) (Math.random()*10000000);
				System.out.println("Is the information below correct? (y/n) \n Name: " + newName + "\n Initial Deposit: " + initDeposit + " credit(s)\n Password: " + newPassword);
				System.out.print(" ");
				confirmation = scan.next().charAt(0);
				if (confirmation == 'y') {
					// can only add one user at the moment; the accounterNo will, however, accumulate for every registration.
					list.add(new BankInfo(newName, iD, initDeposit, newPassword));
					System.out.println("\n\n::::\tSUMMARY PAGE\t::::");
					System.out.println("\n\nYour account has been created. Use these information to log in: \n Name: " + newName + "\n Account ID: " + iD + "\n Password: " + newPassword);
					System.out.println("You can now log in! You will be redirected to the main page.");
					a1 = null;
					newName = null;
					newPassword = null;
					initDeposit = null;
					iD = null;
					confirmation = null;
				}
				else if (confirmation == 'n') {
					System.out.println("\nYou will be redirected to the main screen. You must repeat the entire procedure in order to register.");
				}
				else {
					System.out.println("\nOur system has detected an error in your input. You will be redirected to the main screen. ");
				}
			}
			else if (a1 == 2) {
				System.out.print("\nEnter your account ID: ");
				iDen = scan.nextInt();
				scan.nextLine();
				for(BankInfo elements : list) {
					if(iDen.equals(elements.getAccountID())) {
						accessed = true;
						System.out.print("Welcome, " + elements.getName() + "! Please enter your password: ");
						userPassword = scan.nextLine();
						if(userPassword.equals(elements.getPassword())) {
							System.out.println("\nYou have successfully logged in. \n\nEnter: ");
							do {
								System.out.println(" 1 : To check balance.");
								System.out.println(" 2 : To withdraw.");
								System.out.println(" 3 : To deposit.");
								System.out.println(" 4 : To change personal information or this account.");
								System.out.println(" 5 : To log out.");
								System.out.print(" ");
								a2 = scan.nextInt();
								scan.nextLine();
								switch(a2) {
									case 1:
										System.out.println("\nYour current balance is: " + elements.getBalance() + " credit(s). \n\nEnter: ");
										loggedInExit = true;
										break;
									case 2:
										System.out.print("\nEnter the amount of credit you would like to withdraw: ");
										change = scan.nextDouble();
										scan.nextLine();
										if ((elements.getBalance() - change) >= 0) {
											elements.withdraw(change);
											System.out.println("\nYou have withdrawn " + change + " credit(s) from your account. You have " + elements.getBalance() + " credit(s) remaining. \n\nEnter: ");
										}
										else if ((elements.getBalance() - change) < 0) {
											System.out.println("\nYou have insufficient funds to withdraw such an amount from your account. \n\nEnter: ");
										}
										loggedInExit = true;
										break;
									case 3:
										System.out.print("\nEnter the amount of credit you would like to deposit: ");
										change = scan.nextDouble();
										scan.nextLine();
										elements.deposit(change);
										System.out.println("\nYou have deposited " + change + " credit(s) from your account. You have " + elements.getBalance() + " credit(s) remaining. \n\nEnter: ");
										loggedInExit = true;
										break;
									case 4:
										System.out.print("\nEnter your password for confirmation: ");
										confirmUserPassword = scan.nextLine();
										if(confirmUserPassword.equals(elements.getPassword())) {
											System.out.println("\nWhich information would you like to alter?");
											System.out.println(" 1 : Name. ");
											System.out.println(" 2 : Password.");
											System.out.print(" ");
											a3 = scan.nextInt();
											scan.nextLine();
											switch(a3) {
												case 1:
													System.out.print("\nEnter your new name: ");
													setName = scan.nextLine();
													elements.setName(setName);
													System.out.println("Your name has changed successfully, Mr. / Mrs. " + elements.getName() + ". \n\nEnter: ");
													break;
												case 2:
													System.out.print("\nEnter your new password: ");
													setPassword = scan.nextLine();
													elements.setPassword(setPassword);
													System.out.println("\nYour password has changed successfully, Mr. / Mrs. " + elements.getName() + ". \n\nEnter: ");
													break;
												default:
													System.out.println("\nYour response was not interpreted by our computer correctly. For security reasons, you must repeat the procedure from the beginning. \n\nEnter: ");
													break;
											}
										}
										else {
											System.out.println("\nYour password is incorrect. \n\nEnter: ");
										}
										loggedInExit = true;
										break;
									case 5:
										loggedInExit = false;
										System.out.println("\nYou have successfully logged out. You will be redirected to the main screen.");
										break;
									default:
										System.out.println("\nOur system has detected an error in your input. \n\nEnter:");
										loggedInExit = true;
										break;
								}
								a1 = null;
								a2 = null;
								a3 = null;
								change = null;
								iDen = null;
								userPassword = null;
							} while (loggedInExit);
						}
						else {
							System.out.println("\nYour password is incorrect. You will be redirected to the main screen.");
							a1 = null;
							iDen = null;
							userPassword = null;
						}
					}
					if(accessed) {
						break;
					}
				}
				if(!(accessed)) {
					System.out.println("\nYour account ID could not be found. You will be redirected to the main screen.");
				}
				accessed = false;
			}
			else if (a1 == 3) {
				System.out.println("\nThere are currently " + basis.getAccounterNo() + " user(s) in the server.");
			}
			else if (a1 == 4) {
				System.out.println("\nGoodbye! Come again!");
				exit = false;
			}
			else if (a1 == 0) {
				basis.listNames();
			}
			else {
				System.out.println("\nOur system has detected an error in your input. You will be redirected to the main screen. ");
			}
		} while (exit);
	}
}
/* 
 *  Modifications to do:
 * 	- Use a sort method to sort both lists in lexicographical order.
 *	- Use a delimiter to set requirements for a password. The requirements are:
 *		1. It should be at least 8 characters long.
 *		2. It must contain at least 3 capital letters.
 *		3. All small case letters, capital letters, numbers, and special keys can be used; spaces cannot be used.
 *		4. It must contain at least one numerical value.
 *	- Create a 'confirm password' method and compare two inputs in order to match the two Strings.
 *	- If possible, whenever one inputs a password, make sure the immediate output displays asterisks only
 *	- If possible, try to make the information persistent, not volatile.
 *	- Add a concept of time in the program and implement the method where interest accumulates over the balance.
 *  - Make each account ID a unique number (Although the possibility of getting two identical account ID is 1 of 9999999).
 */