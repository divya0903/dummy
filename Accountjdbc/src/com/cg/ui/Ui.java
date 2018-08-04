package com.cg.ui;

import java.util.Scanner;

import com.cg.wallet.bean.Wallet;
import com.cg.wallet.exception.WalletException;
import com.cg.wallet.service.IWalletService;
import com.cg.wallet.service.WalletService;

public class Ui {
	Scanner sc=new Scanner(System.in);

IWalletService service=new WalletService();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Ui obj=new Ui();
		String opt="";
		while(true)
		{
			
			System.out.println("1.Create account");
			System.out.println("2.Show balance");
			System.out.println("3.deposit");
			System.out.println("4.withdrawl");
			System.out.println("5.fund transfer");
			System.out.println("6.Account details");
			System.out.println("select choice");
			opt=sc.nextLine();
			switch(opt)
			{
			case "1":obj.createAcc();
					break;
			case "2":obj.showBal();
			break;
			case "3":obj.deposit();
			break;
			case "4":obj.withdraw();
			break;
			case "5":obj.fundTransfer();
			break;
			case "6":obj.printTransactions();
			break;
			case "7":sc.close();
			    System.exit(0);
			
			default:
				System.out.println("enter valid choice");
			}
		}
	}
		private void createAcc() {
			Wallet w = new Wallet();
			Ui ui = new Ui();

			System.out.println("Enter Name:");
			w.setName(ui.sc.nextLine());
			System.out.println("Enter Phone Number:");
			w.setPhone(ui.sc.nextLine());
			System.out.println("Enter Email ID:");
			w.setEmail(ui.sc.nextLine());
			System.out.println("Enter current balance:");
			w.setBalance(Double.parseDouble(ui.sc.nextLine()));
			try {
				service.validateWallet(w);
				String id = service.createWallet(w);
				System.out.println(id + " has been added successfully");
			
			
			} catch (WalletException e) {
				// TODO Auto-generated catch block
				System.out.println();
				System.err.println(e.getMessage());
				System.out.println();

			}
			
			
					
			}

		
		
public void showBal()
{
	System.out.println("Enter phone number:");
	String phone = sc.nextLine();

	try {
		double bal = service.showBalance(phone);
		System.out.println("Current balance:" + bal);
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();
	}
}

public void deposit()
{
	System.out.println("enter phone number");
	String phone=sc.nextLine();
	try {
		if (service.validatePhone(phone)) {
			//Account a = serv.getAccountDetails(mobile);
			System.out.println("Enter amount to be deposited:");
			double amt = Double.parseDouble(sc.nextLine());
			double finalAmt = service.deposit(phone, amt);
			System.out.println("Account with mobile id:" + phone
					+ " has been deposited with " + amt);
			System.out.println("Current Balance in the account:" + finalAmt);
		}

	} catch (WalletException e) {
		// TODO Auto-generated catch block
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();
	}
}
public void withdraw()
{
	System.out.println("Enter mobile number");
	String phone = sc.nextLine();
	try {
		if (service.validatePhone(phone)) {
			//Account a = serv.getAccountDetails(mobile);
			System.out.println("Enter amount to be withdrawn:");
			double amt = Double.parseDouble(sc.nextLine());
			double finalAmt = service.withdraw(phone, amt);
			System.out.println("Account with mobile id:" + phone
					+ " has been withdrawn with " + amt);
			System.out
					.println("Current Balance in the account:" + finalAmt);
		}

	} catch (WalletException e) {
		// TODO Auto-generated catch block
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();
	}
}
public void fundTransfer()
{
	System.out.println("Enter your mobile no:");
	String m1 = sc.nextLine();
	System.out.println("Enter receivers mobile number:");
	String m2 = sc.nextLine();
	System.out.println("Enter Transfer Amount:");
	double amount = Double.parseDouble(sc.nextLine());
	try {
		boolean res = service.fundTransfer(m1, m2, amount);
		if (res)
			System.out.println("Fund Transferred");

	} catch (WalletException e) {
		// TODO Auto-generated catch block
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();
	}
}
public void printTransactions()
{
	System.out.println("Enter Mobile number");
	String phone = sc.nextLine();
	try {
		System.out.println(service.getAccountDetails(phone));
	} catch (WalletException e) {
		// TODO Auto-generated catch block
		System.out.println();
		System.err.println(e.getMessage());
		System.out.println();

}
}
}
