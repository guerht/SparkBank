// BankInfo
import java.util.ArrayList;
/**
 * @author Seung Hoon Park <parkseunghoon@gpa.ac.kr>
 * @version 1.0 (beta)
 * @since 1.0
 */
public class BankInfo {
	private static ArrayList<String> listOfNames = new ArrayList<String>();
	private static byte accounterNo;
	private String name;
	private Integer accountID;
	private Double balance;
	private String password;
	private static final double ANNUAL_INTERST = 0.025;
	// the following is an empty constructor set as a guide; it should only used in the main method as a means to access static values and datas.
	public BankInfo() {
		accounterNo = 0;
		name = "";
		balance = 0.0;
		password = "";
	}
	public BankInfo(String name, Integer accountID, Double balance, String password) {
		accounterNo++;
		this.name = name;
		this.accountID = accountID;
		this.balance = balance;
		this.password = password;
		listOfNames.add(name);
	}
	public byte getAccounterNo() {
		return accounterNo;
	}
	public String getName() {
		return name;
	}
	public Integer getAccountID() {
		return accountID;
	}
	public Double getBalance() {
		return balance;
	}
	public String getPassword() {
		return password;
	}
	/**
	 * Lists users in a certain order.
	 * @param void
	 * @return void
	 * Lists users by gaining access to the static ArrayList listOfNames using the normal for loop. 
	 */
	public void listNames() {
		if (listOfNames.size() > 0) {
			System.out.print("\nRegistered Users: ");
			if(listOfNames.size() != 1) {
				for (int i = 0; i < listOfNames.size(); i++) {
					if(i != listOfNames.size()-1)
						System.out.print(listOfNames.get(i) + ", ");
					else
						System.out.println("and " + listOfNames.get(i) + ". ");
				}
			}
			else {
				System.out.println(listOfNames.get(0) + ".");
			}
		}
		else {
			System.out.println("\nThere are no registered users on this server yet. ");
		}
	}
	public void setName(String newName) {
		name = newName;
	}
	public void setPassword(String newPassword) {
		password = newPassword;
	}
	public void withdraw(Double amount) {
		balance -= amount;
	}
	public void deposit(Double amount) {
		balance += amount;
	}
	public void interestMultiplier() {
		if (balance > 100.0) {
			balance = balance + balance * ANNUAL_INTERST;
		}
	}
}