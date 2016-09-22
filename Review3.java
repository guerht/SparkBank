// Review3
import java.util.ArrayList;
public class Review3 {
	ArrayList<String> listOfNames = new ArrayList<String>();
	private static byte accounterNo;
	private String name;
	private Integer accountID;
	private Double balance;
	private String password;
	private static final double ANNUAL_INTERST = 0.025;
	// the following is an empty constructor set as a guide; it should not be used in the main method unless the static value has to be accessed.
	public Review3() {
		accounterNo = 0;
		name = "";
		balance = 0.0;
		password = "";
	}
	public Review3(String name, Integer accountID, Double balance, String password) {
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
	public void listNames() {
		System.out.print("[");
		for (int i = 0; i < listOfNames.size(); i++) {
			if(i != listOfNames.size()-1)
				System.out.print(listOfNames.get(i) + ", ");
			else
				System.out.print(listOfNames.get(i));
		}
		System.out.println("]");
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