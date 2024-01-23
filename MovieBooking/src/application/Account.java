package application;
//this class is for future use when incorporating payment system
public class Account {

	// Private fields to store account data
	private int accountNumber;
	private double balance;
	private String ownerName;
	private String paymentCardInformation;

	// Constructor with initial values
	public Account(int accountNumber, double balance, String ownerName, String paymentCardInformation)

	{
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.ownerName = ownerName;
		this.paymentCardInformation = paymentCardInformation;
	}

	// Getters
	public int getAccountNumber()

	{
		return accountNumber;
	}

	public

			double

			getBalance()

	{
		return balance;
	}

	public String getOwnerName()

	{
		return ownerName;
	}

	// Setters
	public

			void

			setAccountNumber(int accountNumber)

	{
		this.accountNumber = accountNumber;
	}

	public

			void

			setBalance(double balance)

	{
		this.balance = balance;
	}

	public

			void

			setOwnerName(String ownerName)

	{
		this.ownerName = ownerName;
	}

	// Method to deposit money
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		} else {
			throw new IllegalArgumentException("Deposit amount must be positive.");
		}
	}

	// Method to withdraw money
	public void withdraw(double amount) {
		if (amount > 0 && balance >= amount) {
			balance -= amount;
		} else {
			throw new IllegalArgumentException(
					"Withdrawal amount must be positive and not exceed the current balance.");
		}
	}

	// Method to display account information
	public String toString() {
		return "Account Number: " + accountNumber + "\n" + "Balance: $" + balance + "\n" + "Owner Name: " + ownerName;
	}

	public String getPaymentCardInformation() {
		return paymentCardInformation;
	}

	public void setPaymentCardInformation(String paymentCardInformation) {
		this.paymentCardInformation = paymentCardInformation;
	}
}