package com.cg.account.dao;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;

public interface IAccountDao {
	
	String createAccount(Account account) throws AccountException;
	double showBalance(String mobile) throws AccountException;
	double deposit(String mobile, double amt) throws AccountException;
	double withdraw(String mobile, double amt) throws AccountException;
	boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException;
	Account printTransaction(String mobile) throws AccountException;
		

}
