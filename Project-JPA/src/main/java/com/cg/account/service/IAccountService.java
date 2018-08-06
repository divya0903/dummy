package com.cg.account.service;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;

public interface IAccountService {

	String createAccount(Account account) throws AccountException;

	double showBalance(String mobile) throws AccountException;

	double deposit(String mobile, double amt) throws AccountException;

	double withdraw(String mobile, double amt) throws AccountException;

	boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException;

	Account printTransaction(String mobile) throws AccountException;

	boolean validateAccount(Account account) throws AccountException;

	boolean validateMobile(String mobile) throws AccountException;

	boolean validateName(String name) throws AccountException;

	boolean validateEmail(String email) throws AccountException;

	boolean validateBalance(double balance) throws AccountException;

}
