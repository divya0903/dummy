
	package com.cg.account.service;

	import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;

	public interface IAccountService {
		public boolean validateMobile(String mobile) throws AccountException;
		
		public boolean validateNullName(String name) throws AccountException;
		
		public boolean validateName(String name) throws AccountException;
		
		public boolean validateEmail(String email) throws AccountException;
		
		public boolean validateBalance(double balance) throws AccountException;
		
		public String createAccount(Account account) throws AccountException;

		public boolean validateAccount(Account acc) throws AccountException;
		
		public double deposit(String mobile,double amount) throws AccountException;
		
		public double withdraw(String mobile, double amount)throws AccountException;

		public Account getAccountDetails(String mobile)throws AccountException;
		
		public boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException;

		public double showBalance(String mobile)throws AccountException;
		
	}



