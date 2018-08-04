package com.cg.account.dao;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;

public interface IAccountDao {
	
	public String createAccount(Account account) throws AccountException;
	
	public double deposit(String mobile,double amount) throws AccountException;
	
	public double withdraw(String mobile, double amount)throws AccountException;
	
	public Account getAccountDetails(String mobile)throws AccountException;
	
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException;
	
	public double showBalance(String mobile) throws AccountException;
	
	public Account printTransaction(String mobile)throws AccountException;

}

