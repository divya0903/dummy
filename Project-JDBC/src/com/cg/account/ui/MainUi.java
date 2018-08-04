package com.cg.account.ui;

import java.util.Scanner;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.account.service.AccountService;
import com.cg.account.service.IAccountService;

public class MainUi {

	static Scanner sc = new Scanner(System.in);
	IAccountService service = new AccountService();
	static MainUi m = new MainUi();
	public static void main(String[] args) {

		
		String option = "";
		while (true) {
			System.out
					.println("1. Create Account\n2. Show balance\n3. Deposit amount\n"
							+ "4. Withdraw amount\n5. Transfer\n6. Print Transactions\n7. Exit");
			System.out.println("Enter choice:");
			option = sc.nextLine();
			switch (option) {
			case "1":
				m.accountCreation();
				break;
			case "2":
				m.showBalance();
				break;
			case "3":
				m.depositAmount();
				break;
			case "4":
				m.withdrawAmount();
				break;
			case "5":
				m.fundTransfer();
				break;
			case "6":
				m.printTransactions();
				break;
			case "7":
				sc.close();
				System.exit(0);
				break;
			default:
				System.out.println("Enter Correct option");
				break;
			}
		}
	}
		
	private void accountCreation() {
		Account a = new Account();
		

		System.out.println("Enter Name:");
		a.setName(m.sc.nextLine());
		System.out.println("Enter Mobile Number:");
		a.setMobile(m.sc.nextLine());
		System.out.println("Enter Email ID:");
		a.setEmail(m.sc.nextLine());
		System.out.println("Enter current balance:");
		a.setBalance(Double.parseDouble(m.sc.nextLine()));
		
		try {
			service.validateAccount(a);
			String id = service.createAccount(a);
			System.out.println(id + " has been added successfully");
		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
		
	}
	private void showBalance() {
	
		System.out.println("Enter mobile number:");
		String mobile = sc.nextLine();

		try {
			double bal = service.showBalance(mobile);
			System.out.println("Current balance:" + bal);
		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
		
	}
	private void depositAmount() {
		System.out.println("Enter mobile number");
		String mobile = sc.nextLine();
		try {
			if (service.validateMobile(mobile)) {
				
				System.out.println("Enter amount to be deposited:");
				double amount = Double.parseDouble(sc.nextLine());
				double finalAmt = service.deposit(mobile, amount);
				System.out.println("Account with mobile id:" + mobile
						+ " has been deposited with " + amount);
				System.out
						.println("Current Balance in the account:" + finalAmt);
			}

		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
		
	}
	private void withdrawAmount() {
		System.out.println("Enter mobile number");
		String mobile = sc.nextLine();
		try {
			if (service.validateMobile(mobile)) {
				
				System.out.println("Enter amount to be withdrawn:");
				double amount = Double.parseDouble(sc.nextLine());
				double finalAmt = service.withdraw(mobile, amount);
				System.out.println("Account with mobile id:" + mobile
						+ " has been withdrawn with " + amount);
				System.out
						.println("Current Balance in the account:" + finalAmt);
			}

		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}	
	}
	private void fundTransfer() {
		System.out.println("Enter your mobile no:");
		String mobile1 = sc.nextLine();
		System.out.println("Enter receivers mobile number:");
		String mobile2 = sc.nextLine();
		System.out.println("Enter Transfer Amount:");
		double amount = Double.parseDouble(sc.nextLine());
		try {
			boolean result = service.fundTransfer(mobile1, mobile2, amount);
			if (result)
				System.out.println("Fund Transferred");

		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
		
	}
	private void printTransactions() {
		System.out.println("Enter your mobile number: ");
		String mobile=sc.nextLine();
		try {
			service.validateMobile(mobile);
			System.out.println(service.getAccountDetails(mobile));
			/*System.out.println(a.getName());
			System.out.println(a.getMobile());
			System.out.println(a.getEmail());
			System.out.println(a.getBalance());*/
		} catch (AccountException e) {
			
			System.out.println();
			System.err.println(e.getMessage());
			System.out.println();
		}
		
	}

		
	}

	

