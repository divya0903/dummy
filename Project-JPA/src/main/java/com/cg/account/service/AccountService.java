package com.cg.account.service;

import com.cg.account.bean.Account;
import com.cg.account.dao.AccountDao;
import com.cg.account.dao.IAccountDao;
import com.cg.account.exception.AccountException;

public class AccountService implements IAccountService {
	IAccountDao accountDao = new AccountDao();

	@Override
	public String createAccount(Account account) throws AccountException {

		return accountDao.createAccount(account);
	}

	@Override
	public boolean validateAccount(Account acc) throws AccountException {
		if (validateMobile(acc.getMobile()) && validateName(acc.getName()) && validateEmail(acc.getEmail())
				&& validateBalance(acc.getBalance()))
			return true;
		return false;
	}

	@Override
	public boolean validateMobile(String mobile) throws AccountException {
		if (!mobile.matches("\\d{10}"))
			throw new AccountException("Mobile number should contain 10 digits");
		return true;
	}

	@Override
	public boolean validateName(String name) throws AccountException {
		if (name.isEmpty() || name == null)
			throw new AccountException("Name cannot be empty");
		else if (!name.matches("[A-Za-z]{2,}"))
			throw new AccountException("Name must contain only alphabets");
		return true;
	}

	@Override
	public boolean validateEmail(String email) throws AccountException {
		if (!email.matches("[A-Za-z0-9]{3,}@{1}[a-z]{2,}\\.com"))
			throw new AccountException("Invalid Email ID");
		return true;
	}

	@Override
	public boolean validateBalance(double balance) throws AccountException {
		if (balance <= 0)
			throw new AccountException("Balance must be a number greater than zero");
		return true;
	}

	@Override
	public double showBalance(String mobile) throws AccountException {

		if (validateMobile(mobile)) {
			Account acc = accountDao.printTransaction(mobile);
			if (acc == null)
				throw new AccountException("Account Does not Exist");
		}
		return accountDao.showBalance(mobile);
	}

	@Override
	public double deposit(String mobile, double amt) throws AccountException {

		return accountDao.deposit(mobile, amt);
	}

	@Override
	public double withdraw(String mobile, double amt) throws AccountException {
		// TODO Auto-generated method stub
		Account acc = accountDao.printTransaction(mobile);
		if (amt > acc.getBalance())
			throw new AccountException("Account balance is Low");
		return accountDao.withdraw(mobile, amt);
	}

	@Override
	public boolean fundTransfer(String mobile1, String mobile2, double amount) throws AccountException {
		// TODO Auto-generated method stub
		if (!validateMobile(mobile1) || !validateMobile(mobile2))
			throw new AccountException("Mobile number should contain 10 digits");
		if (amount < 0)
			throw new AccountException("Amount must be a number greater than zero");
		return accountDao.fundTransfer(mobile1, mobile2, amount);
	}

	@Override
	public Account printTransaction(String mobile) throws AccountException {
		// TODO Auto-generated method stub
		return accountDao.printTransaction(mobile);
	}

}
