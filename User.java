public class User {
	private String name;
	private Integer accountID;
	private Integer pin;
	private Double balance;
	private static final double INTEREST_RATE = 0.025;
	public User() {
		name = "";
		accountID = 0;
		pin = 0;
		balance = 0.0;
	}
	public User(String name, Integer accountID, Integer pin) {
		this.name = name;
		this.accountID = accountID;
		this.pin = pin;
		balance = 0.0;
	}
	public String getName() {
		return name;
	}
	public Integer getAccountID() {
		return accountID;
	}
	public Integer getPIN() {
		return pin;
	}
	public Double getBalance() {
		return balance;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAccountID(Integer accountID) {
		this.accountID = accountID;
	}
	public void setPIN(Integer pin) {
		this.pin = pin;
	}
	public void setBalance(Double amount) {
		balance = amount;
	}
	public void withdraw(Double amount) {
		balance -= amount;
	}
	public void deposit(Double amount) {
		balance += amount;
	}
}